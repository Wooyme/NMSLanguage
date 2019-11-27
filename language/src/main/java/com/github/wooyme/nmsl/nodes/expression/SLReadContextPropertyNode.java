package com.github.wooyme.nmsl.nodes.expression;

import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.nodes.util.SLToMemberNode;
import com.github.wooyme.nmsl.runtime.SLUndefinedNameException;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "readContext")
@NodeChild("context")
@NodeChild("name")
public abstract class SLReadContextPropertyNode extends SLExpressionNode {
    @Specialization(limit = "3")
    public Object readContext(TruffleObject context,Object name,
                              @CachedLibrary("context") InteropLibrary objects,
                              @Cached SLToMemberNode asMember){
        try {
            return objects.readMember(context, asMember.execute(name));
        } catch (UnsupportedMessageException | UnknownIdentifierException e) {
            // read was not successful. In SL we only have basic support for errors.
            throw SLUndefinedNameException.undefinedProperty(this, name);
        }

    }
}
