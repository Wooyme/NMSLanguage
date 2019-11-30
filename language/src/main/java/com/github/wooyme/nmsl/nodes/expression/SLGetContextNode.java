package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;

@NodeInfo(shortName = "getContext")
@NodeChild(value="func",type = SLExpressionNode.class)
public abstract class SLGetContextNode extends SLExpressionNode {
    @Specialization
    @SuppressWarnings("unused")
    public Object getContext(String func,@CachedContext(SLLanguage.class) SLContext context,
                             @Cached("context.getAllocationReporter()") AllocationReporter reporter){
        DynamicObject result = context.createObject(reporter);
        result.define("$func",func);
        return result;
    }

}
