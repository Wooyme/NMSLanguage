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

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#simplelanguage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplelanguage(SimpleLanguageParser.SimplelanguageContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(SimpleLanguageParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(SimpleLanguageParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SimpleLanguageParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SimpleLanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(SimpleLanguageParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(SimpleLanguageParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(SimpleLanguageParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SimpleLanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#logic_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_term(SimpleLanguageParser.Logic_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#logic_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_factor(SimpleLanguageParser.Logic_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(SimpleLanguageParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SimpleLanguageParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#single_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_factor(SimpleLanguageParser.Single_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(SimpleLanguageParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLanguageParser#member_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expression(SimpleLanguageParser.Member_expressionContext ctx);
}