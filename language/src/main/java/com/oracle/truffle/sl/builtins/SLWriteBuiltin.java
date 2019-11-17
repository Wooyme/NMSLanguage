package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLNull;
import com.oracle.truffle.sl.utils.ServerSocketFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

@NodeInfo(shortName = "write")
public abstract class SLWriteBuiltin extends SLBuiltinNode {
    @Specialization
    public long write(Object writer, String value, Object option, @CachedContext(SLLanguage.class) SLContext context) {
        if (writer instanceof Writer){
            doWrite((Writer) writer, value);
        }else if(writer instanceof ChannelHandlerContext){
            if(doSend(value,(ChannelHandlerContext) writer)) return -1;
        }
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

    @TruffleBoundary
    private boolean doSend(String value, ChannelHandlerContext ctx){
        byte[] bytes = value.getBytes();
        ctx.writeAndFlush(Unpooled.buffer(bytes.length).writeBytes(bytes));
        return true;
    }
}
