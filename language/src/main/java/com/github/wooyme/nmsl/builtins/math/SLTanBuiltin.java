package com.github.wooyme.nmsl.builtins.math;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.builtins.SLBuiltinNode;
import com.github.wooyme.nmsl.runtime.SLBigNumber;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.math.BigDecimal;

@NodeInfo(shortName = "tan")
public abstract class SLTanBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public SLBigNumber close(Object value, @CachedLibrary("value") InteropLibrary values){
        try {
            return new SLBigNumber(BigDecimal.valueOf(Math.tan(values.asDouble(value))));
        }  catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
