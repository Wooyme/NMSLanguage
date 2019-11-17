package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.runtime.SLContext;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;

public class SLNewObjectNode extends SLExpressionNode {
    private final HashMap<Token,SLExpressionNode> map;
    private TruffleLanguage.ContextReference<SLContext> contextRef = null;
    private AllocationReporter reporter = null;
    public SLNewObjectNode(HashMap<Token,SLExpressionNode> map){
        this.map = map;
    }
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if(contextRef==null)
            contextRef = lookupContextReference(SLLanguage.class);
        if(reporter==null)
            reporter = contextRef.get().getAllocationReporter();
        DynamicObject obj = contextRef.get().createObject(reporter);
        map.forEach((k,v)->{
            obj.define(k.getText(),v.executeGeneric(frame));
        });
        return obj;
    }
}
