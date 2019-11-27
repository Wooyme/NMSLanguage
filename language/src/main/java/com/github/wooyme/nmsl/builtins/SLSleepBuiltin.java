package com.github.wooyme.nmsl.builtins;

import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.runtime.SLBigNumber;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "sleep")
public abstract class SLSleepBuiltin extends SLBuiltinNode {
    @Specialization
    public long sleep(SLBigNumber number, @CachedContext(SLLanguage.class) SLContext context) {
        try {
            Thread.sleep(number.getValue().intValue());
        } catch (InterruptedException e) {
            return 1;
        }
        return 0;
    }
}
