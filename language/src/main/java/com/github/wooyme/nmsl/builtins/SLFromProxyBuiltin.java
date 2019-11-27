package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.runtime.SLNull;
import com.github.wooyme.nmsl.runtime.SLProxy;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "fr")
public abstract class SLFromProxyBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object from(Object object){
        Object result = SLProxy.getRaw(object);
        if(result==null) result = SLNull.SINGLETON;
        return result;
    }
}
