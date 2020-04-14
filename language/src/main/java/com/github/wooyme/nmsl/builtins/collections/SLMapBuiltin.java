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
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Property;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@NodeInfo(shortName = "map")
public abstract class SLMapBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object map(Object obj, Object lambda, @CachedLibrary("lambda") InteropLibrary library) {
        if (obj instanceof DynamicObject) {
            Object[] results = new Object[((DynamicObject) obj).size()];
            List<Property> properties = ((DynamicObject) obj).getShape().getPropertyList();
            for (int i = 0; i < properties.size(); i++) {
                try {
                    results[i] = library.execute(lambda, properties.get(i).getKey(), properties.get(i).get((DynamicObject) obj, true));
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return new LinkedList<>(Arrays.asList(results));
        } else if (obj instanceof List) {
            Object[] results = new Object[((List) obj).size()];
            for (int i = 0; i < ((List) obj).size(); i++) {
                try {
                    results[i] = library.execute(lambda, ((List) obj).get(i), i);
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return new LinkedList<>(Arrays.asList(results));
        } else if (obj instanceof Map) {
            Object[] entry = ((Map) obj).entrySet().toArray();
            Object[] results = new Object[entry.length];
            for (int i = 0; i < entry.length; i++) {
                try {
                    results[i] = library.execute(lambda, ((Map.Entry) entry[i]).getValue(), ((Map.Entry) entry[i]).getValue());
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return new LinkedList<>(Arrays.asList(results));
        }
        throw new SLException("Not supported type",this);
    }
}
