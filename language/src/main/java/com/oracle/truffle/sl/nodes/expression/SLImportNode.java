package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

@NodeInfo(shortName = "import")
@NodeChild("nameNode")
public abstract class SLImportNode extends SLExpressionNode {
    @Specialization
    public Object doImport(String name){
        try {
            return this.getClass().getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new SLException(e.getMessage(),this);
        }
    }
}
