package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLLanguage;

import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.*;

public class SLProxy {
    private static Map<Object,DynamicObject> objectMap = new WeakHashMap<>();
    private static Random random = new Random();
    public static Object getProxy(DynamicObject obj,Class clazz,InteropLibrary values,IndirectCallNode callNode){
        ClassLoader classLoader = SLProxy.class.getClassLoader();
        int _hashCode = random.nextInt();
        while(objectMap.keySet().contains(_hashCode)){
            _hashCode = random.nextInt();
        }
        final int hashCode = _hashCode;
        Object object = Proxy.newProxyInstance(classLoader, new Class[]{clazz}, (proxy, method, args) -> {
            String methodName = method.getName();
            if(methodName.equals("hashCode")){
                return hashCode;
            }
            SLFunction function = (SLFunction) values.readMember(obj, methodName);
            Object[] args1;
            int offset;
            if(function.getCtx()!=null){
                if(args!=null){
                    args1 = new Object[args.length + 2];
                }else{
                    args1 = new Object[2];
                }
                offset = 2;
                args1[0] = function.getCtx();
                args1[1] = obj;
            }else{
                if(args!=null) {
                    args1 = new Object[args.length + 1];
                }else{
                    args1 = new Object[1];
                }
                offset = 1;
                args1[0] = obj;
            }
            if(args!=null) {
                for (int i = 0; i < args.length; i++) {
                    if (objectMap.containsKey(args[i])) {
                        args1[offset + i] = objectMap.get(args[i]);
                    } else {
                        args1[offset + i] = SLLanguage.toLanguageObject(args[i],method.getParameterTypes()[i]);
                    }
                }
            }
            Object result = null;
            try {
                result = callNode.call(function.getCallTarget(), args1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        objectMap.put(object,obj);
        return object;
    }
    public static  Object getProxy(DynamicObject obj, String ifName, InteropLibrary values, IndirectCallNode callNode) throws ClassNotFoundException {
        ClassLoader classLoader = SLProxy.class.getClassLoader();
        return getProxy(obj,classLoader.loadClass(ifName),values,callNode);
    }

    public static DynamicObject getRaw(Object object) {
        return objectMap.get(object);
    }

    public static void delete(Object object){
        objectMap.remove(object);
    }
}
