package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.runtime.SLNull;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "to")
public abstract class SLToNode extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object to(Object value,String type,@CachedLibrary("value") InteropLibrary values){
        try {
            switch (type){
                case "int":
                    return values.asInt(value);
                case "long":
                    return values.asLong(value);
                case "double":
                    return values.asDouble(value);
                case "short":
                    return values.asShort(value);
                case "float":
                    return values.asFloat(value);
                case "boolean":
                    return values.asBoolean(value);
                case "byte":
                    return values.asByte(value);
                default:
                    return SLNull.SINGLETON;

            }
        } catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
