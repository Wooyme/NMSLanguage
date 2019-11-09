package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLException;

@NodeInfo(shortName = "members")
public abstract class SLMembersBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object members(Object obj, @CachedLibrary("obj") InteropLibrary arrays) {
        try {
            return arrays.getMembers(obj);
        } catch (UnsupportedMessageException e) {
            throw new SLException("Element is not a valid member.", this);
        }
    }
}
