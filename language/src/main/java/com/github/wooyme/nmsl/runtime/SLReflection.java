package com.github.wooyme.nmsl.runtime;

import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.nodes.expression.SLFunctionLiteralNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import javassist.*;
import javassist.bytecode.SignatureAttribute;
import org.graalvm.collections.Pair;

import java.lang.reflect.Modifier;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SLReflection {
    private static HashMap<String, SLFunctionLiteralNode> operationMap = new HashMap<>();

    public static void addOperation(SLFunctionLiteralNode functionNode) {
        String funcName = functionNode.getFunctionName();
        operationMap.put(funcName, functionNode);
    }

    public static class SLReflectionMethod {
        private static final Map<String, String> boxing = new HashMap<>();
        private static final Map<String,String> unboxingOperation = new HashMap<>();
        private static final Map<String,String> boxingOperation = new HashMap<>();
        private static final Map<Pair<Class, String>, Map<Method,Callable>> cache = new ConcurrentHashMap<>();
        private static final Map<Pair<Class, String>, Method[]> cachedMethods = new ConcurrentHashMap<>();
        private static final Set<Pair<Class,String>> blackList = new HashSet<>();
        private final Object obj;
        private final Pair<Class, String> classStringPair;
        private final Method[] methods;
        private final Map<Method,Callable> callableCache;

        static {
            boxing.put("int", "Integer");
            boxing.put("long", "Long");
            boxing.put("double", "Double");
            boxing.put("boolean", "Boolean");
            boxing.put("float", "Float");
            boxing.put("char", "Char");
            boxing.put("short", "Short");
            boxing.put("byte", "Byte");
            unboxingOperation.put("int", "intValue()");
            unboxingOperation.put("long", "longValue()");
            unboxingOperation.put("double", "doubleValue()");
            unboxingOperation.put("boolean", "booleanValue()");
            unboxingOperation.put("float", "floatValue()");
            unboxingOperation.put("char", "charValue()");
            unboxingOperation.put("short", "shortValue()");
            unboxingOperation.put("byte", "byteValue()");

            boxingOperation.put("int","Integer.valueOf");
            boxingOperation.put("long","Long.valueOf");
            boxingOperation.put("double","Double.valueOf");
            boxingOperation.put("boolean", "Boolean.valueOf");
            boxingOperation.put("float", "Float.valueOf");
            boxingOperation.put("char", "Char.valueOf");
            boxingOperation.put("short", "Short.valueOf");
            boxingOperation.put("byte", "Byte.valueOf");
        }

        public SLReflectionMethod(Class clazz, String name) throws NoSuchMethodException {
            Pair<Method[], Class> pair = initMethod(clazz, name);
            this.methods = pair.getLeft();
            this.classStringPair = Pair.create(pair.getRight(), name);
            this.obj = null;
            if (cache.containsKey(this.classStringPair)) {
                this.callableCache = cache.get(this.classStringPair);
            } else {
                this.callableCache = new ConcurrentHashMap<>();
                cache.put(this.classStringPair, callableCache);

            }
        }

        public SLReflectionMethod(SLReflection father, String name) throws NoSuchMethodException {
            this.obj = father.instance;
            Pair<Method[], Class> pair = initMethod(father.clazz, name);
            this.methods = pair.getLeft();
            this.classStringPair = Pair.create(pair.getRight(), name);
            if (cache.containsKey(this.classStringPair)) {
                this.callableCache = cache.get(this.classStringPair);
            } else {
                this.callableCache = new ConcurrentHashMap<>();
                cache.put(this.classStringPair, callableCache);

            }
        }

        private static boolean primitiveEqual(Class clazz, Object obj) {
            if (!clazz.isPrimitive()) return false;
            if (clazz.getName().equals("int") && obj instanceof Integer) return true;
            if (clazz.getName().equals("long") && obj instanceof Long) return true;
            if (clazz.getName().equals("double") && obj instanceof Double) return true;
            if (clazz.getName().equals("boolean") && obj instanceof Boolean) return true;
            if (clazz.getName().equals("float") && obj instanceof Float) return true;
            if (clazz.getName().equals("char") && obj instanceof Character) return true;
            if (clazz.getName().equals("short") && obj instanceof Short) return true;
            if (clazz.getName().equals("byte") && obj instanceof Byte) return true;
            return false;
        }


        private Pair<Method[], Class> initMethod(Class clazz, String name) throws NoSuchMethodException {
            if(blackList.contains(Pair.create(clazz,name))){
                throw new NoSuchMethodException();
            }
            LinkedList<Method> filteredMethods = new LinkedList<>();
            Class finalClazz = clazz;
            if (Modifier.isPublic(clazz.getModifiers())) {
                Pair<Class, String> classStringPair = Pair.create(clazz, name);
                if (cachedMethods.containsKey(classStringPair)) {
                    return Pair.create(cachedMethods.get(classStringPair), clazz);
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(name) && Modifier.isPublic(method.getModifiers())) {
                        filteredMethods.add(method);
                    }
                }
            } else {
                //Class is not public,so finding public interface implemented by class.
                for (Class anInterface : clazz.getInterfaces()) {
                    if (!Modifier.isPublic(anInterface.getModifiers()))
                        continue;
                    Pair<Class, String> classStringPair = Pair.create(anInterface, name);
                    if (cachedMethods.containsKey(classStringPair)) {
                        return Pair.create(cachedMethods.get(classStringPair), anInterface);
                    }
                    Method[] methods = anInterface.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equals(name) && Modifier.isPublic(method.getModifiers())) {
                            filteredMethods.add(method);
                        }
                    }
                    if (filteredMethods.size() > 0) {
                        finalClazz = anInterface;
                        break;
                    }
                }
            }
            if (filteredMethods.size() == 0){
                blackList.add(Pair.create(clazz,name));
                throw new NoSuchMethodException();
            }
            Method[] _methods = new Method[filteredMethods.size()];
            cachedMethods.put(Pair.create(finalClazz, name), filteredMethods.toArray(_methods));
            return Pair.create(filteredMethods.toArray(_methods), finalClazz);
        }

        public Object invoke(Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method foundMethod = null;
            for (Method method : callableCache.keySet()) {
                if (detectMethod(method, args)) {
                    foundMethod = method;
                    break;
                }
            }
            if (foundMethod == null) {
                for (final Method method : methods) {
                    if (detectMethod(method, args)) {
                        foundMethod = method;
                        break;
                    }
                }
            }
            if (foundMethod == null) throw new NoSuchMethodException();
            foundMethod.setAccessible(true);
            Object any;
            try {
                if (this.obj != null) {
                    if (!callableCache.containsKey(foundMethod))
                        any = createCache(foundMethod,args).execute(this.obj, args);
                    else
                        any = callableCache.get(foundMethod).execute(this.obj, args);
                } else {
                    any = foundMethod.invoke(null, args);
                }
            } catch (NotFoundException | CannotCompileException | InstantiationException e) {
                throw new RuntimeException(e);
            }
            return SLLanguage.toLanguageObject(any, foundMethod.getReturnType());
        }

        private static boolean detectMethod(Method method, Object[] args) {
            if (method.getParameterCount() == args.length) {
                Class[] params = method.getParameterTypes();
                boolean _result = true;
                if (params.length != 0) {
                    for (int j = 0; j < params.length; j++) {
                        if (args[j] instanceof SLReflection) {
                            args[j] = ((SLReflection) args[j]).instance;
                        }
                        if (!params[j].isInstance(args[j])
                                && !primitiveEqual(params[j], args[j])
                                && params[j] != Object.class) {
                            _result = false;
                            break;
                        }

                    }
                }
                return _result;
            } else
                return false;
        }

        private Callable createCache(Method foundMethod,Object[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
            StringBuilder arguments = new StringBuilder();
            Class[] paramTypes = foundMethod.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if(paramTypes[i].isPrimitive()){
                    arguments.append("((").append(boxing.get(paramTypes[i].getName())).append(")").append("args[").append(i).append("]).").append(unboxingOperation.get(paramTypes[i].getName())).append(",");
                }else {
                    if(paramTypes[i].getName().equals("java.lang.Object")){
                        arguments.append("(").append(args[i].getClass().getName()).append(")").append("args[").append(i).append("],");
                    } else {
                        arguments.append("(").append(paramTypes[i].getName()).append(")").append("args[").append(i).append("],");
                    }
                }
            }
            if (paramTypes.length > 0)
                arguments.deleteCharAt(arguments.length() - 1);
            String met = "public Object execute(Object instance,Object[] args) { ";
            if(foundMethod.getReturnType().getName().equals("void")){
                met+="((" + this.classStringPair.getLeft().getName() + ")instance)." + foundMethod.getName() + "(" + arguments.toString() + ");" +
                        "return null; }";
            }else {
                met+="return " + (foundMethod.getReturnType().isPrimitive() ? boxingOperation.get(foundMethod.getReturnType().getName()) : "") + "(((" + foundMethod.getDeclaringClass().getName() + ")instance)." + foundMethod.getName() + "(" + arguments.toString() + ")); }";
            }
            ClassPool cp = ClassPool.getDefault();
            cp.appendClassPath(new LoaderClassPath(this.getClass().getClassLoader()));
            CtClass cc = cp.makeClass("CallTarget$"+ foundMethod.getName() + foundMethod.hashCode());
            cc.setInterfaces(new CtClass[]{cp.get("com.github.wooyme.nmsl.runtime.Callable")});
            SignatureAttribute.ClassSignature cs = new SignatureAttribute.ClassSignature(null, null,
                    new SignatureAttribute.ClassType[]{new SignatureAttribute.ClassType("com.github.wooyme.nmsl.runtime.Callable")});

            cc.setGenericSignature(cs.encode());

            CtMethod make = CtNewMethod.make(met, cc);
            cc.addMethod(make);

            Class<Callable> callableClass = (Class<Callable>) cc.toClass(this.getClass().getClassLoader());
            Callable obj = callableClass.newInstance();
            callableCache.put(foundMethod, obj);
            return obj;
        }

        public String getName() {
            return this.classStringPair.getRight();
        }
    }

    private final Class<?> clazz;
    private final Object instance;
    private final InteropLibrary library;
    private final SLFunctionLiteralNode readOp;
    private final SLFunctionLiteralNode writeOp;


    public SLReflection(Object obj) {
        this.instance = obj;
        this.clazz = obj.getClass();
        SLFunctionLiteralNode readNode, writeNode;
        readNode = operationMap.get(this.clazz.getName().replace(".", "_") + "_read");
        writeNode = operationMap.get(this.clazz.getName().replace(".", "_") + "_write");
        if (readNode == null) {
            for (Class<?> anInterface : this.clazz.getInterfaces()) {
                readNode = operationMap.get(anInterface.getName().replace(".", "_") + "_read");
                if (readNode != null) break;
            }
        }
        if (writeNode == null) {
            for (Class<?> anInterface : this.clazz.getInterfaces()) {
                writeNode = operationMap.get(anInterface.getName().replace(".", "_") + "_write");
                if (writeNode != null) break;
            }
        }

        this.readOp = readNode;
        this.writeOp = writeNode;
        library = InteropLibrary.getFactory().createDispatched(3);
    }

    public SLReflection(Class clazz, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.clazz = clazz;
        Constructor[] constructors = clazz.getConstructors();
        Constructor foundConstructor = null;
        for (int i = 0; i < constructors.length; i++) {
            final Constructor constructor = constructors[i];
            if (constructor.getParameterCount() == args.length) {
                Class[] params = constructor.getParameterTypes();
                boolean _result = true;
                for (int j = 0; j < params.length; j++) {
                    if (!params[j].isPrimitive() && params[j] != args[j].getClass() && params[j] != args[j].getClass().getInterfaces()[0] && params[j] != Object.class) {
                        _result = false;
                        break;
                    }
                }
                if (_result) {
                    foundConstructor = constructor;
                    break;
                }
            }
        }
        if (foundConstructor == null) {
            throw new NoSuchMethodException();
        }
        instance = foundConstructor.newInstance(args);
        SLFunctionLiteralNode readNode, writeNode;
        readNode = operationMap.get(this.clazz.getName().replace(".", "_") + "_read");
        writeNode = operationMap.get(this.clazz.getName().replace(".", "_") + "_write");
        if (readNode == null) {
            for (Class<?> anInterface : this.clazz.getInterfaces()) {
                readNode = operationMap.get(anInterface.getName().replace(".", "_") + "_read");
                if (readNode != null) break;
            }
        }
        if (writeNode == null) {
            for (Class<?> anInterface : this.clazz.getInterfaces()) {
                writeNode = operationMap.get(anInterface.getName().replace(".", "_") + "_write");
                if (writeNode != null) break;
            }
        }
        this.readOp = readNode;
        this.writeOp = writeNode;
        library = InteropLibrary.getFactory().createDispatched(3);
    }


    public Object readProperty(String name, VirtualFrame frame) throws UnsupportedMessageException, ArityException, UnsupportedTypeException, NoSuchMethodException {
        try {
            Object result;
            Field field = this.clazz.getDeclaredField(name);
            result = field.get(this.instance);
            return SLLanguage.toLanguageObject(result, field.getType());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            try {
                return new SLReflectionMethod(this, name);
            } catch (NoSuchMethodException ex) {
                try{
                    String namedName = name.substring(0,1).toUpperCase()+name.substring(1);
                    return new SLReflectionMethod(this,"get"+namedName).invoke(new Object[0]);
                }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exx) {
                    if (readOp == null) throw new NoSuchMethodException();
                    return library.execute(readOp.executeGeneric(frame), this, name);
                }
            }
        }
    }

    public void writeProperty(String name, Object value, VirtualFrame frame) throws NoSuchMethodException, UnsupportedMessageException, ArityException, UnsupportedTypeException {
        if (writeOp == null) throw new NoSuchMethodException();
        library.execute(writeOp.executeGeneric(frame), this, name, value);
    }
}
