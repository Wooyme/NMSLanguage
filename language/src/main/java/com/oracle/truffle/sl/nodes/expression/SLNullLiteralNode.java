package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.runtime.SLNull;


public abstract class SLNullLiteralNode extends SLExpressionNode {
    @Specialization
    public Object createNull(){
        return SLNull.SINGLETON;
    }
}
