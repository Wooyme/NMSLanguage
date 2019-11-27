package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.runtime.SLProxy;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;

@NodeInfo(shortName = "link")
public abstract class SLInterfaceBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object link(Object obj,String ifName,@CachedLibrary("obj") InteropLibrary values,@Cached IndirectCallNode callNode){
        try {
            return SLProxy.getProxy((DynamicObject) obj,ifName,values,callNode);
        } catch (ClassNotFoundException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
