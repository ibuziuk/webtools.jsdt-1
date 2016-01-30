/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 		 Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.wst.jsdt.unittests.esprima;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.wst.jsdt.core.dom.ASTNode;
import org.eclipse.wst.jsdt.core.dom.ArrayAccess;
import org.eclipse.wst.jsdt.core.dom.ArrayInitializer;
import org.eclipse.wst.jsdt.core.dom.ArrowFunctionExpression;
import org.eclipse.wst.jsdt.core.dom.Assignment;
import org.eclipse.wst.jsdt.core.dom.Assignment.Operator;
import org.eclipse.wst.jsdt.core.dom.Block;
import org.eclipse.wst.jsdt.core.dom.BreakStatement;
import org.eclipse.wst.jsdt.core.dom.CatchClause;
import org.eclipse.wst.jsdt.core.dom.ClassInstanceCreation;
import org.eclipse.wst.jsdt.core.dom.ConditionalExpression;
import org.eclipse.wst.jsdt.core.dom.ContinueStatement;
import org.eclipse.wst.jsdt.core.dom.DebuggerStatement;
import org.eclipse.wst.jsdt.core.dom.DoStatement;
import org.eclipse.wst.jsdt.core.dom.EmptyStatement;
import org.eclipse.wst.jsdt.core.dom.Expression;
import org.eclipse.wst.jsdt.core.dom.ExpressionStatement;
import org.eclipse.wst.jsdt.core.dom.FieldAccess;
import org.eclipse.wst.jsdt.core.dom.ForInStatement;
import org.eclipse.wst.jsdt.core.dom.ForOfStatement;
import org.eclipse.wst.jsdt.core.dom.ForStatement;
import org.eclipse.wst.jsdt.core.dom.FunctionExpression;
import org.eclipse.wst.jsdt.core.dom.FunctionInvocation;
import org.eclipse.wst.jsdt.core.dom.IfStatement;
import org.eclipse.wst.jsdt.core.dom.InfixExpression;
import org.eclipse.wst.jsdt.core.dom.JavaScriptUnit;
import org.eclipse.wst.jsdt.core.dom.LabeledStatement;
import org.eclipse.wst.jsdt.core.dom.ListExpression;
import org.eclipse.wst.jsdt.core.dom.NumberLiteral;
import org.eclipse.wst.jsdt.core.dom.ObjectLiteral;
import org.eclipse.wst.jsdt.core.dom.ObjectLiteralField;
import org.eclipse.wst.jsdt.core.dom.PostfixExpression;
import org.eclipse.wst.jsdt.core.dom.PrefixExpression;
import org.eclipse.wst.jsdt.core.dom.RegularExpressionLiteral;
import org.eclipse.wst.jsdt.core.dom.ReturnStatement;
import org.eclipse.wst.jsdt.core.dom.SimpleName;
import org.eclipse.wst.jsdt.core.dom.SwitchCase;
import org.eclipse.wst.jsdt.core.dom.SwitchStatement;
import org.eclipse.wst.jsdt.core.dom.ThisExpression;
import org.eclipse.wst.jsdt.core.dom.ThrowStatement;
import org.eclipse.wst.jsdt.core.dom.TryStatement;
import org.eclipse.wst.jsdt.core.dom.VariableDeclarationExpression;
import org.eclipse.wst.jsdt.core.dom.VariableDeclarationStatement;
import org.eclipse.wst.jsdt.core.dom.VariableKind;
import org.eclipse.wst.jsdt.core.dom.WhileStatement;
import org.eclipse.wst.jsdt.core.dom.WithStatement;
import org.eclipse.wst.jsdt.internal.esprima.EsprimaParser;
import org.junit.Test;

@SuppressWarnings("nls")
public class EsprimaParserTests {

	@Test
	public void createParserInstance(){
		EsprimaParser parser = EsprimaParser.newParser();
		assertNotNull(parser);
	}
	
