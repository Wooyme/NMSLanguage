package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

import java.util.LinkedList;
import java.util.List;

@NodeInfo(shortName = "array")
public class SLNewArrayNode extends SLExpressionNode {
    private final List<SLExpressionNode> nodes;
    public SLNewArrayNode(){
        nodes = null;
    }
    public SLNewArrayNode(List<SLExpressionNode> nodes){
        this.nodes = nodes;
    }
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        LinkedList<Object> list = new LinkedList<>();
        if(nodes!=null)
            nodes.forEach(node-> list.add(node.executeGeneric(frame)));
        return list;
    }
}
