package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

@NodeInfo(shortName = "const")
public final class SLLogicalLiteralNode extends SLExpressionNode {
    private final Boolean value;
    public SLLogicalLiteralNode(boolean value){
        this.value = value;
    }

    @Override
    public Boolean executeGeneric(VirtualFrame frame) {
        return value;
    }
}
