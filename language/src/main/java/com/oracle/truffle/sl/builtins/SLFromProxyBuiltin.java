package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLNull;
import com.oracle.truffle.sl.runtime.SLProxy;

@NodeInfo(shortName = "fr")
public abstract class SLFromProxyBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object from(Object object){
        Object result = SLProxy.getRaw(object);
        if(result==null) result = SLNull.SINGLETON;
        return result;
    }
}
