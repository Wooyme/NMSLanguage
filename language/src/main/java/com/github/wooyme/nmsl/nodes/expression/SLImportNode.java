package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.SLException;
import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

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
