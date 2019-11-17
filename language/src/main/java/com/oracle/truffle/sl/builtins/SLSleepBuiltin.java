package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLBigNumber;
import com.oracle.truffle.sl.runtime.SLContext;

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
