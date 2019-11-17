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

/*
 * The parser and lexer need to be generated using "mx create-sl-parser".
 */

grammar SimpleLanguage;

@parser::header
{
// DO NOT MODIFY - generated from SimpleLanguage.g4 using "mx create-sl-parser"

import java.util.*;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.nodes.SLRootNode;
import com.oracle.truffle.sl.nodes.SLStatementNode;
import com.oracle.truffle.sl.parser.SLParseError;
}

@lexer::header
{
// DO NOT MODIFY - generated from SimpleLanguage.g4 using "mx create-sl-parser"
}

@parser::members
{
private SLNodeFactory factory;
private Source source;

private static final class BailoutErrorListener extends BaseErrorListener {
    private final Source source;
    BailoutErrorListener(Source source) {
        this.source = source;
    }
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throwParseError(source, line, charPositionInLine, (Token) offendingSymbol, msg);
    }
}

public void SemErr(Token token, String message) {
    assert token != null;
    throwParseError(source, token.getLine(), token.getCharPositionInLine(), token, message);
}

private static void throwParseError(Source source, int line, int charPositionInLine, Token token, String message) {
    int col = charPositionInLine + 1;
    String location = "-- line " + line + " col " + col + ": ";
    int length = token == null ? 1 : Math.max(token.getStopIndex() - token.getStartIndex(), 0);
    throw new SLParseError(source, line, col, length, String.format("Error(s) parsing script:%n" + location + message));
}

public static Map<String, RootCallTarget> parseSL(SLLanguage language, Source source) {
    SimpleLanguageLexer lexer = new SimpleLanguageLexer(CharStreams.fromString(source.getCharacters().toString()));
    SimpleLanguageParser parser = new SimpleLanguageParser(new CommonTokenStream(lexer));
    lexer.removeErrorListeners();
    parser.removeErrorListeners();
    BailoutErrorListener listener = new BailoutErrorListener(source);
    lexer.addErrorListener(listener);
    parser.addErrorListener(listener);
    parser.factory = new SLNodeFactory(language, source);
    parser.source = source;
    parser.simplelanguage();
    return parser.factory.getAllFunctions();
}
}

// parser




simplelanguage
:
(
    IDENTIFIER
    '='
    'import'
    STRING_LITERAL
    ';'                                     { factory.doImport($IDENTIFIER,$STRING_LITERAL); }
)*

function function* EOF
;


function
:
'fn'
IDENTIFIER
s='('
                                                { factory.startFunction($IDENTIFIER, $s);}
(
    IDENTIFIER                                  { factory.addFormalParameter($IDENTIFIER); }
    (
        ','
        IDENTIFIER                              { factory.addFormalParameter($IDENTIFIER); }
    )*
)?
')'
body=block[false]                               { factory.finishFunction($body.result); }
;

lambda returns [SLExpressionNode result]
:
s='{'                                           { HashMap<String,Object> oldEnv = factory.startLambda($s);}
(
    IDENTIFIER                                  { factory.addFormalParameter($IDENTIFIER); }
    (
        ','
        IDENTIFIER                              { factory.addFormalParameter($IDENTIFIER); }
    )*
)?
m=':'                                           { factory.startBlock();
                                                  List<SLStatementNode> body = new ArrayList<>(); }
(
    statement[false]                    { body.add($statement.result); }
)*
e='}'                                           { SLStatementNode bodyResult = factory.finishBlock(body, $m.getStartIndex(), $e.getStopIndex() - $m.getStartIndex() + 1);
                                                  $result = factory.finishFunction(bodyResult,oldEnv);}
;

block [boolean inLoop] returns [SLStatementNode result]
:                                               { factory.startBlock();
                                                  List<SLStatementNode> body = new ArrayList<>(); }
