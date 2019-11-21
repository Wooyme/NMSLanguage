package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.sl.SLLanguage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;

public class SLReflection {

    public static class SLReflectionMethod{
        private final Class clazz;
        private final Object obj;
        private final String name;
        private final Method[] methods;
        public SLReflectionMethod(Class clazz,String name){
            this.clazz = clazz;
            this.name = name;
            this.methods = initMethod();
            this.obj = null;
        }
        public SLReflectionMethod(SLReflection father,String name){
            this.clazz = father.clazz;
            this.obj = father.instance;
            this.name = name;
            this.methods = initMethod();
        }

        private static boolean primitiveEqual(Class clazz,Object obj){
            if(!clazz.isPrimitive()) return false;
            if(clazz.getName().equals("int") && obj instanceof Integer) return true;
            if(clazz.getName().equals("long") && obj instanceof Long) return true;
            if(clazz.getName().equals("double") && obj instanceof Double) return true;
            if(clazz.getName().equals("boolean") && obj instanceof Boolean) return true;
            if(clazz.getName().equals("float") && obj instanceof Float) return true;
            if(clazz.getName().equals("char") && obj instanceof Character) return true;
            if(clazz.getName().equals("short") && obj instanceof Short) return true;
            if(clazz.getName().equals("byte") && obj instanceof Byte) return true;
            return false;
        }

        private Method[] initMethod(){
            Method[] methods = this.clazz.getDeclaredMethods();
            LinkedList<Method> filteredMethods = new LinkedList<>();
            for (Method method : methods) {
                if(method.getName().equals(this.name) && Modifier.isPublic(method.getModifiers())){
                    filteredMethods.add(method);
                }
            }
            Method[] _methods = new Method[filteredMethods.size()];
            return filteredMethods.toArray(_methods);
        }

        public Object invoke(Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method foundMethod = null;
            for (int i = 0; i < methods.length; i++) {
                final Method method = methods[i];
                if(method.getParameterCount()==args.length){
                    Class[] params = method.getParameterTypes();
                    boolean _result = true;
                    if(params.length != 0) {
                        for (int j = 0; j < params.length; j++) {
                            if (!params[j].isInstance(args[j])
                                    && !primitiveEqual(params[j],args[j])
                                    && (!(args[j] instanceof SLReflection) || !params[j].isAssignableFrom(((SLReflection) args[j]).clazz))
                                    && params[j] != Object.class) {
                                _result = false;
                                break;
                            }
                            if(args[j] instanceof SLReflection){
                                args[j] = ((SLReflection)args[j]).instance;
                            }
                        }
                    }
                    if(_result) {
                        foundMethod = method;
                        Method _tmp = methods[0];
                        methods[0] = foundMethod; //提高优先级，下次搜索的时候只需要一次循环.
                        methods[i] = _tmp;
                        break;
                    }
                }
            }
            if(foundMethod==null){
                throw new NoSuchMethodException();
            }
            foundMethod.setAccessible(true);
            Object any = foundMethod.invoke(this.obj,args);
            return SLLanguage.toLanguageObject(any);
        }

        public String getName(){
            return this.name;
        }
    }
    private final Class<?> clazz;
    private final Object instance;

    public SLReflection(Object obj){
        this.instance = obj;
        this.clazz = obj.getClass();
    }

    public SLReflection(Class clazz,Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.clazz = clazz;
        Constructor[] constructors = clazz.getConstructors();
        Constructor foundConstructor = null;
        for (int i = 0; i < constructors.length; i++) {
            final Constructor constructor = constructors[i];
            if(constructor.getParameterCount()==args.length){
                Class[] params = constructor.getParameterTypes();
                boolean _result = true;
                for (int j = 0; j < params.length; j++) {
                    if(!params[j].isPrimitive() && params[j]!=args[j].getClass() && params[j]!=args[j].getClass().getInterfaces()[0] && params[j]!=Object.class){
                        _result = false;
                        break;
                    }
                }
                if(_result) {
                    foundConstructor = constructor;
                    break;
                }
            }
        }
        if(foundConstructor==null){
            throw new NoSuchMethodException();
        }
        instance = foundConstructor.newInstance(args);
    }

    public Object readProperty(String name){
        try {
            return this.clazz.getDeclaredField(name).get(this.instance);
        } catch (NoSuchFieldException | IllegalAccessException e ) {
            return new SLReflectionMethod(this,name);
        }
    }
}
