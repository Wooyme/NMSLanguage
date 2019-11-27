package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLException;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "toInt")
public abstract class SLToIntNode extends SLBuiltinNode {
    @Specialization(limit = "3")
    public int toInt(Object num,@CachedLibrary("num") InteropLibrary numbers){
        try {
            return numbers.asInt(num);
        } catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
