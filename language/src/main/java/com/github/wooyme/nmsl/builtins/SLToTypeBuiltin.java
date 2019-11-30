package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLException;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "to")
public abstract class SLToTypeBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object toType(Object num, String type, @CachedLibrary("num") InteropLibrary numbers){
        try {
            switch (type){
                case "int":
                    return numbers.asInt(num);
                case "long":
                    return numbers.asLong(num);
                case "short":
                    return numbers.asShort(num);
                case "float":
                    return numbers.asFloat(num);
                case "double":
                    return numbers.asDouble(num);
                default:
                    throw new SLException("Cannot cast decimal to "+type,this);
            }
        } catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
