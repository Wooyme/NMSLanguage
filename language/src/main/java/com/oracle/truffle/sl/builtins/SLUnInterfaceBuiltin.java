package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLProxy;

@NodeInfo(shortName = "unlink")
public abstract class SLUnInterfaceBuiltin extends SLBuiltinNode {
    @Specialization
    public long unlink(Object object){
        SLProxy.delete(object);
        return 0;
    }
}
