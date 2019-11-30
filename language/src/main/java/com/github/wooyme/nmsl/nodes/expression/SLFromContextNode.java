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
import com.oracle.truffle.api.object.DynamicObject;

@NodeInfo(shortName = "fromContext")
@NodeChild(value = "old", type = SLExpressionNode.class)
@NodeChild(value = "func",type = SLExpressionNode.class)
public abstract class SLFromContextNode extends SLExpressionNode {
    @Specialization(guards = "!values.isNull(obj)", limit = "3")
    public Object fromContext(Object obj,String func, @CachedLibrary("obj") InteropLibrary values) {
        try {
            DynamicObject result;
            if(!func.startsWith("@"))
                result = (DynamicObject) values.instantiate(obj);
            else
                result = (DynamicObject) obj;
            result.set("$func",func);
            return result;
        } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
            throw SLUndefinedNameException.undefinedFunction(this, obj);
        }
    }
}
