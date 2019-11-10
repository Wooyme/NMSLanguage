package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.runtime.SLContext;

@NodeInfo(shortName = "getContext")
public abstract class SLGetContextNode extends SLExpressionNode {
    @Specialization
    @SuppressWarnings("unused")
    public Object getContext(@CachedContext(SLLanguage.class) SLContext context,
                             @Cached("context.getAllocationReporter()") AllocationReporter reporter){
        return context.createObject(reporter);
    }

}
