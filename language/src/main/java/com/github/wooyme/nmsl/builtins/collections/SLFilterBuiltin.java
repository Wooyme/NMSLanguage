package com.github.wooyme.nmsl.builtins.collections;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.builtins.SLBuiltinNode;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
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

@NodeInfo(shortName = "grep")
public abstract class SLFilterBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object filter(Object obj, Object lambda, @CachedContext(SLLanguage.class) SLContext context
            , @Cached("context.getAllocationReporter()") AllocationReporter reporter
            , @CachedLibrary("lambda") InteropLibrary library){
        if (obj instanceof DynamicObject) {
            DynamicObject result = context.createObject(reporter);
            List<Property> properties = ((DynamicObject) obj).getShape().getPropertyList();
            for (Property property : properties) {
                try {
                    Object key = property.getKey();
                    Object value = property.get((DynamicObject) obj, true);
                    if((boolean)library.execute(lambda, key, value)){
                        result.define(key,value);
                    }
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return result;
        } else if (obj instanceof List) {
            List<Object> result = new LinkedList<>();
            for (int i = 0; i < ((List) obj).size(); i++) {
                try {
                    if((boolean)library.execute(lambda, ((List) obj).get(i), i)){
                        result.add(((List) obj).get(i));
                    }
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return result;
        } else if (obj instanceof Map) {
            Object[] entry = ((Map) obj).entrySet().toArray();
            DynamicObject result = context.createObject(reporter);
            for (Object object : entry) {
                try {
                    Object key = ((Map.Entry) object).getKey();
                    Object value = ((Map.Entry) object).getValue();
                    if((boolean)library.execute(lambda, key, value)){
                        result.define(key,value);
                    }
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(), this);
                }
            }
            return result;
        }
        throw new SLException("Not supported type",this);
    }
}
