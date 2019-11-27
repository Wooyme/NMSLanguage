package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

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
