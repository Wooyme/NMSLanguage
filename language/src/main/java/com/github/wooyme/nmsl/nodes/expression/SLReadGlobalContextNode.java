package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.util.HashMap;

@NodeInfo(shortName = "global")
public class SLReadGlobalContextNode extends SLExpressionNode {
    private static final HashMap<String, Class> map = new HashMap<>();

    public static void addGlobal(String name,String clazzName) throws ClassNotFoundException {
        Class clazz = Class.forName(clazzName);
        map.put(name,clazz);
    }


    public static boolean containsName(String name){
        return map.containsKey(name);
    }
    private final String name;
    public SLReadGlobalContextNode(String name){
        this.name = name;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return map.get(this.name);
    }
}
