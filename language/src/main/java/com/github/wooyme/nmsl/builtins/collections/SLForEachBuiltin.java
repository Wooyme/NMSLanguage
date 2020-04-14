package com.github.wooyme.nmsl.builtins.collections;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.builtins.SLBuiltinNode;
import com.github.wooyme.nmsl.runtime.SLContext;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Property;

import java.util.List;
import java.util.Map;

@NodeInfo(shortName = "forEach")
public abstract class SLForEachBuiltin extends SLBuiltinNode {
    @Specialization(limit = "3")
    public Object forEach(Object obj,Object lambda, @CachedContext(SLLanguage.class) SLContext context,@CachedLibrary("lambda") InteropLibrary library){
        if(obj instanceof DynamicObject){
            for (Property property : ((DynamicObject) obj).getShape().getProperties()) {
                try {
                    library.execute(lambda,property.getKey(),property.get((DynamicObject) obj,true));
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(),this);
                }
            }
        }else if(obj instanceof List){
            for (int i = 0; i < ((List) obj).size(); i++) {
                try {
                    library.execute(lambda,((List) obj).get(i),i);
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(),this);
                }
            }
        }else if(obj instanceof Map){
            for (Object entry : ((Map) obj).entrySet()) {
                try {
                    library.execute(lambda,((Map.Entry)entry).getKey(),((Map.Entry)entry).getValue());
                } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                    throw new SLException(e.getMessage(),this);
                }
            }
        }
        return null;
    }
}
