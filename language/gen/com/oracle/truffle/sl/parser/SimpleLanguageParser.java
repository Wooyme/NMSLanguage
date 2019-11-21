// Generated from /home/wooyme/Projects/simplelanguage/language/src/main/java/com/oracle/truffle/sl/parser/SimpleLanguage.g4 by ANTLR 4.7.2
package com.oracle.truffle.sl.parser;

// DO NOT MODIFY - generated from SimpleLanguage.g4 using "mx create-sl-parser"

import java.util.*;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.nodes.SLExpressionNode;
import com.oracle.truffle.sl.nodes.SLRootNode;
import com.oracle.truffle.sl.nodes.SLStatementNode;
import com.oracle.truffle.sl.parser.SLParseError;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, WS=37, COMMENT=38, LINE_COMMENT=39, 
		LOGICAL_LITERAL=40, IDENTIFIER=41, STRING_LITERAL=42, NUMERIC_LITERAL=43;
	public static final int
		RULE_simplelanguage = 0, RULE_function = 1, RULE_lambda = 2, RULE_block = 3, 
		RULE_statement = 4, RULE_while_statement = 5, RULE_if_statement = 6, RULE_return_statement = 7, 
		RULE_expression = 8, RULE_logic_term = 9, RULE_logic_factor = 10, RULE_arithmetic = 11, 
		RULE_term = 12, RULE_single_factor = 13, RULE_factor = 14, RULE_member_expression = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"simplelanguage", "function", "lambda", "block", "statement", "while_statement", 
			"if_statement", "return_statement", "expression", "logic_term", "logic_factor", 
			"arithmetic", "term", "single_factor", "factor", "member_expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'import'", "';'", "'load'", "'fn'", "'('", "','", "')'", 
			"'{'", "':'", "'}'", "'break'", "'continue'", "'debugger'", "'while'", 
			"'if'", "'else'", "'return'", "'||'", "'&&'", "'<'", "'<='", "'>'", "'>='", 
			"'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'!'", "'null'", "'['", "']'", 
			"'.'", "'..'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "WS", "COMMENT", "LINE_COMMENT", "LOGICAL_LITERAL", "IDENTIFIER", 
			"STRING_LITERAL", "NUMERIC_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimpleLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public SimpleLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SimplelanguageContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public Token STRING_LITERAL;
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TerminalNode EOF() { return getToken(SimpleLanguageParser.EOF, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleLanguageParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleLanguageParser.IDENTIFIER, i);
		}
		public List<TerminalNode> STRING_LITERAL() { return getTokens(SimpleLanguageParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(SimpleLanguageParser.STRING_LITERAL, i);
		}
		public SimplelanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simplelanguage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterSimplelanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitSimplelanguage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitSimplelanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimplelanguageContext simplelanguage() throws RecognitionException {
		SimplelanguageContext _localctx = new SimplelanguageContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_simplelanguage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(32);
				((SimplelanguageContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(33);
				match(T__0);
				setState(34);
				match(T__1);
				setState(35);
				((SimplelanguageContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				setState(36);
				match(T__2);
				 factory.doImport(((SimplelanguageContext)_localctx).IDENTIFIER,((SimplelanguageContext)_localctx).STRING_LITERAL); 
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(43);
				match(T__3);
				setState(44);
				((SimplelanguageContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 factory.doLoad(((SimplelanguageContext)_localctx).STRING_LITERAL); 
				setState(46);
				match(T__2);
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			function();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(53);
				function();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public Token s;
		public BlockContext body;
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleLanguageParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleLanguageParser.IDENTIFIER, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__4);
			setState(62);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(63);
			((FunctionContext)_localctx).s = match(T__5);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(65);
				((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(67);
					match(T__6);
					setState(68);
					((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(77);
			match(T__7);
			setState(78);
			((FunctionContext)_localctx).body = block(false);
			 factory.finishFunction(((FunctionContext)_localctx).body.result); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaContext extends ParserRuleContext {
		public SLExpressionNode result;
		public Token s;
		public Token IDENTIFIER;
		public Token m;
		public StatementContext statement;
		public Token e;
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleLanguageParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleLanguageParser.IDENTIFIER, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			((LambdaContext)_localctx).s = match(T__8);
			 HashMap<String,Object> oldEnv = factory.startLambda(((LambdaContext)_localctx).s);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(83);
				((LambdaContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((LambdaContext)_localctx).IDENTIFIER); 
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(85);
					match(T__6);
					setState(86);
					((LambdaContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((LambdaContext)_localctx).IDENTIFIER); 
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(95);
			((LambdaContext)_localctx).m = match(T__9);
			 factory.startBlock();
			                                                  List<SLStatementNode> body = new ArrayList<>(); 
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__17) | (1L << T__27) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(97);
				((LambdaContext)_localctx).statement = statement(false);
				 body.add(((LambdaContext)_localctx).statement.result); 
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			((LambdaContext)_localctx).e = match(T__10);
			 SLStatementNode bodyResult = factory.finishBlock(body, ((LambdaContext)_localctx).m.getStartIndex(), ((LambdaContext)_localctx).e.getStopIndex() - ((LambdaContext)_localctx).m.getStartIndex() + 1);
			                                                  ((LambdaContext)_localctx).result =  factory.finishFunction(bodyResult,oldEnv);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public boolean inLoop;
		public SLStatementNode result;
		public Token s;
		public StatementContext statement;
		public Token e;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block(boolean inLoop) throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 factory.startBlock();
			                                                  List<SLStatementNode> body = new ArrayList<>(); 
			setState(109);
			((BlockContext)_localctx).s = match(T__8);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__17) | (1L << T__27) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(110);
				((BlockContext)_localctx).statement = statement(inLoop);
				 body.add(((BlockContext)_localctx).statement.result); 
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
			((BlockContext)_localctx).e = match(T__10);
			 ((BlockContext)_localctx).result =  factory.finishBlock(body, ((BlockContext)_localctx).s.getStartIndex(), ((BlockContext)_localctx).e.getStopIndex() - ((BlockContext)_localctx).s.getStartIndex() + 1); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public boolean inLoop;
		public SLStatementNode result;
		public While_statementContext while_statement;
		public Token b;
		public Token c;
		public If_statementContext if_statement;
		public Return_statementContext return_statement;
		public ExpressionContext expression;
		public Token d;
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public StatementContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement(boolean inLoop) throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 8, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				{
				setState(121);
				((StatementContext)_localctx).while_statement = while_statement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).while_statement.result; 
				}
				break;
			case T__11:
				{
				setState(124);
				((StatementContext)_localctx).b = match(T__11);
				 if (inLoop) { ((StatementContext)_localctx).result =  factory.createBreak(((StatementContext)_localctx).b); } else { SemErr(((StatementContext)_localctx).b, "break used outside of loop"); } 
				setState(126);
				match(T__2);
				}
				break;
			case T__12:
				{
				setState(127);
				((StatementContext)_localctx).c = match(T__12);
				 if (inLoop) { ((StatementContext)_localctx).result =  factory.createContinue(((StatementContext)_localctx).c); } else { SemErr(((StatementContext)_localctx).c, "continue used outside of loop"); } 
				setState(129);
				match(T__2);
				}
				break;
			case T__15:
				{
				setState(130);
				((StatementContext)_localctx).if_statement = if_statement(inLoop);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).if_statement.result; 
				}
				break;
			case T__17:
				{
				setState(133);
				((StatementContext)_localctx).return_statement = return_statement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).return_statement.result; 
				}
				break;
			case T__5:
			case T__8:
			case T__27:
			case T__30:
			case T__31:
			case T__32:
			case LOGICAL_LITERAL:
			case IDENTIFIER:
			case STRING_LITERAL:
			case NUMERIC_LITERAL:
				{
				setState(136);
				((StatementContext)_localctx).expression = expression();
				setState(137);
				match(T__2);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).expression.result; 
				}
				break;
			case T__13:
				{
				setState(140);
				((StatementContext)_localctx).d = match(T__13);
				 ((StatementContext)_localctx).result =  factory.createDebugger(((StatementContext)_localctx).d); 
				setState(142);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statementContext extends ParserRuleContext {
		public SLStatementNode result;
		public Token w;
		public ExpressionContext condition;
		public BlockContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitWhile_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitWhile_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			((While_statementContext)_localctx).w = match(T__14);
			setState(146);
			match(T__5);
			setState(147);
			((While_statementContext)_localctx).condition = expression();
			setState(148);
			match(T__7);
			setState(149);
			((While_statementContext)_localctx).body = block(true);
			 ((While_statementContext)_localctx).result =  factory.createWhile(((While_statementContext)_localctx).w, ((While_statementContext)_localctx).condition.result, ((While_statementContext)_localctx).body.result); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statementContext extends ParserRuleContext {
		public boolean inLoop;
		public SLStatementNode result;
		public Token i;
		public ExpressionContext condition;
		public BlockContext then;
		public BlockContext block;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public If_statementContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitIf_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement(boolean inLoop) throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 12, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((If_statementContext)_localctx).i = match(T__15);
			setState(153);
			match(T__5);
			setState(154);
			((If_statementContext)_localctx).condition = expression();
			setState(155);
			match(T__7);
			setState(156);
			((If_statementContext)_localctx).then = ((If_statementContext)_localctx).block = block(inLoop);
			 SLStatementNode elsePart = null; 
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(158);
				match(T__16);
				setState(159);
				((If_statementContext)_localctx).block = block(inLoop);
				 elsePart = ((If_statementContext)_localctx).block.result; 
				}
			}

			 ((If_statementContext)_localctx).result =  factory.createIf(((If_statementContext)_localctx).i, ((If_statementContext)_localctx).condition.result, ((If_statementContext)_localctx).then.result, elsePart); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_statementContext extends ParserRuleContext {
		public SLStatementNode result;
		public Token r;
		public ExpressionContext expression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_return_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((Return_statementContext)_localctx).r = match(T__17);
			 SLExpressionNode value = null; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__27) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(168);
				((Return_statementContext)_localctx).expression = expression();
				 value = ((Return_statementContext)_localctx).expression.result; 
				}
			}

			 ((Return_statementContext)_localctx).result =  factory.createReturn(((Return_statementContext)_localctx).r, value); 
			setState(174);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public SLExpressionNode result;
		public Logic_termContext logic_term;
		public Token op;
		public List<Logic_termContext> logic_term() {
			return getRuleContexts(Logic_termContext.class);
		}
		public Logic_termContext logic_term(int i) {
			return getRuleContext(Logic_termContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			((ExpressionContext)_localctx).logic_term = logic_term();
			 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).logic_term.result; 
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(178);
					((ExpressionContext)_localctx).op = match(T__18);
					setState(179);
					((ExpressionContext)_localctx).logic_term = logic_term();
					 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, _localctx.result, ((ExpressionContext)_localctx).logic_term.result); 
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_termContext extends ParserRuleContext {
		public SLExpressionNode result;
		public Logic_factorContext logic_factor;
		public Token op;
		public List<Logic_factorContext> logic_factor() {
			return getRuleContexts(Logic_factorContext.class);
		}
		public Logic_factorContext logic_factor(int i) {
			return getRuleContext(Logic_factorContext.class,i);
		}
		public Logic_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterLogic_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitLogic_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitLogic_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logic_termContext logic_term() throws RecognitionException {
		Logic_termContext _localctx = new Logic_termContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logic_term);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			((Logic_termContext)_localctx).logic_factor = logic_factor();
			 ((Logic_termContext)_localctx).result =  ((Logic_termContext)_localctx).logic_factor.result; 
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(189);
					((Logic_termContext)_localctx).op = match(T__19);
					setState(190);
					((Logic_termContext)_localctx).logic_factor = logic_factor();
					 ((Logic_termContext)_localctx).result =  factory.createBinary(((Logic_termContext)_localctx).op, _localctx.result, ((Logic_termContext)_localctx).logic_factor.result); 
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_factorContext extends ParserRuleContext {
		public SLExpressionNode result;
		public ArithmeticContext arithmetic;
		public Token op;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public Logic_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterLogic_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitLogic_factor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitLogic_factor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logic_factorContext logic_factor() throws RecognitionException {
		Logic_factorContext _localctx = new Logic_factorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logic_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			((Logic_factorContext)_localctx).arithmetic = arithmetic();
			 ((Logic_factorContext)_localctx).result =  ((Logic_factorContext)_localctx).arithmetic.result; 
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(200);
				((Logic_factorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) ) {
					((Logic_factorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(201);
				((Logic_factorContext)_localctx).arithmetic = arithmetic();
				 ((Logic_factorContext)_localctx).result =  factory.createBinary(((Logic_factorContext)_localctx).op, _localctx.result, ((Logic_factorContext)_localctx).arithmetic.result); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticContext extends ParserRuleContext {
		public SLExpressionNode result;
		public TermContext term;
		public Token op;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arithmetic);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			((ArithmeticContext)_localctx).term = term();
			 ((ArithmeticContext)_localctx).result =  ((ArithmeticContext)_localctx).term.result; 
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(208);
					((ArithmeticContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__26 || _la==T__27) ) {
						((ArithmeticContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(209);
					((ArithmeticContext)_localctx).term = term();
					 ((ArithmeticContext)_localctx).result =  factory.createBinary(((ArithmeticContext)_localctx).op, _localctx.result, ((ArithmeticContext)_localctx).term.result); 
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public SLExpressionNode result;
		public Single_factorContext single_factor;
		public Token op;
		public List<Single_factorContext> single_factor() {
			return getRuleContexts(Single_factorContext.class);
		}
		public Single_factorContext single_factor(int i) {
			return getRuleContext(Single_factorContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			((TermContext)_localctx).single_factor = single_factor();
			 ((TermContext)_localctx).result =  ((TermContext)_localctx).single_factor.result; 
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(219);
					((TermContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__28 || _la==T__29) ) {
						((TermContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(220);
					((TermContext)_localctx).single_factor = single_factor();
					 ((TermContext)_localctx).result =  factory.createBinary(((TermContext)_localctx).op, _localctx.result, ((TermContext)_localctx).single_factor.result); 
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_factorContext extends ParserRuleContext {
		public SLExpressionNode result;
		public FactorContext factor;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Single_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterSingle_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitSingle_factor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitSingle_factor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_factorContext single_factor() throws RecognitionException {
		Single_factorContext _localctx = new Single_factorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_single_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__30:
				{
				{
				setState(228);
				match(T__30);
				setState(229);
				((Single_factorContext)_localctx).factor = factor();
				 ((Single_factorContext)_localctx).result =  factory.createNot(((Single_factorContext)_localctx).factor.result); 
				}
				}
				break;
			case T__5:
			case T__8:
			case T__27:
			case T__31:
			case T__32:
			case LOGICAL_LITERAL:
			case IDENTIFIER:
			case STRING_LITERAL:
			case NUMERIC_LITERAL:
				{
				setState(232);
				((Single_factorContext)_localctx).factor = factor();
				((Single_factorContext)_localctx).result =  ((Single_factorContext)_localctx).factor.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public SLExpressionNode result;
		public Token LOGICAL_LITERAL;
		public Token n;
		public Token IDENTIFIER;
		public Member_expressionContext member_expression;
		public Token STRING_LITERAL;
		public Token sb;
		public Token NUMERIC_LITERAL;
		public ExpressionContext expression;
		public Token arr;
		public Token e;
		public LambdaContext lmbd;
		public Token s;
		public ExpressionContext expr;
		public TerminalNode LOGICAL_LITERAL() { return getToken(SimpleLanguageParser.LOGICAL_LITERAL, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleLanguageParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleLanguageParser.IDENTIFIER, i);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SimpleLanguageParser.STRING_LITERAL, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(SimpleLanguageParser.NUMERIC_LITERAL, 0); }
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(237);
				((FactorContext)_localctx).LOGICAL_LITERAL = match(LOGICAL_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createLogicalLiteral(((FactorContext)_localctx).LOGICAL_LITERAL); 
				}
				break;
			case 2:
				{
				setState(239);
				((FactorContext)_localctx).n = match(T__31);
				 ((FactorContext)_localctx).result =  factory.createNull(((FactorContext)_localctx).n); 
				}
				break;
			case 3:
				{
				setState(241);
				((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 SLExpressionNode assignmentName = factory.createStringLiteral(((FactorContext)_localctx).IDENTIFIER, false); 
				setState(247);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(243);
					((FactorContext)_localctx).member_expression = member_expression(null, null, assignmentName,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  factory.createRead(assignmentName); 
					}
					break;
				}
				}
				break;
			case 4:
				{
				setState(249);
				((FactorContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createStringLiteral(((FactorContext)_localctx).STRING_LITERAL, true); 
				}
				break;
			case 5:
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__27) {
					{
					setState(251);
					((FactorContext)_localctx).sb = match(T__27);
					}
				}

				setState(254);
				((FactorContext)_localctx).NUMERIC_LITERAL = match(NUMERIC_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createNumericLiteral(((FactorContext)_localctx).sb,((FactorContext)_localctx).NUMERIC_LITERAL); 
				}
				break;
			case 6:
				{
				setState(256);
				match(T__32);
				 LinkedList<SLExpressionNode> list = new LinkedList<>(); 
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__27) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
					{
					setState(258);
					((FactorContext)_localctx).expression = expression();
					 list.add(((FactorContext)_localctx).expression.result); 
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(260);
						match(T__6);
						setState(261);
						((FactorContext)_localctx).expression = expression();
						 list.add(((FactorContext)_localctx).expression.result); 
						}
						}
						setState(268);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(271);
				((FactorContext)_localctx).arr = match(T__33);
				 ((FactorContext)_localctx).result =  factory.createArray(((FactorContext)_localctx).arr,list); 
				}
				break;
			case 7:
				{
				setState(273);
				match(T__8);
				 HashMap<Token,SLExpressionNode> map = new HashMap<>(); 
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(275);
					((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(276);
					match(T__9);
					setState(277);
					((FactorContext)_localctx).expression = expression();
					 map.put(((FactorContext)_localctx).IDENTIFIER,((FactorContext)_localctx).expression.result); 
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(279);
						match(T__6);
						setState(280);
						((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(281);
						match(T__9);
						setState(282);
						((FactorContext)_localctx).expression = expression();
						 map.put(((FactorContext)_localctx).IDENTIFIER,((FactorContext)_localctx).expression.result); 
						}
						}
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(292);
				((FactorContext)_localctx).e = match(T__10);
				 ((FactorContext)_localctx).result =  factory.createObject(((FactorContext)_localctx).e,map); 
				}
				break;
			case 8:
				{
				setState(294);
				((FactorContext)_localctx).lmbd = lambda();
				 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).lmbd.result; 
				}
				break;
			case 9:
				{
				setState(297);
				((FactorContext)_localctx).s = match(T__5);
				setState(298);
				((FactorContext)_localctx).expr = ((FactorContext)_localctx).expression = expression();
				setState(299);
				((FactorContext)_localctx).e = match(T__7);
				 ((FactorContext)_localctx).result =  factory.createParenExpression(((FactorContext)_localctx).expr.result, ((FactorContext)_localctx).s.getStartIndex(), ((FactorContext)_localctx).e.getStopIndex() - ((FactorContext)_localctx).s.getStartIndex() + 1); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_expressionContext extends ParserRuleContext {
		public SLExpressionNode r;
		public SLExpressionNode assignmentReceiver;
		public SLExpressionNode assignmentName;
		public int status;
		public SLExpressionNode result;
		public ExpressionContext expression;
		public Token e;
		public Token IDENTIFIER;
		public LambdaContext lmbd;
		public ExpressionContext start1;
		public ExpressionContext end;
		public Member_expressionContext member_expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(SimpleLanguageParser.IDENTIFIER, 0); }
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public Member_expressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Member_expressionContext(ParserRuleContext parent, int invokingState, SLExpressionNode r, SLExpressionNode assignmentReceiver, SLExpressionNode assignmentName, int status) {
			super(parent, invokingState);
			this.r = r;
			this.assignmentReceiver = assignmentReceiver;
			this.assignmentName = assignmentName;
			this.status = status;
		}
		@Override public int getRuleIndex() { return RULE_member_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterMember_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitMember_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitMember_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_expressionContext member_expression(SLExpressionNode r,SLExpressionNode assignmentReceiver,SLExpressionNode assignmentName,int status) throws RecognitionException {
		Member_expressionContext _localctx = new Member_expressionContext(_ctx, getState(), r, assignmentReceiver, assignmentName, status);
		enterRule(_localctx, 30, RULE_member_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 SLExpressionNode receiver = r;
			                                                  SLExpressionNode nestedAssignmentName = null; 
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(305);
				match(T__5);
				 List<SLExpressionNode> parameters = new ArrayList<>();
				                                                  if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__27) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
					{
					setState(307);
					((Member_expressionContext)_localctx).expression = expression();
					 parameters.add(((Member_expressionContext)_localctx).expression.result); 
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(309);
						match(T__6);
						setState(310);
						((Member_expressionContext)_localctx).expression = expression();
						 parameters.add(((Member_expressionContext)_localctx).expression.result); 
						}
						}
						setState(317);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(320);
				((Member_expressionContext)_localctx).e = match(T__7);
				 ((Member_expressionContext)_localctx).result =  factory.createCall(receiver,null, parameters, ((Member_expressionContext)_localctx).e); 
				}
				break;
			case 2:
				{
				setState(322);
				match(T__0);
				setState(323);
				((Member_expressionContext)_localctx).expression = expression();

				                                                  if(status==1){
				                                                    ((Member_expressionContext)_localctx).result =  factory.createAppend(assignmentReceiver,((Member_expressionContext)_localctx).expression.result);
				                                                  }else if(status == 2){
				                                                    ((Member_expressionContext)_localctx).result =  factory.createInsert(assignmentReceiver,assignmentName,((Member_expressionContext)_localctx).expression.result);
				                                                  }else if (assignmentName == null) {
				                                                      SemErr((((Member_expressionContext)_localctx).expression!=null?(((Member_expressionContext)_localctx).expression.start):null), "invalid assignment target");
				                                                  } else if (assignmentReceiver == null) {
				                                                      ((Member_expressionContext)_localctx).result =  factory.createAssignment(assignmentName, ((Member_expressionContext)_localctx).expression.result);
				                                                  } else {
				                                                      ((Member_expressionContext)_localctx).result =  factory.createWriteProperty(assignmentReceiver, assignmentName, ((Member_expressionContext)_localctx).expression.result);
				                                                  } 
				}
				break;
			case 3:
				{
				setState(326);
				match(T__34);
				 if (receiver == null) {
				                                                       receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(328);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				}
				break;
			case 4:
				{
				setState(330);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 if (receiver == null) {
				                                                    receiver = factory.createRead(assignmentName);
				                                                  }
				                                                  nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createRead(nestedAssignmentName);
				                                                
				setState(332);
				((Member_expressionContext)_localctx).lmbd = lambda();

				                                                  List<SLExpressionNode> parameters = new ArrayList<>();

				                                                  parameters.add(((Member_expressionContext)_localctx).lmbd.result);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createCall(_localctx.result,receiver, parameters, null);
				                                                
				}
				break;
			case 5:
				{
				setState(335);
				match(T__32);
				setState(336);
				match(T__33);
				 status = 1;
				                                                  if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                  }
				                                                
				}
				break;
			case 6:
				{
				setState(338);
				match(T__32);
				 if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                    } 
				setState(340);
				match(T__35);
				setState(341);
				((Member_expressionContext)_localctx).expression = expression();
				   status = 2;
				                                                    nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result; 
				setState(343);
				match(T__33);
				}
				break;
			case 7:
				{
				setState(345);
				match(T__32);
				 if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(347);
				((Member_expressionContext)_localctx).expression = expression();
				 nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result;
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				setState(349);
				match(T__33);
				}
				break;
			case 8:
				{
				setState(351);
				match(T__32);
				 if (receiver == null) {
				                                                    receiver = factory.createRead(assignmentName);
				                                                 } 
				setState(353);
				((Member_expressionContext)_localctx).start1 = ((Member_expressionContext)_localctx).expression = expression();
				 SLExpressionNode startNode = ((Member_expressionContext)_localctx).start1.result;
				setState(355);
				match(T__9);
				setState(356);
				((Member_expressionContext)_localctx).end = ((Member_expressionContext)_localctx).expression = expression();
				 SLExpressionNode endNode = ((Member_expressionContext)_localctx).end.result; 
				setState(358);
				match(T__33);
				 ((Member_expressionContext)_localctx).result =  factory.createSlice(receiver,startNode,endNode); 
				}
				break;
			case 9:
				{
				setState(361);
				match(T__32);
				setState(362);
				match(T__27);
				setState(363);
				((Member_expressionContext)_localctx).expression = expression();
				 if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                  }
				                                                  ((Member_expressionContext)_localctx).result =  factory.createRemove(receiver,((Member_expressionContext)_localctx).expression.result);
				                                                
				setState(365);
				match(T__33);
				}
				break;
			}
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(369);
				((Member_expressionContext)_localctx).member_expression = member_expression(_localctx.result, receiver, nestedAssignmentName,status);
				 ((Member_expressionContext)_localctx).result =  ((Member_expressionContext)_localctx).member_expression.result; 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0179\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\7\2)\n\2\f\2\16\2,\13\2\3\2\3\2\3\2\3\2\7\2\62\n\2\f\2"+
		"\16\2\65\13\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\5\3N\n\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4[\n\4\f\4\16\4^\13\4\5\4`\n\4\3\4\3\4"+
		"\3\4\3\4\3\4\7\4g\n\4\f\4\16\4j\13\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\7"+
		"\5t\n\5\f\5\16\5w\13\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0092\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a5"+
		"\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\u00ae\n\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\7\n\u00b9\n\n\f\n\16\n\u00bc\13\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00c4\n\13\f\13\16\13\u00c7\13\13\3\f\3\f\3\f\3\f\3\f\3\f\5"+
		"\f\u00cf\n\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d7\n\r\f\r\16\r\u00da\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00e2\n\16\f\16\16\16\u00e5\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00ee\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00fa\n\20\3\20\3\20\3\20\5\20\u00ff"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u010b\n\20"+
		"\f\20\16\20\u010e\13\20\5\20\u0110\n\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0120\n\20\f\20\16\20\u0123"+
		"\13\20\5\20\u0125\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u0131\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u013c\n\21\f\21\16\21\u013f\13\21\5\21\u0141\n\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\5\21\u0172\n\21\3\21\3\21\3\21\5\21\u0177\n\21\3\21\2\2\22\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \2\5\3\2\27\34\3\2\35\36\3\2\37 \2\u0198"+
		"\2*\3\2\2\2\4?\3\2\2\2\6S\3\2\2\2\bn\3\2\2\2\n\u0091\3\2\2\2\f\u0093\3"+
		"\2\2\2\16\u009a\3\2\2\2\20\u00a8\3\2\2\2\22\u00b2\3\2\2\2\24\u00bd\3\2"+
		"\2\2\26\u00c8\3\2\2\2\30\u00d0\3\2\2\2\32\u00db\3\2\2\2\34\u00ed\3\2\2"+
		"\2\36\u0130\3\2\2\2 \u0132\3\2\2\2\"#\7+\2\2#$\7\3\2\2$%\7\4\2\2%&\7,"+
		"\2\2&\'\7\5\2\2\')\b\2\1\2(\"\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\63"+
		"\3\2\2\2,*\3\2\2\2-.\7\6\2\2./\7,\2\2/\60\b\2\1\2\60\62\7\5\2\2\61-\3"+
		"\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3"+
		"\2\2\2\66:\5\4\3\2\679\5\4\3\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2"+
		"\2;=\3\2\2\2<:\3\2\2\2=>\7\2\2\3>\3\3\2\2\2?@\7\7\2\2@A\7+\2\2AB\7\b\2"+
		"\2BM\b\3\1\2CD\7+\2\2DJ\b\3\1\2EF\7\t\2\2FG\7+\2\2GI\b\3\1\2HE\3\2\2\2"+
		"IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2\2MC\3\2\2\2MN\3\2\2\2"+
		"NO\3\2\2\2OP\7\n\2\2PQ\5\b\5\2QR\b\3\1\2R\5\3\2\2\2ST\7\13\2\2T_\b\4\1"+
		"\2UV\7+\2\2V\\\b\4\1\2WX\7\t\2\2XY\7+\2\2Y[\b\4\1\2ZW\3\2\2\2[^\3\2\2"+
		"\2\\Z\3\2\2\2\\]\3\2\2\2]`\3\2\2\2^\\\3\2\2\2_U\3\2\2\2_`\3\2\2\2`a\3"+
		"\2\2\2ab\7\f\2\2bh\b\4\1\2cd\5\n\6\2de\b\4\1\2eg\3\2\2\2fc\3\2\2\2gj\3"+
		"\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\r\2\2lm\b\4\1\2m\7"+
		"\3\2\2\2no\b\5\1\2ou\7\13\2\2pq\5\n\6\2qr\b\5\1\2rt\3\2\2\2sp\3\2\2\2"+
		"tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7\r\2\2yz\b\5\1\2"+
		"z\t\3\2\2\2{|\5\f\7\2|}\b\6\1\2}\u0092\3\2\2\2~\177\7\16\2\2\177\u0080"+
		"\b\6\1\2\u0080\u0092\7\5\2\2\u0081\u0082\7\17\2\2\u0082\u0083\b\6\1\2"+
		"\u0083\u0092\7\5\2\2\u0084\u0085\5\16\b\2\u0085\u0086\b\6\1\2\u0086\u0092"+
		"\3\2\2\2\u0087\u0088\5\20\t\2\u0088\u0089\b\6\1\2\u0089\u0092\3\2\2\2"+
		"\u008a\u008b\5\22\n\2\u008b\u008c\7\5\2\2\u008c\u008d\b\6\1\2\u008d\u0092"+
		"\3\2\2\2\u008e\u008f\7\20\2\2\u008f\u0090\b\6\1\2\u0090\u0092\7\5\2\2"+
		"\u0091{\3\2\2\2\u0091~\3\2\2\2\u0091\u0081\3\2\2\2\u0091\u0084\3\2\2\2"+
		"\u0091\u0087\3\2\2\2\u0091\u008a\3\2\2\2\u0091\u008e\3\2\2\2\u0092\13"+
		"\3\2\2\2\u0093\u0094\7\21\2\2\u0094\u0095\7\b\2\2\u0095\u0096\5\22\n\2"+
		"\u0096\u0097\7\n\2\2\u0097\u0098\5\b\5\2\u0098\u0099\b\7\1\2\u0099\r\3"+
		"\2\2\2\u009a\u009b\7\22\2\2\u009b\u009c\7\b\2\2\u009c\u009d\5\22\n\2\u009d"+
		"\u009e\7\n\2\2\u009e\u009f\5\b\5\2\u009f\u00a4\b\b\1\2\u00a0\u00a1\7\23"+
		"\2\2\u00a1\u00a2\5\b\5\2\u00a2\u00a3\b\b\1\2\u00a3\u00a5\3\2\2\2\u00a4"+
		"\u00a0\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\b"+
		"\1\2\u00a7\17\3\2\2\2\u00a8\u00a9\7\24\2\2\u00a9\u00ad\b\t\1\2\u00aa\u00ab"+
		"\5\22\n\2\u00ab\u00ac\b\t\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00aa\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\b\t\1\2\u00b0\u00b1"+
		"\7\5\2\2\u00b1\21\3\2\2\2\u00b2\u00b3\5\24\13\2\u00b3\u00ba\b\n\1\2\u00b4"+
		"\u00b5\7\25\2\2\u00b5\u00b6\5\24\13\2\u00b6\u00b7\b\n\1\2\u00b7\u00b9"+
		"\3\2\2\2\u00b8\u00b4\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\23\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\5\26\f"+
		"\2\u00be\u00c5\b\13\1\2\u00bf\u00c0\7\26\2\2\u00c0\u00c1\5\26\f\2\u00c1"+
		"\u00c2\b\13\1\2\u00c2\u00c4\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c4\u00c7\3"+
		"\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\25\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\u00c9\5\30\r\2\u00c9\u00ce\b\f\1\2\u00ca\u00cb\t"+
		"\2\2\2\u00cb\u00cc\5\30\r\2\u00cc\u00cd\b\f\1\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u00ca\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\27\3\2\2\2\u00d0\u00d1\5\32\16"+
		"\2\u00d1\u00d8\b\r\1\2\u00d2\u00d3\t\3\2\2\u00d3\u00d4\5\32\16\2\u00d4"+
		"\u00d5\b\r\1\2\u00d5\u00d7\3\2\2\2\u00d6\u00d2\3\2\2\2\u00d7\u00da\3\2"+
		"\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\31\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00db\u00dc\5\34\17\2\u00dc\u00e3\b\16\1\2\u00dd\u00de\t\4\2"+
		"\2\u00de\u00df\5\34\17\2\u00df\u00e0\b\16\1\2\u00e0\u00e2\3\2\2\2\u00e1"+
		"\u00dd\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\33\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7!\2\2\u00e7\u00e8"+
		"\5\36\20\2\u00e8\u00e9\b\17\1\2\u00e9\u00ee\3\2\2\2\u00ea\u00eb\5\36\20"+
		"\2\u00eb\u00ec\b\17\1\2\u00ec\u00ee\3\2\2\2\u00ed\u00e6\3\2\2\2\u00ed"+
		"\u00ea\3\2\2\2\u00ee\35\3\2\2\2\u00ef\u00f0\7*\2\2\u00f0\u0131\b\20\1"+
		"\2\u00f1\u00f2\7\"\2\2\u00f2\u0131\b\20\1\2\u00f3\u00f4\7+\2\2\u00f4\u00f9"+
		"\b\20\1\2\u00f5\u00f6\5 \21\2\u00f6\u00f7\b\20\1\2\u00f7\u00fa\3\2\2\2"+
		"\u00f8\u00fa\b\20\1\2\u00f9\u00f5\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u0131"+
		"\3\2\2\2\u00fb\u00fc\7,\2\2\u00fc\u0131\b\20\1\2\u00fd\u00ff\7\36\2\2"+
		"\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101"+
		"\7-\2\2\u0101\u0131\b\20\1\2\u0102\u0103\7#\2\2\u0103\u010f\b\20\1\2\u0104"+
		"\u0105\5\22\n\2\u0105\u010c\b\20\1\2\u0106\u0107\7\t\2\2\u0107\u0108\5"+
		"\22\n\2\u0108\u0109\b\20\1\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010b"+
		"\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0110\3\2"+
		"\2\2\u010e\u010c\3\2\2\2\u010f\u0104\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\u0111\3\2\2\2\u0111\u0112\7$\2\2\u0112\u0131\b\20\1\2\u0113\u0114\7\13"+
		"\2\2\u0114\u0124\b\20\1\2\u0115\u0116\7+\2\2\u0116\u0117\7\f\2\2\u0117"+
		"\u0118\5\22\n\2\u0118\u0121\b\20\1\2\u0119\u011a\7\t\2\2\u011a\u011b\7"+
		"+\2\2\u011b\u011c\7\f\2\2\u011c\u011d\5\22\n\2\u011d\u011e\b\20\1\2\u011e"+
		"\u0120\3\2\2\2\u011f\u0119\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0124"+
		"\u0115\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\7\r"+
		"\2\2\u0127\u0131\b\20\1\2\u0128\u0129\5\6\4\2\u0129\u012a\b\20\1\2\u012a"+
		"\u0131\3\2\2\2\u012b\u012c\7\b\2\2\u012c\u012d\5\22\n\2\u012d\u012e\7"+
		"\n\2\2\u012e\u012f\b\20\1\2\u012f\u0131\3\2\2\2\u0130\u00ef\3\2\2\2\u0130"+
		"\u00f1\3\2\2\2\u0130\u00f3\3\2\2\2\u0130\u00fb\3\2\2\2\u0130\u00fe\3\2"+
		"\2\2\u0130\u0102\3\2\2\2\u0130\u0113\3\2\2\2\u0130\u0128\3\2\2\2\u0130"+
		"\u012b\3\2\2\2\u0131\37\3\2\2\2\u0132\u0171\b\21\1\2\u0133\u0134\7\b\2"+
		"\2\u0134\u0140\b\21\1\2\u0135\u0136\5\22\n\2\u0136\u013d\b\21\1\2\u0137"+
		"\u0138\7\t\2\2\u0138\u0139\5\22\n\2\u0139\u013a\b\21\1\2\u013a\u013c\3"+
		"\2\2\2\u013b\u0137\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d"+
		"\u013e\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0135\3\2"+
		"\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\7\n\2\2\u0143"+
		"\u0172\b\21\1\2\u0144\u0145\7\3\2\2\u0145\u0146\5\22\n\2\u0146\u0147\b"+
		"\21\1\2\u0147\u0172\3\2\2\2\u0148\u0149\7%\2\2\u0149\u014a\b\21\1\2\u014a"+
		"\u014b\7+\2\2\u014b\u0172\b\21\1\2\u014c\u014d\7+\2\2\u014d\u014e\b\21"+
		"\1\2\u014e\u014f\5\6\4\2\u014f\u0150\b\21\1\2\u0150\u0172\3\2\2\2\u0151"+
		"\u0152\7#\2\2\u0152\u0153\7$\2\2\u0153\u0172\b\21\1\2\u0154\u0155\7#\2"+
		"\2\u0155\u0156\b\21\1\2\u0156\u0157\7&\2\2\u0157\u0158\5\22\n\2\u0158"+
		"\u0159\b\21\1\2\u0159\u015a\7$\2\2\u015a\u0172\3\2\2\2\u015b\u015c\7#"+
		"\2\2\u015c\u015d\b\21\1\2\u015d\u015e\5\22\n\2\u015e\u015f\b\21\1\2\u015f"+
		"\u0160\7$\2\2\u0160\u0172\3\2\2\2\u0161\u0162\7#\2\2\u0162\u0163\b\21"+
		"\1\2\u0163\u0164\5\22\n\2\u0164\u0165\b\21\1\2\u0165\u0166\7\f\2\2\u0166"+
		"\u0167\5\22\n\2\u0167\u0168\b\21\1\2\u0168\u0169\7$\2\2\u0169\u016a\b"+
		"\21\1\2\u016a\u0172\3\2\2\2\u016b\u016c\7#\2\2\u016c\u016d\7\36\2\2\u016d"+
		"\u016e\5\22\n\2\u016e\u016f\b\21\1\2\u016f\u0170\7$\2\2\u0170\u0172\3"+
		"\2\2\2\u0171\u0133\3\2\2\2\u0171\u0144\3\2\2\2\u0171\u0148\3\2\2\2\u0171"+
		"\u014c\3\2\2\2\u0171\u0151\3\2\2\2\u0171\u0154\3\2\2\2\u0171\u015b\3\2"+
		"\2\2\u0171\u0161\3\2\2\2\u0171\u016b\3\2\2\2\u0172\u0176\3\2\2\2\u0173"+
		"\u0174\5 \21\2\u0174\u0175\b\21\1\2\u0175\u0177\3\2\2\2\u0176\u0173\3"+
		"\2\2\2\u0176\u0177\3\2\2\2\u0177!\3\2\2\2\37*\63:JM\\_hu\u0091\u00a4\u00ad"+
		"\u00ba\u00c5\u00ce\u00d8\u00e3\u00ed\u00f9\u00fe\u010c\u010f\u0121\u0124"+
		"\u0130\u013d\u0140\u0171\u0176";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}