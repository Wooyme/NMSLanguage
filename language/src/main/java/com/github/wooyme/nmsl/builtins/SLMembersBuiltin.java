package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.runtime.SLNull;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

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