s='{'
(
    statement[inLoop]                   { body.add($statement.result); }
)*
e='}'
                                                { $result = factory.finishBlock(body, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }
;

statement [boolean inLoop] returns [SLStatementNode result]
:
(
    while_statement                    { $result = $while_statement.result; }
|
    b='break'                                   { if (inLoop) { $result = factory.createBreak($b); } else { SemErr($b, "break used outside of loop"); } }
    ';'
|
    c='continue'                                { if (inLoop) { $result = factory.createContinue($c); } else { SemErr($c, "continue used outside of loop"); } }
    ';'
|
    if_statement[inLoop]                { $result = $if_statement.result; }
|
    return_statement                   { $result = $return_statement.result; }
|
    expression ';'                     { $result = $expression.result; }
|
    d='debugger'                                { $result = factory.createDebugger($d); }
    ';'
)
;


while_statement returns [SLStatementNode result]
:
w='while'
'('
condition=expression
')'
body=block[true]                        { $result = factory.createWhile($w, $condition.result, $body.result); }
;


if_statement [boolean inLoop] returns [SLStatementNode result]
:
i='if'
'('
condition=expression
')'
then=block[inLoop]                              { SLStatementNode elsePart = null; }
(
    'else'
    block[inLoop]                               { elsePart = $block.result; }
)?                                              { $result = factory.createIf($i, $condition.result, $then.result, elsePart); }
;


return_statement returns [SLStatementNode result]
:
r='return'                                      { SLExpressionNode value = null; }
(
    expression                                  { value = $expression.result; }
)?                                              { $result = factory.createReturn($r, value); }
';'
;


expression returns [SLExpressionNode result]
:
logic_term                                      { $result = $logic_term.result; }
(
    op='||'
    logic_term                                  { $result = factory.createBinary($op, $result, $logic_term.result); }
)*
;


logic_term returns [SLExpressionNode result]
:
logic_factor                                    { $result = $logic_factor.result; }
(
    op='&&'
    logic_factor                                { $result = factory.createBinary($op, $result, $logic_factor.result); }
)*
;


logic_factor returns [SLExpressionNode result]
:
arithmetic                                      { $result = $arithmetic.result; }
(
    op=('<' | '<=' | '>' | '>=' | '==' | '!=' )
    arithmetic                                  { $result = factory.createBinary($op, $result, $arithmetic.result); }
)?
;


arithmetic returns [SLExpressionNode result]
:
term                                            { $result = $term.result; }
(
    op=('+' | '-')
    term                                      { $result = factory.createBinary($op, $result, $term.result); }
)*
;


term returns [SLExpressionNode result]
:
single_factor                                         { $result = $single_factor.result; }
(
    op=('*' | '/')
    single_factor                                     { $result = factory.createBinary($op, $result, $single_factor.result); }
)*
;

single_factor returns [SLExpressionNode result]
:
(
    (
        '!'
        factor  { $result = factory.createNot($factor.result); }
    )
|
    factor  {$result = $factor.result; }
)
;

factor returns [SLExpressionNode result]
:
(
    LOGICAL_LITERAL                             { $result = factory.createLogicalLiteral($LOGICAL_LITERAL); }
|
    n = 'null'                                  { $result = factory.createNull($n); }
|
    IDENTIFIER                                  { SLExpressionNode assignmentName = factory.createStringLiteral($IDENTIFIER, false); }
    (
        member_expression[null, null, assignmentName,0] { $result = $member_expression.result; }
    |
                                                { $result = factory.createRead(assignmentName); }
    )
|
    STRING_LITERAL                              { $result = factory.createStringLiteral($STRING_LITERAL, true); }
|
    NUMERIC_LITERAL                             { $result = factory.createNumericLiteral($NUMERIC_LITERAL); }
|
    '['                                         { LinkedList<SLExpressionNode> list = new LinkedList<>(); }
    (
        expression                              { list.add($expression.result); }
        (
            ','
            expression                          { list.add($expression.result); }
        )*
    )?
    arr = ']'                                   { $result = factory.createArray($arr,list); }
|
    '{'                                         { HashMap<Token,SLExpressionNode> map = new HashMap<>(); }
    (
        IDENTIFIER
        ':'
        expression                              { map.put($IDENTIFIER,$expression.result); }
        (
            ','
            IDENTIFIER
            ':'
            expression                          { map.put($IDENTIFIER,$expression.result); }
        )*
    )?
    e = '}'                                     { $result = factory.createObject($e,map); }
|

    lmbd=lambda                                 { $result = $lmbd.result; }
|
    s='('
    expr=expression
    e=')'                                       { $result = factory.createParenExpression($expr.result, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }
)
;

member_expression [SLExpressionNode r, SLExpressionNode assignmentReceiver, SLExpressionNode assignmentName,int status] returns [SLExpressionNode result]
:                                               { SLExpressionNode receiver = r;
                                                  SLExpressionNode nestedAssignmentName = null; }
(
    '('                                         { List<SLExpressionNode> parameters = new ArrayList<>();
                                                  if (receiver == null) {
                                                      receiver = factory.createRead(assignmentName);
                                                  } }
    (
        expression                     { parameters.add($expression.result); }
        (
            ','
            expression                 { parameters.add($expression.result); }
        )*
    )?
    e=')'
                                                { $result = factory.createCall(receiver,assignmentReceiver, parameters, $e); }
|
    '='
    expression                                  {
                                                  if(status==1){
                                                    $result = factory.createAppend(assignmentReceiver,$expression.result);
                                                  }else if(status == 2){
                                                    $result = factory.createInsert(assignmentReceiver,assignmentName,$expression.result);
                                                  }else if (assignmentName == null) {
                                                      SemErr($expression.start, "invalid assignment target");
                                                  } else if (assignmentReceiver == null) {
                                                      $result = factory.createAssignment(assignmentName, $expression.result);
                                                  } else {
                                                      $result = factory.createWriteProperty(assignmentReceiver, assignmentName, $expression.result);
                                                  } }
|
    '.'                                         { if (receiver == null) {
                                                       receiver = factory.createRead(assignmentName);
                                                  } }
    IDENTIFIER
                                                { nestedAssignmentName = factory.createStringLiteral($IDENTIFIER, false);
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }
|
    IDENTIFIER                                  { if (receiver == null) {
                                                    receiver = factory.createRead(assignmentName);
                                                  }
                                                  nestedAssignmentName = factory.createStringLiteral($IDENTIFIER, false);
                                                  $result = factory.createRead(nestedAssignmentName);
                                                }
    lmbd=lambda
                                                {
                                                  List<SLExpressionNode> parameters = new ArrayList<>();
                                                  parameters.add($lmbd.result);
                                                  $result = factory.createCall($result,receiver, parameters, null);
                                                }

|
    '[]'                                        { status = 1;
                                                  if (receiver == null) {
                                                     receiver = factory.createRead(assignmentName);
                                                  }
                                                }
|
    '['                                          { if (receiver == null) {
                                                     receiver = factory.createRead(assignmentName);
                                                    } }
    '..'
    expression                                  {   status = 2;
                                                    nestedAssignmentName = $expression.result; }
    ']'
|
    '['                                         { if (receiver == null) {
                                                      receiver = factory.createRead(assignmentName);
                                                  } }
    expression
                                                { nestedAssignmentName = $expression.result;
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }
    ']'
|
    '['                                         { if (receiver == null) {
                                                    receiver = factory.createRead(assignmentName);
                                                 } }
     start1=expression                           { SLExpressionNode startNode = $start1.result;}
     ':'
     end=expression                             { SLExpressionNode endNode = $end.result; }
    ']'                                         { $result = factory.createSlice(receiver,startNode,endNode); }
|
    '['
    '-'
    expression                                  { if (receiver == null) {
                                                     receiver = factory.createRead(assignmentName);
                                                  }
                                                  $result = factory.createRemove(receiver,$expression.result);
                                                }
    ']'
)
(
    member_expression[$result, receiver, nestedAssignmentName,status] { $result = $member_expression.result; }
)?
;

// lexer

WS : [ \t\r\n\u000C]+ -> skip;
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;

fragment LETTER : [A-Z] | [a-z] | '_' | '$';
fragment NON_ZERO_DIGIT : [1-9];
fragment DIGIT : [0-9];
fragment HEX_DIGIT : [0-9] | [a-f] | [A-F];
fragment OCT_DIGIT : [0-7];
fragment BINARY_DIGIT : '0' | '1';
fragment TAB : '\t';
fragment STRING_CHAR : '\\\\' | '\\"'| '\\n' | '\\t' | '\\r'| '\\x' | ~( '\\' | '"' | '\r' | '\n');

LOGICAL_LITERAL : 'true' | 'false';
IDENTIFIER : LETTER (LETTER | DIGIT)*;
STRING_LITERAL : '"' STRING_CHAR* '"';
NUMERIC_LITERAL : '0' | ('-')? NON_ZERO_DIGIT DIGIT* | '0' '.' DIGIT* | ('-')? NON_ZERO_DIGIT DIGIT* '.' DIGIT*;

