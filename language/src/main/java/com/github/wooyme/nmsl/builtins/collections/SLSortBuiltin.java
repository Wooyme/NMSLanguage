package com.github.wooyme.nmsl.builtins.collections;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.builtins.SLBuiltinNode;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@NodeInfo(shortName = "sort")
public abstract class SLSortBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object sort(Object obj,Object lambda,@CachedLibrary("lambda") InteropLibrary library){
        if (obj instanceof List) {
            Object[] objects = ((List) obj).toArray();
            Arrays.sort(objects, (o1, o2) -> {
                try {
                    Object r1 = library.execute(lambda, o1);
                    Object r2 = library.execute(lambda, o2);
                    if(r1 instanceof Comparable && r2 instanceof Comparable){
                        return ((Comparable) r1).compareTo(r2);
                    }
                }catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e){
                    throw new SLException(e.getMessage(),this);
                }
                throw new SLException("Can not be compared between "+o1+" and "+o2,this);
            });
            return new LinkedList<>(Arrays.asList(objects));
        }
        throw new SLException("Not supported type",this);
    }
}
