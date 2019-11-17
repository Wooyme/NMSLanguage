package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLBigNumber;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLNull;
import com.oracle.truffle.sl.utils.ServerSocketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

@NodeInfo(shortName = "read")
public abstract class SLReadBuiltin extends SLBuiltinNode {
    @Specialization
    public Object read(Object reader, SLBigNumber number, @CachedContext(SLLanguage.class) SLContext context , @Cached("context.getAllocationReporter()") AllocationReporter reporter) {
        Object result = null;
        if(reader!=null) {
            if (reader instanceof BufferedReader)
                result = doRead((BufferedReader) reader, number.getValue().intValue());
        }else
                result = doRead(context.getInput(),number.getValue().intValue());
        if (result == null) {
            /*
             * We do not have a sophisticated end of file handling, so returning an empty string is
             * a reasonable alternative. Note that the Java null value should never be used, since
             * it can interfere with the specialization logic in generated source code.
             */
            result = SLNull.SINGLETON;
        }
        return result;
    }

    @TruffleBoundary
    private String doRead(BufferedReader reader,int size){
        try {
            char[] bytes = new char[size];
            if(reader.read(bytes)==-1){
                return null;
            }
            return String.valueOf(bytes);
        } catch (IOException e) {
            throw new SLException(e.getMessage(), this);
        }
    }

}
