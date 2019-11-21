package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.nodes.util.SLToMemberNode;
import com.oracle.truffle.sl.runtime.SLFunction;
import com.oracle.truffle.sl.runtime.SLUndefinedNameException;

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
