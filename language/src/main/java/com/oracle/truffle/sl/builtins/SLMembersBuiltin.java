package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.runtime.SLNull;

import java.util.LinkedList;

@NodeInfo(shortName = "members")
public abstract class SLMembersBuiltin extends SLBuiltinNode {

    @Specialization(limit = "3")
    public Object members(Object obj, @CachedLibrary("obj") InteropLibrary values) {
        try {
            return values.getMembers(obj);
        } catch (UnsupportedMessageException e) {
            return SLNull.SINGLETON;
        }
    }
}
