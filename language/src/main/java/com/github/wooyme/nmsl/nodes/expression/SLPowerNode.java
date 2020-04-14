package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.nodes.SLBinaryNode;
import com.github.wooyme.nmsl.runtime.SLBigNumber;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.math.BigDecimal;

@NodeInfo(shortName = "^")
public abstract class SLPowerNode extends SLBinaryNode {
    @Specialization(rewriteOn = ArithmeticException.class)
    protected long pow(long left, long right) {
        return (long) Math.pow(left, right);
    }

    @Specialization
    @CompilerDirectives.TruffleBoundary
    protected SLBigNumber pow(SLBigNumber left, SLBigNumber right) {
        return new SLBigNumber(new BigDecimal(Math.pow(left.getValue().doubleValue(),right.getValue().doubleValue())));
    }

    @Fallback
    protected Object typeError(Object left, Object right) {
        throw SLException.typeError(this, left, right);
    }

}
