package com.oracle.truffle.sl.nodes.local;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

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
