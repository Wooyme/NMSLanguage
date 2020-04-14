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
                    if(value instanceof String){
                        return Integer.valueOf((String) value);
                    }
                    return values.asInt(value);
                case "long":
                    if(value instanceof String){
                        return Long.valueOf((String) value);
                    }
                    return values.asLong(value);
                case "double":
                    if(value instanceof String){
                        return Double.valueOf((String) value);
                    }
                    return values.asDouble(value);
                case "short":
                    if(value instanceof String){
                        return Short.valueOf((String) value);
                    }
                    return values.asShort(value);
                case "float":
                    if(value instanceof String){
                        return Float.valueOf((String) value);
                    }
                    return values.asFloat(value);
                case "boolean":
                    if(value instanceof String){
                        return Boolean.valueOf((String) value);
                    }
                    return values.asBoolean(value);
                case "byte":
                    if(value instanceof String){
                        return Byte.parseByte((String)value);
                    }
                    return values.asByte(value);
                default:
                    throw new SLException("Wrong type "+type,this);

            }
        } catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
