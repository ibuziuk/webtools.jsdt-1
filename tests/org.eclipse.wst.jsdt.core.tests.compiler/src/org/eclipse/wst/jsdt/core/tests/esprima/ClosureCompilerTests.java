/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 		 Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.wst.jsdt.core.tests.esprima;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.eclipse.wst.jsdt.core.dom.ASTNode;
import org.eclipse.wst.jsdt.core.dom.ExpressionStatement;
import org.eclipse.wst.jsdt.core.dom.JavaScriptUnit;
import org.eclipse.wst.jsdt.core.dom.SimpleName;
import org.eclipse.wst.jsdt.core.dom.TemplateElement;
import org.eclipse.wst.jsdt.core.dom.TemplateLiteral;
import org.eclipse.wst.jsdt.internal.compiler.closure.ClosureCompiler;
import org.junit.Test;

@SuppressWarnings("nls")
public class ClosureCompilerTests {
	
	
	@Test
	public void testTemplateLiteral(){
		JavaScriptUnit unit = parse("`this blog lives ${cheer} at ${host}`");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement es = (ExpressionStatement) astNode;
				assertEquals(ASTNode.TEMPLATE_LITERAL, es.getExpression().getNodeType());
				TemplateLiteral tl = (TemplateLiteral)es.getExpression();
				assertEquals(3, tl.elements().size());
				assertEquals(2, tl.expressions().size());
				assertNull(tl.getTag());
				TemplateElement el = (TemplateElement)tl.elements().get(0);
				assertEquals("this blog lives ", el.getRawValue());
				assertFalse(el.isTail());
				el = (TemplateElement)tl.elements().get(2);
				assertTrue(el.isTail());
				SimpleName name = (SimpleName)tl.expressions().get(0);
				assertEquals("cheer", name.getIdentifier());
				return;
			}
		}
		fail();
	}
	
	@Test
	public void testTagTemplateLiteral(){
		JavaScriptUnit unit = parse("aTag`this blog lives ${cheer} at ${host}`");
		assertNotNull(unit);
		List<ASTNode> statements = unit.statements();
		for (ASTNode astNode : statements) {
			if(astNode.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
				ExpressionStatement es = (ExpressionStatement) astNode;
				assertEquals(ASTNode.TEMPLATE_LITERAL, es.getExpression().getNodeType());
				TemplateLiteral tl = (TemplateLiteral)es.getExpression();
				assertEquals(3, tl.elements().size());
				assertEquals(2, tl.expressions().size());
				assertNotNull(tl.getTag());
				SimpleName tag = (SimpleName)tl.getTag();
				assertEquals("aTag", tag.getIdentifier());
				TemplateElement el = (TemplateElement)tl.elements().get(0);
				assertEquals("this blog lives ", el.getRawValue());
				assertFalse(el.isTail());
				el = (TemplateElement)tl.elements().get(2);
				assertTrue(el.isTail());
				SimpleName name = (SimpleName)tl.expressions().get(0);
				assertEquals("cheer", name.getIdentifier());
				assertEquals("aTag`this blog lives ${cheer} at ${host}`", tl.toString());
				return;

			}
		}
		fail();
	}

	@Test
	public void testEverythingJS_es5(){
		testEverythingJs("es5.js");
	}
	
	@Test
	public void testEverythingJS_es2015_script(){
		testEverythingJs("es2015-script.js");
	}

	@Test
	public void testEverythingJS_es2015_module(){
		testEverythingJs("es2015-module.js");
	}

	private JavaScriptUnit parse( String content){
	
		ClosureCompiler compiler = new ClosureCompiler();
		return compiler.setSource(content).parse();
		
	}

	private void testEverythingJs(String file){
		InputStream in = this.getClass().getResourceAsStream(file);
		parse(readFile(in));
	}

	private String readFile(InputStream input){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(input,Charset.forName("UTF-8")));
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
