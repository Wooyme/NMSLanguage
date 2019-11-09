package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLContext;

import java.io.Closeable;
import java.io.IOException;

@NodeInfo(shortName = "close")
public abstract class SLCloseBuiltin extends SLBuiltinNode {
    @Specialization
    public long close(Closeable closeable,@CachedContext(SLLanguage.class) SLContext context){
        try {
            closeable.close();
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }
}
