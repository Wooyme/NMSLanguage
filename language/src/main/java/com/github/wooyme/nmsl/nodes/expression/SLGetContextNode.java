package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "getContext")
public abstract class SLGetContextNode extends SLExpressionNode {
    @Specialization
    @SuppressWarnings("unused")
    public Object getContext(@CachedContext(SLLanguage.class) SLContext context,
                             @Cached("context.getAllocationReporter()") AllocationReporter reporter){
        return context.createObject(reporter);
    }

}
