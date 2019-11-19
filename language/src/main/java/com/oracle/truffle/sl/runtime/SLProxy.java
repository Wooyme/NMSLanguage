package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.object.DynamicObject;

import java.lang.reflect.Proxy;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SLProxy {
    private static ConcurrentHashMap<Object,DynamicObject> objectMap = new ConcurrentHashMap<>();
    private static Set<Integer> hashSet = new LinkedHashSet<>();
    private static Random random = new Random();
    public static  Object getProxy(DynamicObject obj, String ifName, InteropLibrary values, IndirectCallNode callNode) throws ClassNotFoundException {
        ClassLoader classLoader = SLProxy.class.getClassLoader();
        int _hashCode = random.nextInt();
        while(hashSet.contains(_hashCode)){
            _hashCode = random.nextInt();
        }
        hashSet.add(_hashCode);
        final int hashCode = _hashCode;
        Object object = Proxy.newProxyInstance(classLoader, new Class[]{classLoader.loadClass(ifName)}, (proxy, method, args) -> {
            String methodName = method.getName();
            if(methodName.equals("hashCode")){
                return hashCode;
            }
            SLFunction function = (SLFunction) values.readMember(obj, methodName);
            Object[] args1;
            int offset;
            if(function.getCtx()!=null){
                args1 = new Object[args.length + 2];
                args1[0] = function.getCtx();
                args1[1] = obj;
                offset = 2;
            }else{
                args1 = new Object[args.length+1];
                args1[0] = obj;
                offset = 1;
            }
            for (int i = 0; i < args.length; i++) {
                if(objectMap.containsKey(args[i])){
                    args1[offset+i] = objectMap.get(args[i]);
                }else{
                    args1[offset+i] = args[i];
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

    public static DynamicObject getRaw(Object object) {
        return objectMap.get(object);
    }

    public static void delete(Object object){
        objectMap.remove(object);
        hashSet.remove(object.hashCode());
    }
}
