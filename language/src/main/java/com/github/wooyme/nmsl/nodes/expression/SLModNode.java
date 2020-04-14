package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.nodes.SLBinaryNode;
import com.github.wooyme.nmsl.runtime.SLBigNumber;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "%")
public abstract class SLModNode extends SLBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long mod(long left, long right) {
        return Math.floorMod(left, right);
    }

    @Specialization
    @CompilerDirectives.TruffleBoundary
    protected SLBigNumber mod(SLBigNumber left, SLBigNumber right) {
        return new SLBigNumber(left.getValue().remainder(right.getValue()));
    }
    @Fallback
    protected Object typeError(Object left, Object right) {
        throw SLException.typeError(this, left, right);
    }
}
