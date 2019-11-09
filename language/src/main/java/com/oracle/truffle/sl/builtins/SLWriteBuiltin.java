package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLContext;

import java.io.BufferedWriter;
import java.io.IOException;

@NodeInfo(shortName = "write")
public abstract class SLWriteBuiltin extends SLBuiltinNode {
    @Specialization
    public long write(Object writer, String value, @CachedContext(SLLanguage.class) SLContext context) {
        doWrite((BufferedWriter) writer, value);
        return 0;
    }

    @TruffleBoundary
    private void doWrite(BufferedWriter writer,String value){
        try {
            writer.write(value);
        } catch (IOException e) {
            throw new SLException(e.getMessage(), this);
        }
    }
}
