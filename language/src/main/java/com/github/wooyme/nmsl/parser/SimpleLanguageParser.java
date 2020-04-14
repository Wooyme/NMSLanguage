// Generated from /home/wooyme/Projects/simplelanguage/language/src/main/java/com/github/wooyme/nmsl/parser/SimpleLanguage.g4 by ANTLR 4.7.2
package com.github.wooyme.nmsl.parser;

// DO NOT MODIFY - generated from SimpleLanguage.g4 using "mx create-sl-parser"

import java.util.*;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.RootCallTarget;
import com.github.wooyme.nmsl.SLLanguage;
import com.github.wooyme.nmsl.nodes.SLExpressionNode;
import com.github.wooyme.nmsl.nodes.SLStatementNode;

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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, WS=42, COMMENT=43, LINE_COMMENT=44, LOGICAL_LITERAL=45, 
		IDENTIFIER=46, STRING_LITERAL=47, NUMERIC_LITERAL=48;
	public static final int
		RULE_simplelanguage = 0, RULE_function = 1, RULE_lambda = 2, RULE_label_block = 3, 
		RULE_block = 4, RULE_statement = 5, RULE_while_statement = 6, RULE_if_statement = 7, 
		RULE_return_statement = 8, RULE_expression = 9, RULE_logic_term = 10, 
		RULE_logic_factor = 11, RULE_arithmetic = 12, RULE_term = 13, RULE_single_factor = 14, 
		RULE_factor = 15, RULE_member_expression = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"simplelanguage", "function", "lambda", "label_block", "block", "statement", 
			"while_statement", "if_statement", "return_statement", "expression", 
			"logic_term", "logic_factor", "arithmetic", "term", "single_factor", 
			"factor", "member_expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'import'", "';'", "'load'", "'fn'", "'fn+'", "'('", "','", 
			"')'", "'{'", "'->'", "'}'", "'await'", "'break'", "'continue'", "'debugger'", 
			"'while'", "'if'", "'else'", "'return'", "'||'", "'&&'", "'<'", "'<='", 
			"'>'", "'>='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", 
			"'!'", "'null'", "'['", "']'", "':'", "'.'", "'..'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "WS", "COMMENT", "LINE_COMMENT", 
			"LOGICAL_LITERAL", "IDENTIFIER", "STRING_LITERAL", "NUMERIC_LITERAL"
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
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(34);
				((SimplelanguageContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(35);
				match(T__0);
				setState(36);
				match(T__1);
				setState(37);
				((SimplelanguageContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				setState(38);
				match(T__2);
				 factory.doImport(((SimplelanguageContext)_localctx).IDENTIFIER,((SimplelanguageContext)_localctx).STRING_LITERAL); 
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(45);
				match(T__3);
				setState(46);
				((SimplelanguageContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 factory.doLoad(((SimplelanguageContext)_localctx).STRING_LITERAL); 
				setState(48);
				match(T__2);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			function();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==T__5) {
				{
				{
				setState(55);
				function();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
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
			 boolean isOverwrite; 
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(64);
				match(T__4);
				 isOverwrite = false; 
				}
				break;
			case T__5:
				{
				setState(66);
				match(T__5);
				 isOverwrite = true; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(70);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(71);
			((FunctionContext)_localctx).s = match(T__6);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(73);
				((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(75);
					match(T__7);
					setState(76);
					((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(85);
			match(T__8);
			setState(86);
			((FunctionContext)_localctx).body = block(false);
			 factory.finishFunction(((FunctionContext)_localctx).body.result,isOverwrite); 
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
		public List<Label_blockContext> label_block() {
			return getRuleContexts(Label_blockContext.class);
		}
		public Label_blockContext label_block(int i) {
			return getRuleContext(Label_blockContext.class,i);
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
			setState(89);
			((LambdaContext)_localctx).s = match(T__9);
			 HashMap<String,Object> oldEnv = factory.startLambda(((LambdaContext)_localctx).s);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(91);
				((LambdaContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((LambdaContext)_localctx).IDENTIFIER); 
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(93);
					match(T__7);
					setState(94);
					((LambdaContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((LambdaContext)_localctx).IDENTIFIER); 
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(103);
			((LambdaContext)_localctx).m = match(T__10);
			 factory.startBlock();
			                                                  List<SLStatementNode> body = new ArrayList<>(); 
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__19) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(109);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__6:
				case T__9:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__19:
				case T__29:
				case T__34:
				case T__35:
				case T__36:
				case LOGICAL_LITERAL:
				case IDENTIFIER:
				case STRING_LITERAL:
				case NUMERIC_LITERAL:
					{
					setState(105);
					((LambdaContext)_localctx).statement = statement(false);
					 body.add(((LambdaContext)_localctx).statement.result); 
					}
					break;
				case T__12:
					{
					setState(108);
					label_block(false);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			((LambdaContext)_localctx).e = match(T__11);
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

	public static class Label_blockContext extends ParserRuleContext {
		public boolean inLoop;
		public SLStatementNode result;
		public Token IDENTIFIER;
		public Token m;
		public StatementContext statement;
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
		public List<Label_blockContext> label_block() {
			return getRuleContexts(Label_blockContext.class);
		}
		public Label_blockContext label_block(int i) {
			return getRuleContext(Label_blockContext.class,i);
		}
		public Label_blockContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Label_blockContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_label_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).enterLabel_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLanguageListener ) ((SimpleLanguageListener)listener).exitLabel_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLanguageVisitor ) return ((SimpleLanguageVisitor<? extends T>)visitor).visitLabel_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Label_blockContext label_block(boolean inLoop) throws RecognitionException {
		Label_blockContext _localctx = new Label_blockContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 6, RULE_label_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__12);
			setState(118);
			((Label_blockContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			 HashMap<String,Object> oldEnv = factory.startLabelLambda(((Label_blockContext)_localctx).IDENTIFIER,((Label_blockContext)_localctx).IDENTIFIER);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(120);
				match(T__10);
				setState(121);
				match(T__6);
				setState(122);
				((Label_blockContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((Label_blockContext)_localctx).IDENTIFIER); 
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(124);
					match(T__7);
					setState(125);
					((Label_blockContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((Label_blockContext)_localctx).IDENTIFIER); 
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				match(T__8);
				}
			}

			setState(135);
			((Label_blockContext)_localctx).m = match(T__2);
			 factory.startBlock();
			                                                  List<SLStatementNode> body = new ArrayList<>(); 
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(141);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__6:
					case T__9:
					case T__13:
					case T__14:
					case T__15:
					case T__16:
					case T__17:
					case T__19:
					case T__29:
					case T__34:
					case T__35:
					case T__36:
					case LOGICAL_LITERAL:
					case IDENTIFIER:
					case STRING_LITERAL:
					case NUMERIC_LITERAL:
						{
						setState(137);
						((Label_blockContext)_localctx).statement = statement(inLoop);
						 body.add(((Label_blockContext)_localctx).statement.result); 
						}
						break;
					case T__12:
						{
						setState(140);
						label_block(inLoop);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			 SLStatementNode bodyResult = factory.finishBlock(body, ((Label_blockContext)_localctx).m.getStartIndex(), 1);
			                                                  ((Label_blockContext)_localctx).result =  factory.finishFunction(bodyResult,oldEnv);
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
		public List<Label_blockContext> label_block() {
			return getRuleContexts(Label_blockContext.class);
		}
		public Label_blockContext label_block(int i) {
			return getRuleContext(Label_blockContext.class,i);
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
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 factory.startBlock();
			                                                  List<SLStatementNode> body = new ArrayList<>(); 
			setState(149);
			((BlockContext)_localctx).s = match(T__9);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__19) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(154);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__6:
				case T__9:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__19:
				case T__29:
				case T__34:
				case T__35:
				case T__36:
				case LOGICAL_LITERAL:
				case IDENTIFIER:
				case STRING_LITERAL:
				case NUMERIC_LITERAL:
					{
					setState(150);
					((BlockContext)_localctx).statement = statement(inLoop);
					 body.add(((BlockContext)_localctx).statement.result); 
					}
					break;
				case T__12:
					{
					setState(153);
					label_block(inLoop);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159);
			((BlockContext)_localctx).e = match(T__11);
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
		enterRule(_localctx, 10, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				{
				setState(162);
				((StatementContext)_localctx).while_statement = while_statement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).while_statement.result; 
				}
				break;
			case T__13:
				{
				setState(165);
				((StatementContext)_localctx).b = match(T__13);
				 if (inLoop) { ((StatementContext)_localctx).result =  factory.createBreak(((StatementContext)_localctx).b); } else { SemErr(((StatementContext)_localctx).b, "break used outside of loop"); } 
				setState(167);
				match(T__2);
				}
				break;
			case T__14:
				{
				setState(168);
				((StatementContext)_localctx).c = match(T__14);
				 if (inLoop) { ((StatementContext)_localctx).result =  factory.createContinue(((StatementContext)_localctx).c); } else { SemErr(((StatementContext)_localctx).c, "continue used outside of loop"); } 
				setState(170);
				match(T__2);
				}
				break;
			case T__17:
				{
				setState(171);
				((StatementContext)_localctx).if_statement = if_statement(inLoop);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).if_statement.result; 
				}
				break;
			case T__19:
				{
				setState(174);
				((StatementContext)_localctx).return_statement = return_statement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).return_statement.result; 
				}
				break;
			case T__6:
			case T__9:
			case T__29:
			case T__34:
			case T__35:
			case T__36:
			case LOGICAL_LITERAL:
			case IDENTIFIER:
			case STRING_LITERAL:
			case NUMERIC_LITERAL:
				{
				setState(177);
				((StatementContext)_localctx).expression = expression();
				setState(178);
				match(T__2);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).expression.result; 
				}
				break;
			case T__15:
				{
				setState(181);
				((StatementContext)_localctx).d = match(T__15);
				 ((StatementContext)_localctx).result =  factory.createDebugger(((StatementContext)_localctx).d); 
				setState(183);
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
		enterRule(_localctx, 12, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((While_statementContext)_localctx).w = match(T__16);
			setState(187);
			match(T__6);
			setState(188);
			((While_statementContext)_localctx).condition = expression();
			setState(189);
			match(T__8);
			setState(190);
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
		enterRule(_localctx, 14, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			((If_statementContext)_localctx).i = match(T__17);
			setState(194);
			match(T__6);
			setState(195);
			((If_statementContext)_localctx).condition = expression();
			setState(196);
			match(T__8);
			setState(197);
			((If_statementContext)_localctx).then = ((If_statementContext)_localctx).block = block(inLoop);
			 SLStatementNode elsePart = null; 
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(199);
				match(T__18);
				setState(200);
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
		enterRule(_localctx, 16, RULE_return_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			((Return_statementContext)_localctx).r = match(T__19);
			 SLExpressionNode value = null; 
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__9) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(209);
				((Return_statementContext)_localctx).expression = expression();
				 value = ((Return_statementContext)_localctx).expression.result; 
				}
			}

			 ((Return_statementContext)_localctx).result =  factory.createReturn(((Return_statementContext)_localctx).r, value); 
			setState(215);
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
		enterRule(_localctx, 18, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			((ExpressionContext)_localctx).logic_term = logic_term();
			 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).logic_term.result; 
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(219);
					((ExpressionContext)_localctx).op = match(T__20);
					setState(220);
					((ExpressionContext)_localctx).logic_term = logic_term();
					 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, _localctx.result, ((ExpressionContext)_localctx).logic_term.result); 
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		enterRule(_localctx, 20, RULE_logic_term);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			((Logic_termContext)_localctx).logic_factor = logic_factor();
			 ((Logic_termContext)_localctx).result =  ((Logic_termContext)_localctx).logic_factor.result; 
			setState(236);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(230);
					((Logic_termContext)_localctx).op = match(T__21);
					setState(231);
					((Logic_termContext)_localctx).logic_factor = logic_factor();
					 ((Logic_termContext)_localctx).result =  factory.createBinary(((Logic_termContext)_localctx).op, _localctx.result, ((Logic_termContext)_localctx).logic_factor.result); 
					}
					} 
				}
				setState(238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 22, RULE_logic_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			((Logic_factorContext)_localctx).arithmetic = arithmetic();
			 ((Logic_factorContext)_localctx).result =  ((Logic_factorContext)_localctx).arithmetic.result; 
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(241);
				((Logic_factorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
					((Logic_factorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(242);
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
		enterRule(_localctx, 24, RULE_arithmetic);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			((ArithmeticContext)_localctx).term = term();
			 ((ArithmeticContext)_localctx).result =  ((ArithmeticContext)_localctx).term.result; 
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(249);
					((ArithmeticContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__28 || _la==T__29) ) {
						((ArithmeticContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(250);
					((ArithmeticContext)_localctx).term = term();
					 ((ArithmeticContext)_localctx).result =  factory.createBinary(((ArithmeticContext)_localctx).op, _localctx.result, ((ArithmeticContext)_localctx).term.result); 
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 26, RULE_term);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			((TermContext)_localctx).single_factor = single_factor();
			 ((TermContext)_localctx).result =  ((TermContext)_localctx).single_factor.result; 
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(260);
					((TermContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33))) != 0)) ) {
						((TermContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(261);
					((TermContext)_localctx).single_factor = single_factor();
					 ((TermContext)_localctx).result =  factory.createBinary(((TermContext)_localctx).op, _localctx.result, ((TermContext)_localctx).single_factor.result); 
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		enterRule(_localctx, 28, RULE_single_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__34:
				{
				{
				setState(269);
				match(T__34);
				setState(270);
				((Single_factorContext)_localctx).factor = factor();
				 ((Single_factorContext)_localctx).result =  factory.createNot(((Single_factorContext)_localctx).factor.result); 
				}
				}
				break;
			case T__6:
			case T__9:
			case T__29:
			case T__35:
			case T__36:
			case LOGICAL_LITERAL:
			case IDENTIFIER:
			case STRING_LITERAL:
			case NUMERIC_LITERAL:
				{
				setState(273);
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
		enterRule(_localctx, 30, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(278);
				((FactorContext)_localctx).LOGICAL_LITERAL = match(LOGICAL_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createLogicalLiteral(((FactorContext)_localctx).LOGICAL_LITERAL); 
				}
				break;
			case 2:
				{
				setState(280);
				((FactorContext)_localctx).n = match(T__35);
				 ((FactorContext)_localctx).result =  factory.createNull(((FactorContext)_localctx).n); 
				}
				break;
			case 3:
				{
				setState(282);
				((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 SLExpressionNode assignmentName = factory.createStringLiteral(((FactorContext)_localctx).IDENTIFIER, false); 
				setState(288);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(284);
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
				setState(290);
				((FactorContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 SLExpressionNode receiver = factory.createStringLiteral(((FactorContext)_localctx).STRING_LITERAL, true); 
				setState(296);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(292);
					((FactorContext)_localctx).member_expression = member_expression(receiver,null, null,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  receiver; 
					}
					break;
				}
				}
				break;
			case 5:
				{
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__29) {
					{
					setState(298);
					((FactorContext)_localctx).sb = match(T__29);
					}
				}

				setState(301);
				((FactorContext)_localctx).NUMERIC_LITERAL = match(NUMERIC_LITERAL);
				 SLExpressionNode receiver = factory.createNumericLiteral(((FactorContext)_localctx).sb,((FactorContext)_localctx).NUMERIC_LITERAL); 
				setState(307);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(303);
					((FactorContext)_localctx).member_expression = member_expression(receiver,null, null,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  receiver; 
					}
					break;
				}
				}
				break;
			case 6:
				{
				setState(309);
				match(T__36);
				 LinkedList<SLExpressionNode> list = new LinkedList<>(); 
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__9) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
					{
					setState(311);
					((FactorContext)_localctx).expression = expression();
					 list.add(((FactorContext)_localctx).expression.result); 
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(313);
						match(T__7);
						setState(314);
						((FactorContext)_localctx).expression = expression();
						 list.add(((FactorContext)_localctx).expression.result); 
						}
						}
						setState(321);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(324);
				((FactorContext)_localctx).arr = match(T__37);
				 SLExpressionNode receiver = factory.createArray(((FactorContext)_localctx).arr,list); 
				setState(330);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(326);
					((FactorContext)_localctx).member_expression = member_expression(receiver,null, null,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  receiver; 
					}
					break;
				}
				}
				break;
			case 7:
				{
				setState(332);
				match(T__9);
				 HashMap<Token,SLExpressionNode> map = new HashMap<>(); 
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(334);
					((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(335);
					match(T__38);
					setState(336);
					((FactorContext)_localctx).expression = expression();
					 map.put(((FactorContext)_localctx).IDENTIFIER,((FactorContext)_localctx).expression.result); 
					setState(346);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(338);
						match(T__7);
						setState(339);
						((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(340);
						match(T__38);
						setState(341);
						((FactorContext)_localctx).expression = expression();
						 map.put(((FactorContext)_localctx).IDENTIFIER,((FactorContext)_localctx).expression.result); 
						}
						}
						setState(348);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(351);
				((FactorContext)_localctx).e = match(T__11);
				 SLExpressionNode receiver = factory.createObject(((FactorContext)_localctx).e,map); 
				setState(357);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(353);
					((FactorContext)_localctx).member_expression = member_expression(receiver,null, null,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  receiver; 
					}
					break;
				}
				}
				break;
			case 8:
				{
				setState(359);
				((FactorContext)_localctx).lmbd = lambda();
				 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).lmbd.result; 
				}
				break;
			case 9:
				{
				setState(362);
				((FactorContext)_localctx).s = match(T__6);
				setState(363);
				((FactorContext)_localctx).expr = ((FactorContext)_localctx).expression = expression();
				setState(364);
				((FactorContext)_localctx).e = match(T__8);
				 SLExpressionNode receiver = factory.createParenExpression(((FactorContext)_localctx).expr.result, ((FactorContext)_localctx).s.getStartIndex(), ((FactorContext)_localctx).e.getStopIndex() - ((FactorContext)_localctx).s.getStartIndex() + 1); 
				setState(370);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(366);
					((FactorContext)_localctx).member_expression = member_expression(receiver,null, null,0);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((FactorContext)_localctx).result =  receiver; 
					}
					break;
				}
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
		public ExpressionContext start1;
		public ExpressionContext end;
		public ExpressionContext expr;
		public Member_expressionContext member_expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(SimpleLanguageParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 32, RULE_member_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 SLExpressionNode receiver = r;
			                                                  SLExpressionNode nestedAssignmentName = null; 
			setState(442);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(375);
				match(T__6);
				 List<SLExpressionNode> parameters = new ArrayList<>();
				                                                  if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__9) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << LOGICAL_LITERAL) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
					{
					setState(377);
					((Member_expressionContext)_localctx).expression = expression();
					 parameters.add(((Member_expressionContext)_localctx).expression.result); 
					setState(385);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(379);
						match(T__7);
						setState(380);
						((Member_expressionContext)_localctx).expression = expression();
						 parameters.add(((Member_expressionContext)_localctx).expression.result); 
						}
						}
						setState(387);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(390);
				((Member_expressionContext)_localctx).e = match(T__8);
				 ((Member_expressionContext)_localctx).result =  factory.createCall(receiver,null, parameters, ((Member_expressionContext)_localctx).e); 
				}
				break;
			case 2:
				{
				setState(392);
				match(T__0);
				setState(393);
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
				setState(396);
				match(T__39);
				 if (receiver == null) {
				                                                       receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(398);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				}
				break;
			case 4:
				{
				setState(400);
				match(T__36);
				setState(401);
				match(T__37);
				 status = 1;
				                                                  if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                  }
				                                                
				}
				break;
			case 5:
				{
				setState(403);
				match(T__36);
				 if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                    } 
				setState(405);
				match(T__40);
				setState(406);
				((Member_expressionContext)_localctx).expression = expression();
				   status = 2;
				                                                    nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result; 
				setState(408);
				match(T__37);
				}
				break;
			case 6:
				{
				setState(410);
				match(T__36);
				 if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(412);
				((Member_expressionContext)_localctx).expression = expression();
				 nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result;
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				setState(414);
				match(T__37);
				}
				break;
			case 7:
				{
				setState(416);
				match(T__36);
				 if (receiver == null) {
				                                                    receiver = factory.createRead(assignmentName);
				                                                 } 
				setState(418);
				((Member_expressionContext)_localctx).start1 = ((Member_expressionContext)_localctx).expression = expression();
				 SLExpressionNode startNode = ((Member_expressionContext)_localctx).start1.result;
				setState(420);
				match(T__38);
				setState(421);
				((Member_expressionContext)_localctx).end = ((Member_expressionContext)_localctx).expression = expression();
				 SLExpressionNode endNode = ((Member_expressionContext)_localctx).end.result; 
				setState(423);
				match(T__37);
				 ((Member_expressionContext)_localctx).result =  factory.createSlice(receiver,startNode,endNode); 
				}
				break;
			case 8:
				{
				setState(426);
				match(T__36);
				setState(427);
				match(T__29);
				setState(428);
				((Member_expressionContext)_localctx).expression = expression();
				 if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                  }
				                                                  ((Member_expressionContext)_localctx).result =  factory.createRemove(receiver,((Member_expressionContext)_localctx).expression.result);
				                                                
				setState(430);
				match(T__37);
				}
				break;
			case 9:
				{
				setState(432);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 if (receiver == null) {
				                                                    receiver = factory.createRead(assignmentName);

				                                                  }
				                                                  nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createRead(nestedAssignmentName);
				                                                  List<SLExpressionNode> parameters = new ArrayList<>();
				                                                
				{
				setState(434);
				((Member_expressionContext)_localctx).expr = ((Member_expressionContext)_localctx).expression = expression();
				 parameters.add(((Member_expressionContext)_localctx).expr.result); 
				}

				                                                  ((Member_expressionContext)_localctx).result =  factory.createCall(_localctx.result,receiver, parameters, null);
				                                                
				}
				break;
			case 10:
				{
				setState(439);
				match(T__38);
				setState(440);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				  if (receiver == null) {
				                                                     receiver = factory.createRead(assignmentName);
				                                                  }
				                                                  nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createRead(nestedAssignmentName);
				                                                  List<SLExpressionNode> parameters = new ArrayList<>();
				                                                  ((Member_expressionContext)_localctx).result =  factory.createCall(_localctx.result,receiver, parameters, null);
				                                               
				}
				break;
			}
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(444);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u01c4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\2\3\2\7\2\64"+
		"\n\2\f\2\16\2\67\13\2\3\2\3\2\7\2;\n\2\f\2\16\2>\13\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\5\3G\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3Q\n\3\f\3\16"+
		"\3T\13\3\5\3V\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4c\n\4"+
		"\f\4\16\4f\13\4\5\4h\n\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4p\n\4\f\4\16\4s\13"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0082\n\5\f"+
		"\5\16\5\u0085\13\5\3\5\5\5\u0088\n\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0090"+
		"\n\5\f\5\16\5\u0093\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u009d\n\6"+
		"\f\6\16\6\u00a0\13\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00bb\n\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ce"+
		"\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\5\n\u00d7\n\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\7\13\u00e2\n\13\f\13\16\13\u00e5\13\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\7\f\u00ed\n\f\f\f\16\f\u00f0\13\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u00f8\n\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0100\n\16\f\16\16\16"+
		"\u0103\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u010b\n\17\f\17\16\17"+
		"\u010e\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0117\n\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0123\n\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u012b\n\21\3\21\5\21\u012e\n\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\5\21\u0136\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\7\21\u0140\n\21\f\21\16\21\u0143\13\21\5\21\u0145\n\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u014d\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\7\21\u015b\n\21\f\21\16\21\u015e\13\21\5\21"+
		"\u0160\n\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0168\n\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0175\n\21\5\21\u0177"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0182\n\22\f\22"+
		"\16\22\u0185\13\22\5\22\u0187\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u01bd\n\22\3\22\3\22\3\22\5\22\u01c2\n\22\3\22\2"+
		"\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\5\3\2\31\36\3\2\37 "+
		"\3\2!$\2\u01ef\2,\3\2\2\2\4A\3\2\2\2\6[\3\2\2\2\bw\3\2\2\2\n\u0096\3\2"+
		"\2\2\f\u00ba\3\2\2\2\16\u00bc\3\2\2\2\20\u00c3\3\2\2\2\22\u00d1\3\2\2"+
		"\2\24\u00db\3\2\2\2\26\u00e6\3\2\2\2\30\u00f1\3\2\2\2\32\u00f9\3\2\2\2"+
		"\34\u0104\3\2\2\2\36\u0116\3\2\2\2 \u0176\3\2\2\2\"\u0178\3\2\2\2$%\7"+
		"\60\2\2%&\7\3\2\2&\'\7\4\2\2\'(\7\61\2\2()\7\5\2\2)+\b\2\1\2*$\3\2\2\2"+
		"+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\65\3\2\2\2.,\3\2\2\2/\60\7\6\2\2\60\61"+
		"\7\61\2\2\61\62\b\2\1\2\62\64\7\5\2\2\63/\3\2\2\2\64\67\3\2\2\2\65\63"+
		"\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\28<\5\4\3\29;\5\4\3\2:"+
		"9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\2\2\3"+
		"@\3\3\2\2\2AF\b\3\1\2BC\7\7\2\2CG\b\3\1\2DE\7\b\2\2EG\b\3\1\2FB\3\2\2"+
		"\2FD\3\2\2\2GH\3\2\2\2HI\7\60\2\2IJ\7\t\2\2JU\b\3\1\2KL\7\60\2\2LR\b\3"+
		"\1\2MN\7\n\2\2NO\7\60\2\2OQ\b\3\1\2PM\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3"+
		"\2\2\2SV\3\2\2\2TR\3\2\2\2UK\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\13\2\2XY"+
		"\5\n\6\2YZ\b\3\1\2Z\5\3\2\2\2[\\\7\f\2\2\\g\b\4\1\2]^\7\60\2\2^d\b\4\1"+
		"\2_`\7\n\2\2`a\7\60\2\2ac\b\4\1\2b_\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2"+
		"\2\2eh\3\2\2\2fd\3\2\2\2g]\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7\r\2\2jq\b\4"+
		"\1\2kl\5\f\7\2lm\b\4\1\2mp\3\2\2\2np\5\b\5\2ok\3\2\2\2on\3\2\2\2ps\3\2"+
		"\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\16\2\2uv\b\4\1\2v\7\3"+
		"\2\2\2wx\7\17\2\2xy\7\60\2\2y\u0087\b\5\1\2z{\7\r\2\2{|\7\t\2\2|}\7\60"+
		"\2\2}\u0083\b\5\1\2~\177\7\n\2\2\177\u0080\7\60\2\2\u0080\u0082\b\5\1"+
		"\2\u0081~\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\7\13\2\2"+
		"\u0087z\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7"+
		"\5\2\2\u008a\u0091\b\5\1\2\u008b\u008c\5\f\7\2\u008c\u008d\b\5\1\2\u008d"+
		"\u0090\3\2\2\2\u008e\u0090\5\b\5\2\u008f\u008b\3\2\2\2\u008f\u008e\3\2"+
		"\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\b\5\1\2\u0095\t\3\2\2\2"+
		"\u0096\u0097\b\6\1\2\u0097\u009e\7\f\2\2\u0098\u0099\5\f\7\2\u0099\u009a"+
		"\b\6\1\2\u009a\u009d\3\2\2\2\u009b\u009d\5\b\5\2\u009c\u0098\3\2\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\7\16\2\2\u00a2"+
		"\u00a3\b\6\1\2\u00a3\13\3\2\2\2\u00a4\u00a5\5\16\b\2\u00a5\u00a6\b\7\1"+
		"\2\u00a6\u00bb\3\2\2\2\u00a7\u00a8\7\20\2\2\u00a8\u00a9\b\7\1\2\u00a9"+
		"\u00bb\7\5\2\2\u00aa\u00ab\7\21\2\2\u00ab\u00ac\b\7\1\2\u00ac\u00bb\7"+
		"\5\2\2\u00ad\u00ae\5\20\t\2\u00ae\u00af\b\7\1\2\u00af\u00bb\3\2\2\2\u00b0"+
		"\u00b1\5\22\n\2\u00b1\u00b2\b\7\1\2\u00b2\u00bb\3\2\2\2\u00b3\u00b4\5"+
		"\24\13\2\u00b4\u00b5\7\5\2\2\u00b5\u00b6\b\7\1\2\u00b6\u00bb\3\2\2\2\u00b7"+
		"\u00b8\7\22\2\2\u00b8\u00b9\b\7\1\2\u00b9\u00bb\7\5\2\2\u00ba\u00a4\3"+
		"\2\2\2\u00ba\u00a7\3\2\2\2\u00ba\u00aa\3\2\2\2\u00ba\u00ad\3\2\2\2\u00ba"+
		"\u00b0\3\2\2\2\u00ba\u00b3\3\2\2\2\u00ba\u00b7\3\2\2\2\u00bb\r\3\2\2\2"+
		"\u00bc\u00bd\7\23\2\2\u00bd\u00be\7\t\2\2\u00be\u00bf\5\24\13\2\u00bf"+
		"\u00c0\7\13\2\2\u00c0\u00c1\5\n\6\2\u00c1\u00c2\b\b\1\2\u00c2\17\3\2\2"+
		"\2\u00c3\u00c4\7\24\2\2\u00c4\u00c5\7\t\2\2\u00c5\u00c6\5\24\13\2\u00c6"+
		"\u00c7\7\13\2\2\u00c7\u00c8\5\n\6\2\u00c8\u00cd\b\t\1\2\u00c9\u00ca\7"+
		"\25\2\2\u00ca\u00cb\5\n\6\2\u00cb\u00cc\b\t\1\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00c9\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b\t"+
		"\1\2\u00d0\21\3\2\2\2\u00d1\u00d2\7\26\2\2\u00d2\u00d6\b\n\1\2\u00d3\u00d4"+
		"\5\24\13\2\u00d4\u00d5\b\n\1\2\u00d5\u00d7\3\2\2\2\u00d6\u00d3\3\2\2\2"+
		"\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\n\1\2\u00d9\u00da"+
		"\7\5\2\2\u00da\23\3\2\2\2\u00db\u00dc\5\26\f\2\u00dc\u00e3\b\13\1\2\u00dd"+
		"\u00de\7\27\2\2\u00de\u00df\5\26\f\2\u00df\u00e0\b\13\1\2\u00e0\u00e2"+
		"\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3"+
		"\u00e4\3\2\2\2\u00e4\25\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\5\30\r"+
		"\2\u00e7\u00ee\b\f\1\2\u00e8\u00e9\7\30\2\2\u00e9\u00ea\5\30\r\2\u00ea"+
		"\u00eb\b\f\1\2\u00eb\u00ed\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed\u00f0\3\2"+
		"\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\27\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f1\u00f2\5\32\16\2\u00f2\u00f7\b\r\1\2\u00f3\u00f4\t\2\2\2"+
		"\u00f4\u00f5\5\32\16\2\u00f5\u00f6\b\r\1\2\u00f6\u00f8\3\2\2\2\u00f7\u00f3"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\31\3\2\2\2\u00f9\u00fa\5\34\17\2\u00fa"+
		"\u0101\b\16\1\2\u00fb\u00fc\t\3\2\2\u00fc\u00fd\5\34\17\2\u00fd\u00fe"+
		"\b\16\1\2\u00fe\u0100\3\2\2\2\u00ff\u00fb\3\2\2\2\u0100\u0103\3\2\2\2"+
		"\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\33\3\2\2\2\u0103\u0101"+
		"\3\2\2\2\u0104\u0105\5\36\20\2\u0105\u010c\b\17\1\2\u0106\u0107\t\4\2"+
		"\2\u0107\u0108\5\36\20\2\u0108\u0109\b\17\1\2\u0109\u010b\3\2\2\2\u010a"+
		"\u0106\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010d\35\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\7%\2\2\u0110\u0111"+
		"\5 \21\2\u0111\u0112\b\20\1\2\u0112\u0117\3\2\2\2\u0113\u0114\5 \21\2"+
		"\u0114\u0115\b\20\1\2\u0115\u0117\3\2\2\2\u0116\u010f\3\2\2\2\u0116\u0113"+
		"\3\2\2\2\u0117\37\3\2\2\2\u0118\u0119\7/\2\2\u0119\u0177\b\21\1\2\u011a"+
		"\u011b\7&\2\2\u011b\u0177\b\21\1\2\u011c\u011d\7\60\2\2\u011d\u0122\b"+
		"\21\1\2\u011e\u011f\5\"\22\2\u011f\u0120\b\21\1\2\u0120\u0123\3\2\2\2"+
		"\u0121\u0123\b\21\1\2\u0122\u011e\3\2\2\2\u0122\u0121\3\2\2\2\u0123\u0177"+
		"\3\2\2\2\u0124\u0125\7\61\2\2\u0125\u012a\b\21\1\2\u0126\u0127\5\"\22"+
		"\2\u0127\u0128\b\21\1\2\u0128\u012b\3\2\2\2\u0129\u012b\b\21\1\2\u012a"+
		"\u0126\3\2\2\2\u012a\u0129\3\2\2\2\u012b\u0177\3\2\2\2\u012c\u012e\7 "+
		"\2\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0130\7\62\2\2\u0130\u0135\b\21\1\2\u0131\u0132\5\"\22\2\u0132\u0133"+
		"\b\21\1\2\u0133\u0136\3\2\2\2\u0134\u0136\b\21\1\2\u0135\u0131\3\2\2\2"+
		"\u0135\u0134\3\2\2\2\u0136\u0177\3\2\2\2\u0137\u0138\7\'\2\2\u0138\u0144"+
		"\b\21\1\2\u0139\u013a\5\24\13\2\u013a\u0141\b\21\1\2\u013b\u013c\7\n\2"+
		"\2\u013c\u013d\5\24\13\2\u013d\u013e\b\21\1\2\u013e\u0140\3\2\2\2\u013f"+
		"\u013b\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0139\3\2\2\2\u0144"+
		"\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7(\2\2\u0147\u014c\b\21"+
		"\1\2\u0148\u0149\5\"\22\2\u0149\u014a\b\21\1\2\u014a\u014d\3\2\2\2\u014b"+
		"\u014d\b\21\1\2\u014c\u0148\3\2\2\2\u014c\u014b\3\2\2\2\u014d\u0177\3"+
		"\2\2\2\u014e\u014f\7\f\2\2\u014f\u015f\b\21\1\2\u0150\u0151\7\60\2\2\u0151"+
		"\u0152\7)\2\2\u0152\u0153\5\24\13\2\u0153\u015c\b\21\1\2\u0154\u0155\7"+
		"\n\2\2\u0155\u0156\7\60\2\2\u0156\u0157\7)\2\2\u0157\u0158\5\24\13\2\u0158"+
		"\u0159\b\21\1\2\u0159\u015b\3\2\2\2\u015a\u0154\3\2\2\2\u015b\u015e\3"+
		"\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0160\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015f\u0150\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\3\2"+
		"\2\2\u0161\u0162\7\16\2\2\u0162\u0167\b\21\1\2\u0163\u0164\5\"\22\2\u0164"+
		"\u0165\b\21\1\2\u0165\u0168\3\2\2\2\u0166\u0168\b\21\1\2\u0167\u0163\3"+
		"\2\2\2\u0167\u0166\3\2\2\2\u0168\u0177\3\2\2\2\u0169\u016a\5\6\4\2\u016a"+
		"\u016b\b\21\1\2\u016b\u0177\3\2\2\2\u016c\u016d\7\t\2\2\u016d\u016e\5"+
		"\24\13\2\u016e\u016f\7\13\2\2\u016f\u0174\b\21\1\2\u0170\u0171\5\"\22"+
		"\2\u0171\u0172\b\21\1\2\u0172\u0175\3\2\2\2\u0173\u0175\b\21\1\2\u0174"+
		"\u0170\3\2\2\2\u0174\u0173\3\2\2\2\u0175\u0177\3\2\2\2\u0176\u0118\3\2"+
		"\2\2\u0176\u011a\3\2\2\2\u0176\u011c\3\2\2\2\u0176\u0124\3\2\2\2\u0176"+
		"\u012d\3\2\2\2\u0176\u0137\3\2\2\2\u0176\u014e\3\2\2\2\u0176\u0169\3\2"+
		"\2\2\u0176\u016c\3\2\2\2\u0177!\3\2\2\2\u0178\u01bc\b\22\1\2\u0179\u017a"+
		"\7\t\2\2\u017a\u0186\b\22\1\2\u017b\u017c\5\24\13\2\u017c\u0183\b\22\1"+
		"\2\u017d\u017e\7\n\2\2\u017e\u017f\5\24\13\2\u017f\u0180\b\22\1\2\u0180"+
		"\u0182\3\2\2\2\u0181\u017d\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0183\u0184\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0186"+
		"\u017b\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\7\13"+
		"\2\2\u0189\u01bd\b\22\1\2\u018a\u018b\7\3\2\2\u018b\u018c\5\24\13\2\u018c"+
		"\u018d\b\22\1\2\u018d\u01bd\3\2\2\2\u018e\u018f\7*\2\2\u018f\u0190\b\22"+
		"\1\2\u0190\u0191\7\60\2\2\u0191\u01bd\b\22\1\2\u0192\u0193\7\'\2\2\u0193"+
		"\u0194\7(\2\2\u0194\u01bd\b\22\1\2\u0195\u0196\7\'\2\2\u0196\u0197\b\22"+
		"\1\2\u0197\u0198\7+\2\2\u0198\u0199\5\24\13\2\u0199\u019a\b\22\1\2\u019a"+
		"\u019b\7(\2\2\u019b\u01bd\3\2\2\2\u019c\u019d\7\'\2\2\u019d\u019e\b\22"+
		"\1\2\u019e\u019f\5\24\13\2\u019f\u01a0\b\22\1\2\u01a0\u01a1\7(\2\2\u01a1"+
		"\u01bd\3\2\2\2\u01a2\u01a3\7\'\2\2\u01a3\u01a4\b\22\1\2\u01a4\u01a5\5"+
		"\24\13\2\u01a5\u01a6\b\22\1\2\u01a6\u01a7\7)\2\2\u01a7\u01a8\5\24\13\2"+
		"\u01a8\u01a9\b\22\1\2\u01a9\u01aa\7(\2\2\u01aa\u01ab\b\22\1\2\u01ab\u01bd"+
		"\3\2\2\2\u01ac\u01ad\7\'\2\2\u01ad\u01ae\7 \2\2\u01ae\u01af\5\24\13\2"+
		"\u01af\u01b0\b\22\1\2\u01b0\u01b1\7(\2\2\u01b1\u01bd\3\2\2\2\u01b2\u01b3"+
		"\7\60\2\2\u01b3\u01b4\b\22\1\2\u01b4\u01b5\5\24\13\2\u01b5\u01b6\b\22"+
		"\1\2\u01b6\u01b7\3\2\2\2\u01b7\u01b8\b\22\1\2\u01b8\u01bd\3\2\2\2\u01b9"+
		"\u01ba\7)\2\2\u01ba\u01bb\7\60\2\2\u01bb\u01bd\b\22\1\2\u01bc\u0179\3"+
		"\2\2\2\u01bc\u018a\3\2\2\2\u01bc\u018e\3\2\2\2\u01bc\u0192\3\2\2\2\u01bc"+
		"\u0195\3\2\2\2\u01bc\u019c\3\2\2\2\u01bc\u01a2\3\2\2\2\u01bc\u01ac\3\2"+
		"\2\2\u01bc\u01b2\3\2\2\2\u01bc\u01b9\3\2\2\2\u01bd\u01c1\3\2\2\2\u01be"+
		"\u01bf\5\"\22\2\u01bf\u01c0\b\22\1\2\u01c0\u01c2\3\2\2\2\u01c1\u01be\3"+
		"\2\2\2\u01c1\u01c2\3\2\2\2\u01c2#\3\2\2\2+,\65<FRUdgoq\u0083\u0087\u008f"+
		"\u0091\u009c\u009e\u00ba\u00cd\u00d6\u00e3\u00ee\u00f7\u0101\u010c\u0116"+
		"\u0122\u012a\u012d\u0135\u0141\u0144\u014c\u015c\u015f\u0167\u0174\u0176"+
		"\u0183\u0186\u01bc\u01c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}