	@Test
	public void parseMultipleCalls(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("var a = 1;");
		assertNotNull(unit);
		unit = EsprimaParser.newParser().parse("var a = true;");
		assertNotNull(unit);
		unit = EsprimaParser.newParser().parse("var a = \'atest\';");
		assertNotNull(unit);
		unit = EsprimaParser.newParser().parse("var a = null;");
		assertNotNull(unit);
	}
	
	@Test
	public void checkRange(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("var a = 1;");
		assertNotNull(unit);
		assertEquals(0,unit.getStartPosition());
		assertEquals(10, unit.getLength());
	}
	
	
	
	@Test
	public void testVariableDeclaration_VAR(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("var a,b;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT){
				VariableDeclarationStatement  vd = (VariableDeclarationStatement) astNode;
				assertEquals(VariableKind.VAR, vd.getKind());
				assertEquals(2, vd.fragments().size());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testVariableDeclaration_LET(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("let a,b;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT){
				VariableDeclarationStatement  vd = (VariableDeclarationStatement) astNode;
				assertEquals(VariableKind.LET, vd.getKind());
				assertEquals(2, vd.fragments().size());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testSimpleAssignment(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("a += 123;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				Assignment assignment = (Assignment) ((ExpressionStatement)astNode).getExpression(); 
				assertSame(Operator.PLUS_ASSIGN, assignment.getOperator());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testThisExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("this;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				assertTrue(((ExpressionStatement)astNode).getExpression() instanceof ThisExpression );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testArrayExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("[1,2,3];");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				assertTrue(((ExpressionStatement)astNode).getExpression() instanceof ArrayInitializer );
				return;
			}
		}
		fail();
	}	
	
	@Test
	public void testObjectExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("o={ test:9 };");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				Expression c1 = ((ExpressionStatement)astNode).getExpression();
				assertTrue(c1 instanceof Assignment);
				Assignment a = (Assignment)c1;
				assertSame(a.getRightHandSide().getNodeType(),ASTNode.OBJECT_LITERAL);
				ObjectLiteral ol = (ObjectLiteral) a.getRightHandSide();
				assertNotNull(ol.fields().get(0));
				ObjectLiteralField lf = (ObjectLiteralField) ol.fields().get(0);
				assertEquals(ObjectLiteralField.FieldKind.INIT, lf.getKind());
				assertTrue(lf.getInitializer().getNodeType() == ASTNode.NUMBER_LITERAL);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testPosxFixExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("123++;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				PostfixExpression pf = (PostfixExpression) ((ExpressionStatement)astNode).getExpression(); 
				assertSame(PostfixExpression.Operator.INCREMENT, pf.getOperator());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testPreFixExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("delete a;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				PrefixExpression pf = (PrefixExpression) ((ExpressionStatement)astNode).getExpression(); 
				assertSame(PrefixExpression.Operator.DELETE, pf.getOperator());
				SimpleName operand = (SimpleName) pf.getOperand();
				assertEquals("a", operand.getIdentifier());
				return;
			}
		}
		fail();
	}

	@Test
	public void testInfixExpression_0(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("d+7;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				InfixExpression pf = (InfixExpression) ((ExpressionStatement)astNode).getExpression(); 
				assertSame(InfixExpression.Operator.PLUS, pf.getOperator());
				SimpleName operand = (SimpleName) pf.getLeftOperand();
				assertEquals("d", operand.getIdentifier());
				return;
			}
		}
		fail();
	}
	@Test
	public void testInfixExpression_1(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("d && a;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				InfixExpression pf = (InfixExpression) ((ExpressionStatement)astNode).getExpression(); 
				assertSame(InfixExpression.Operator.CONDITIONAL_AND, pf.getOperator());
				SimpleName operand = (SimpleName) pf.getLeftOperand();
				assertEquals("d", operand.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testArrayAccess(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("a[b];");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				assertTrue(((ExpressionStatement)astNode).getExpression() instanceof ArrayAccess );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testFieldAccess(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("a.b;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				assertTrue(((ExpressionStatement)astNode).getExpression() instanceof FieldAccess );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testConditionalExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("a==1?b:c;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof ConditionalExpression );
				ConditionalExpression conditional = (ConditionalExpression)anode.getExpression();
				assertTrue( conditional.getExpression() instanceof InfixExpression );
				return;
			}
		}
		fail();
	}

	@Test
	public void testCallExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f();");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof FunctionInvocation );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testListExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("a,b.y,a[3];");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof ListExpression );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testClassInstanceCreation(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("new ab(c,d);");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof ClassInstanceCreation);
				return;
			}
		}
		fail();
	}	
	
	@Test
	public void testFunctionExpression_withIdentifierPattern(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f = function(a){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof Assignment);
				Assignment assignment = (Assignment) anode.getExpression();
				assertTrue( assignment.getRightHandSide() instanceof FunctionExpression);
				FunctionExpression fe = (FunctionExpression) assignment.getRightHandSide();
				assertFalse(fe.getMethod().parameters().isEmpty());
				return;
			}
		}
		fail();
	}	
	
	@Test
	public void testGeneratorFunctionExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f = function* (a){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof Assignment);
				Assignment assignment = (Assignment) anode.getExpression();
				assertTrue( assignment.getRightHandSide() instanceof FunctionExpression);
				FunctionExpression fe = (FunctionExpression) assignment.getRightHandSide();
				assertTrue(fe.getMethod().isGenerator());
				assertFalse(fe.getMethod().parameters().isEmpty());
				return;
			}
		}
		fail();
	}

	@Test
	public void testFunctionExpression_withObjectPatternParameter(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f= function({foo, bar: baz}){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof Assignment);
				Assignment assignment = (Assignment) anode.getExpression();
				assertTrue( assignment.getRightHandSide() instanceof FunctionExpression);
				FunctionExpression fe = (FunctionExpression) assignment.getRightHandSide();
				assertNotNull(fe.getMethod().parameters().get(0));
				
				return;
			}
		}
		fail();
	}	
	
	
	@Test
	public void testArrowFunctionExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("foo = (bar) => 5+1;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement anode = (ExpressionStatement)astNode;
				assertTrue(anode.getExpression() instanceof Assignment);
				Assignment assignment = (Assignment) anode.getExpression();
				assertTrue( assignment.getRightHandSide() instanceof ArrowFunctionExpression );
				return;
			}
		}
		fail();
	}	
	@Test
	public void testBlockStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("{a=2;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.BLOCK){
				Block anode = (Block)astNode;
				assertFalse(anode.statements().isEmpty());
				assertTrue(anode.statements().get(0) instanceof ExpressionStatement);
				ExpressionStatement anotherNode = (ExpressionStatement)anode.statements().get(0);
				assertTrue(anotherNode.getExpression() instanceof Assignment);
				Assignment assignment = (Assignment) anotherNode.getExpression();
				assertTrue(assignment.getRightHandSide() instanceof NumberLiteral);
				assertTrue(assignment.getLeftHandSide()instanceof SimpleName);
				return;
			}
		}
		fail();
	}	

	@Test
	public void testEmptyStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("{;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.BLOCK){
				Block anode = (Block)astNode;
				assertFalse(anode.statements().isEmpty());
				assertTrue(anode.statements().get(0) instanceof EmptyStatement);
				return;
			}
		}
		fail();
	}	
	
	@Test
	public void testDebuggerStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("{debugger;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.BLOCK){
				Block anode = (Block)astNode;
				assertFalse(anode.statements().isEmpty());
				assertTrue(anode.statements().get(0) instanceof DebuggerStatement);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testWithStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("with(console){log(\"test\");};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.WITH_STATEMENT){
				WithStatement anode = (WithStatement)astNode;
				assertTrue(anode.getExpression() instanceof SimpleName );
				assertTrue(anode.getBody() instanceof Block );
				return;
			}
		}
		fail();
	}

	@Test
	public void testReturnStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f= function(){return 0;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement stmt = (ExpressionStatement)astNode;
				Assignment asgn = (Assignment) stmt.getExpression();
				FunctionExpression anode = (FunctionExpression)asgn.getRightHandSide();
				assertFalse(anode.getMethod().getBody().statements().isEmpty() );
				assertTrue(anode.getMethod().getBody().statements().get(0) instanceof ReturnStatement );
				ReturnStatement rs = (ReturnStatement) anode.getMethod().getBody().statements().get(0);
				assertTrue(rs.getExpression() instanceof NumberLiteral);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testLabeledStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("abc: a=7+1;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.LABELED_STATEMENT){
				LabeledStatement ls = (LabeledStatement)astNode;
				assertEquals("abc", ls.getLabel().getIdentifier());			
				assertTrue(ls.getBody() instanceof ExpressionStatement);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testBreakStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("while(e){break;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.WHILE_STATEMENT){
				WhileStatement ws = (WhileStatement)astNode;
				assertTrue(ws.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName)ws.getExpression();
				assertEquals("e", sn.getIdentifier());
				assertTrue(ws.getBody() instanceof Block);
				Block b = (Block)ws.getBody();
				assertTrue(b.statements().get(0) instanceof BreakStatement);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testContinueStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("while(e){continue;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.WHILE_STATEMENT){
				WhileStatement ws = (WhileStatement)astNode;
				assertTrue(ws.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName)ws.getExpression();
				assertEquals("e", sn.getIdentifier());
				assertTrue(ws.getBody() instanceof Block);
				Block b = (Block)ws.getBody();
				assertTrue(b.statements().get(0) instanceof ContinueStatement);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testIfStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("if(a){ab;}else ac;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.IF_STATEMENT){
				IfStatement is = (IfStatement)astNode;
				assertTrue(is.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName) is.getExpression();
				assertEquals("a", sn.getIdentifier());
				assertTrue(is.getElseStatement() instanceof Block);
				assertTrue(is.getThenStatement() instanceof ExpressionStatement );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testSwitchStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("switch(a) {};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.SWITCH_STATEMENT){
				SwitchStatement ss = (SwitchStatement)astNode;
				assertTrue(ss.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName)ss.getExpression();
				assertEquals("a", sn.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testSwitchCaseStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("switch(a) {case a:b;};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.SWITCH_STATEMENT){
				SwitchStatement ss = (SwitchStatement)astNode;
				assertFalse(ss.statements().isEmpty());
				assertTrue(ss.statements().get(0) instanceof SwitchCase);
				SwitchCase sc = (SwitchCase)ss.statements().get(0);
				assertTrue(sc.getExpression() instanceof SimpleName);
				assertTrue(ss.statements().get(1) instanceof ExpressionStatement);
				return;
			}
		}
		fail();
	}	
	
	@Test
	public void testThrowStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("throw d;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.THROW_STATEMENT){
				ThrowStatement ts = (ThrowStatement) astNode;
				assertTrue(ts.getExpression() instanceof SimpleName );
				SimpleName sn = (SimpleName)ts.getExpression();
				assertEquals("d", sn.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testTryCatchFinallyStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("try{}catch(e){}finally{}");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.TRY_STATEMENT){
				TryStatement ts = (TryStatement) astNode;
				assertEquals(1,ts.catchClauses().size());
				CatchClause cc = (CatchClause) ts.catchClauses().get(0);
				assertEquals("e",cc.getException().getName().getIdentifier());
				assertNotNull(ts.getFinally());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testWhileStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("while(e){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.WHILE_STATEMENT){
				WhileStatement ws = (WhileStatement)astNode;
				assertTrue(ws.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName)ws.getExpression();
				assertEquals("e", sn.getIdentifier());
				assertTrue(ws.getBody() instanceof Block);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testDoWhileStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("do{}while(e);");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.DO_STATEMENT){
				DoStatement ws = (DoStatement)astNode;
				assertTrue(ws.getExpression() instanceof SimpleName);
				SimpleName sn = (SimpleName)ws.getExpression();
				assertEquals("e", sn.getIdentifier());
				assertTrue(ws.getBody() instanceof Block);
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testForStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("for(let i=0;i<10;i++){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.FOR_STATEMENT){
				ForStatement fs = (ForStatement)astNode;
				assertTrue(fs.getBody() instanceof Block);
				assertEquals(1, fs.initializers().size());
				assertEquals(1, fs.updaters().size());
				assertTrue(fs.initializers().get(0) instanceof VariableDeclarationExpression);
				VariableDeclarationExpression vde = (VariableDeclarationExpression) fs.initializers().get(0);
				assertEquals(VariableKind.LET, vde.getKind());
				assertNotNull(fs.getExpression());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testForInStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("for(let i in a){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.FOR_IN_STATEMENT){
				ForInStatement fs = (ForInStatement)astNode;
				assertTrue(fs.getBody() instanceof Block);
				assertTrue(fs.getIterationVariable() instanceof VariableDeclarationStatement );
				VariableDeclarationStatement vde = (VariableDeclarationStatement) fs.getIterationVariable();
				assertEquals(VariableKind.LET, vde.getKind());
				assertTrue(fs.getCollection()instanceof SimpleName);
				SimpleName sn = (SimpleName)fs.getCollection();
				assertEquals("a", sn.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testForInStatement_1(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("for(i in a){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.FOR_IN_STATEMENT){
				ForInStatement fs = (ForInStatement)astNode;
				assertTrue(fs.getIterationVariable() instanceof ExpressionStatement );
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testForOfStatement(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("for(let i of a){};");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.FOR_OF_STATEMENT){
				ForOfStatement fs = (ForOfStatement)astNode;
				assertTrue(fs.getBody() instanceof Block);
				assertTrue(fs.getIterationVariable() instanceof VariableDeclarationStatement );
				VariableDeclarationStatement vde = (VariableDeclarationStatement) fs.getIterationVariable();
				assertEquals(VariableKind.LET, vde.getKind());
				assertTrue(fs.getCollection()instanceof SimpleName);
				SimpleName sn = (SimpleName)fs.getCollection();
				assertEquals("a", sn.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testAnonymousFunction(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("f(function(){});");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement es = (ExpressionStatement)astNode;
				assertTrue(es.getExpression() instanceof FunctionInvocation);
				FunctionInvocation fi = (FunctionInvocation) es.getExpression();
				assertTrue (fi.arguments().get(0) instanceof FunctionExpression);
				FunctionExpression func = (FunctionExpression)fi.arguments().get(0);
				assertNull(func.getMethod().getName());
				return;
			}
		}
		fail();
	}

	@Test
	public void testFunctionExpression_andParameter(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("(function(y){});");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement es = (ExpressionStatement)astNode;
				assertTrue(es.getExpression() instanceof FunctionExpression);
				FunctionExpression func = (FunctionExpression)es.getExpression();
				assertFalse(func.getMethod().parameters().isEmpty());
				assertEquals(1, func.getMethod().parameters().size());
				
				return;
			}
		}
		fail();
	}
	@Test
	public void testRegularExpression(){
		JavaScriptUnit unit = EsprimaParser.newParser().parse("/.{0}/;");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement es = (ExpressionStatement)astNode;
				assertTrue(es.getExpression() instanceof RegularExpressionLiteral);
				RegularExpressionLiteral rel = (RegularExpressionLiteral)es.getExpression();
				assertEquals("/.{0}/", rel.getRegularExpression());
				return;
			}
		}
		fail();
	}
	
	
	// Everything.js tests.
	
	@Test
	public void testEverythingJS_es5(){
		testEverythingJs("es5.js");
	}
	
	@Test
	public void testEverythingJS_es2015_script(){
		testEverythingJs("es2015-script.js");
	}
	
	private void testEverythingJs(String file){
		InputStream in = this.getClass().getResourceAsStream(file);
		JavaScriptUnit unit = EsprimaParser.newParser().parse(readFile(in));
		assertNotNull(unit);
		assertFalse(unit.statements().isEmpty());
	}
	
	private String readFile(InputStream input){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(input));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
