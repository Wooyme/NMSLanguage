package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.nodes.util.SLToMemberNode;
import com.oracle.truffle.sl.runtime.SLUndefinedNameException;

import java.util.LinkedList;

@NodeInfo(shortName = "delete")
@NodeChild("receiverNode")
@NodeChild("nameNode")
public abstract class SLRemovePropertyNode extends SLExpressionNode {

    @Specialization(limit = "3")
    protected Object removeArray(LinkedList receiver, Object index,
                               @CachedLibrary("index") InteropLibrary numbers) {
        try {
            return receiver.remove(numbers.asInt(index));
        } catch (UnsupportedMessageException | IndexOutOfBoundsException e ) {
            // read was not successful. In SL we only have basic support for errors.
            throw SLUndefinedNameException.undefinedProperty(this, index);
        }
    }

    @Specialization(guards = "arrays.hasArrayElements(receiver)", limit = "3")
    protected Object removeArray(Object receiver, Object index,
                               @CachedLibrary("receiver") InteropLibrary arrays,
                               @CachedLibrary("index") InteropLibrary numbers) {
        try {
            Object obj = arrays.readArrayElement(receiver, numbers.asLong(index));
            arrays.removeArrayElement(receiver,numbers.asLong(index));
            return obj;
        } catch (UnsupportedMessageException | InvalidArrayIndexException e) {
            // read was not successful. In SL we only have basic support for errors.
            throw SLUndefinedNameException.undefinedProperty(this, index);
        }
    }

    @Specialization(guards = "objects.hasMembers(receiver)", limit = "3")
    public Object removeArray(Object receiver,Object name,
                         @CachedLibrary("receiver") InteropLibrary objects,
                         @Cached SLToMemberNode asMember){
        try {
            Object obj = objects.readMember(receiver, asMember.execute(name));
            objects.removeMember(receiver,asMember.execute(name));
            return  obj;
        } catch (UnsupportedMessageException | UnknownIdentifierException e) {
            // read was not successful. In SL we only have basic support for errors.
            throw SLUndefinedNameException.undefinedProperty(this, name);
        }
    }
}
