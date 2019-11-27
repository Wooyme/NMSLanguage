package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.runtime.SLProxy;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "unlink")
public abstract class SLUnInterfaceBuiltin extends SLBuiltinNode {
    @Specialization
    public long unlink(Object object){
        SLProxy.delete(object);
        return 0;
    }
}
