package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

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
