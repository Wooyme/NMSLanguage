package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.io.IOException;
import java.io.Writer;

@NodeInfo(shortName = "write")
public abstract class SLWriteBuiltin extends SLBuiltinNode {
    @Specialization
    public long write(Writer writer, String value, @CachedContext(SLLanguage.class) SLContext context) {
        doWrite(writer, value);
        return 0;
    }

    @TruffleBoundary
    private void doWrite(Writer writer, String value){
        try {
            writer.write(value);
        } catch (IOException e) {
            throw new SLException(e.getMessage(), this);
        }
    }

}
