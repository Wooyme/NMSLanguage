package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.object.DynamicObject;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        public SLReflectionMethod(Class clazz,String name){
            this.clazz = clazz;
            this.name = name;
            this.obj = null;
        }
        public SLReflectionMethod(SLReflection father,String name){
            this.clazz = father.clazz;
            this.obj = father.instance;
            this.name = name;
        }

        public Object invoke(Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Class[] parameters = new Class[args.length-1];
            for (int i = 0; i < args.length-1; i++) {
                parameters[i] = Object.class;
            }
            Object[] args1 = Arrays.copyOfRange(args,1,args.length);
            Method method = clazz.getDeclaredMethod(this.name,parameters);
            Object any = method.invoke(this.obj,args1);
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
            }else if(any instanceof String || any instanceof List || any instanceof Boolean || any instanceof DynamicObject || any instanceof SLObjectType){
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
        Class[] parameters = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameters[i] = args[i].getClass();
        }
        instance = clazz.getConstructor(parameters).newInstance(args);
    }

    public Object readProperty(String name) throws IllegalAccessException {
        try {
            return this.clazz.getDeclaredField(name).get(this.instance);
        } catch (NoSuchFieldException e) {
            return new SLReflectionMethod(this,name);
        }
    }
}
