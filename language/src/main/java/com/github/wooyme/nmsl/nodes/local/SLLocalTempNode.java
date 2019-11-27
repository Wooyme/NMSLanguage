package com.github.wooyme.nmsl.nodes.local;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public class SLLocalTempNode extends SLExpressionNode {
    private final SLExpressionNode child;
    private Object value = null;
    public SLLocalTempNode(SLExpressionNode child){
        this.child = child;
    }
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if(value==null)
            value = child.executeGeneric(frame);
        return value;
    }
}
