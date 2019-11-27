package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.runtime.SLNull;
import com.oracle.truffle.api.dsl.Specialization;


public abstract class SLNullLiteralNode extends SLExpressionNode {
    @Specialization
    public Object createNull(){
        return SLNull.SINGLETON;
    }
}
