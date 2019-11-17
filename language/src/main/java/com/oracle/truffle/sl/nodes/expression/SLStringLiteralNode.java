/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.sl.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.nodes.SLExpressionNode;

import java.util.HashMap;

/**
 * Constant literal for a String value.
 */
@NodeInfo(shortName = "const")
public final class SLStringLiteralNode extends SLExpressionNode {
    private static HashMap<Character,Character> specialCharMap = new HashMap<>();
    static {
        specialCharMap.put('n','\n');
        specialCharMap.put('r','\r');
        specialCharMap.put('t','\t');
        specialCharMap.put('"','"');
    }
    private final String value;

    public SLStringLiteralNode(String value) {
        char[] tmp = new char[2];
        char[] chars = value.toCharArray();
        StringBuilder sb = new StringBuilder();
        int status = 0;
        for (char aChar : chars) {
            if (aChar == '\\') {
                if (status == 0)
                    status = 1;
                else {
                    status = 0;
                    sb.append('\\');
                }
            } else {
                if (status == 1) {
                    if(aChar == 'x'){
                        status = 2;
                    }else if (specialCharMap.containsKey(aChar)) {
                        sb.append(specialCharMap.get(aChar));
                        status = 0;
                    } else {
                        throw new RuntimeException("Failed to parse string");
                    }
                }else if(status == 2){
                    tmp[0] = aChar;
                    status = 3;
                } else if(status == 3){
                    tmp[1] = aChar;
                    int a1 = Character.digit(tmp[0],16);
                    int a2 = Character.digit(tmp[1],16);
                    char x = (char)(a1*16+a2);
                    sb.append(x);
                    status = 0;
                } else {
                    sb.append(aChar);
                }
            }
        }
        this.value = sb.toString();
    }

    @Override
    public String executeGeneric(VirtualFrame frame) {
        return value;
    }
}
