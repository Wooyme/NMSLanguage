package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

import java.util.List;

@NodeInfo(shortName = "insert")
@NodeChild("receiverNode")
@NodeChild("indexNode")
@NodeChild("valueNode")
public abstract class SLInsertNode extends SLExpressionNode {

    @Specialization(limit = "3")
    public Object insert(List receiver, Object index, Object value, @CachedLibrary("index") InteropLibrary numbers){
        try {
            receiver.add(numbers.asInt(index),value);
        } catch (UnsupportedMessageException e) {
            e.printStackTrace();
        }
        return value;
    }
}
