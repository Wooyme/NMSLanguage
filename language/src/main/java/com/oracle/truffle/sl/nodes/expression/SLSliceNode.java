package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.runtime.SLBigNumber;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLNull;
import io.netty.buffer.ByteBuf;
import org.graalvm.collections.Pair;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@NodeInfo(shortName = "slice")
@NodeChild("receiverNode")
@NodeChild("start")
@NodeChild("end")
public abstract class SLSliceNode extends SLExpressionNode {
    @Specialization(limit = "3")
    protected Object slice(Object receiver, Object start, Object end,
                           @CachedLibrary("receiver") InteropLibrary library,
                           @CachedLibrary("start") InteropLibrary starts,
                           @CachedLibrary("end") InteropLibrary ends
            , @CachedContext(SLLanguage.class) SLContext context, @Cached("context.getAllocationReporter()") AllocationReporter reporter) {
        Object result = null;
        try {
            if (receiver instanceof String) {
                result = sliceString((String) receiver, starts.asInt(start), ends.asInt(end));
            } else if (receiver instanceof LinkedList) {
                result = sliceLinkedList((LinkedList) receiver, starts.asInt(start), ends.asInt(end));
            } else if (receiver instanceof ByteBuf) {
                result = sliceByteBuf((ByteBuf) receiver, starts.asInt(start), ends.asInt(end));
            }
        } catch (UnsupportedMessageException e) {
            throw new SLException(e.getMessage(), this);
        }

        if (result == null) {
            result = SLNull.SINGLETON;
        }
        return result;
    }

    @CompilerDirectives.TruffleBoundary
    protected String sliceString(String str, int start, int _end) {
        int end = _end < 0 ? str.length() + _end : _end;
        return str.substring(start, end);
    }


    @CompilerDirectives.TruffleBoundary
    protected ByteBuf sliceByteBuf(ByteBuf byteBuf, int start, int _end) {
        int end = _end < 0 ? byteBuf.array().length + _end : _end;
        return byteBuf.slice(start, end);
    }

    protected LinkedList sliceLinkedList(LinkedList list, int start, int _end) {
        int end = _end < 0 ? list.size() + _end : _end;
        return new LinkedList<Object>(list.subList(start, end));
    }


}
