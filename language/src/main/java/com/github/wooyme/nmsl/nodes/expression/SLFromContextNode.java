package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.runtime.SLUndefinedNameException;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "fromContext")
@NodeChild(value = "old", type = SLExpressionNode.class)
public abstract class SLFromContextNode extends SLExpressionNode {
    @Specialization(guards = "!values.isNull(obj)", limit = "3")
    public Object fromContext(Object obj, @CachedLibrary("obj") InteropLibrary values) {
        try {
            return values.instantiate(obj);
        } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
            throw SLUndefinedNameException.undefinedFunction(this, obj);
        }
    }
}
