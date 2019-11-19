package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.object.DynamicObject;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

        private Method[] initMethod(){
            Method[] methods = this.clazz.getDeclaredMethods();
            LinkedList<Method> filteredMethods = new LinkedList<>();
            for (Method method : methods) {
                if(method.getName().equals(this.name)){
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
                if(method.getParameterCount()==args.length-1){
                    Class[] params = method.getParameterTypes();
                    boolean _result = true;
                    for (int j = 0; j < params.length; j++) {
                        if(!params[j].isPrimitive() && params[j]!=(args[j+1].getClass()) && params[j]!=args[j+1].getClass().getInterfaces()[0] && params[j]!=Object.class){
                            _result = false;
                            break;
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
            Object[] args1 = Arrays.copyOfRange(args,1,args.length);
            Object any = foundMethod.invoke(this.obj,args1);
            if(any==null)
                return SLNull.SINGLETON;
            if(any instanceof Integer){
                return new SLBigNumber(new BigDecimal((Integer) any));
            }else if(any instanceof Long){
                return new SLBigNumber(new BigDecimal((Long) any));
            }else if(any instanceof Float){
                return new SLBigNumber(new BigDecimal((Float) any));
            }else if(any instanceof Double){
                return new SLBigNumber(new BigDecimal((Double) any));
            }else if(any instanceof BigInteger){
                return new SLBigNumber(new BigDecimal((BigInteger) any));
            }else if(any instanceof BigDecimal){
                return new SLBigNumber((BigDecimal) any);
            }else if(any instanceof Array){
                return new LinkedList<>(Arrays.asList((Object[]) any));
            }else if( SLProxy.getRaw(any)!=null ||any instanceof String || any instanceof List || any instanceof Boolean || any instanceof DynamicObject || any instanceof SLObjectType){
                return any;
            }else{
                return new SLReflection(any);
            }
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

    public Object readProperty(String name) throws IllegalAccessException {
        try {
            return this.clazz.getDeclaredField(name).get(this.instance);
        } catch (NoSuchFieldException e) {
            return new SLReflectionMethod(this,name);
        }
    }
}
