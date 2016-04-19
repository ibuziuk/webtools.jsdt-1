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

package org.eclipse.wst.jsdt.internal.compiler.closure;

import org.eclipse.wst.jsdt.core.UnimplementedException;
import org.eclipse.wst.jsdt.core.dom.AST;
import org.eclipse.wst.jsdt.core.dom.ASTNode;
import org.eclipse.wst.jsdt.core.dom.ArrayInitializer;
import org.eclipse.wst.jsdt.core.dom.ExpressionStatement;
import org.eclipse.wst.jsdt.core.dom.InfixExpression;
import org.eclipse.wst.jsdt.core.dom.JavaScriptUnit;
import org.eclipse.wst.jsdt.core.dom.TemplateElement;
import org.eclipse.wst.jsdt.core.dom.TemplateLiteral;

import com.google.javascript.jscomp.parsing.parser.Token;
import com.google.javascript.jscomp.parsing.parser.trees.ArrayLiteralExpressionTree;
import com.google.javascript.jscomp.parsing.parser.trees.BinaryOperatorTree;
import com.google.javascript.jscomp.parsing.parser.trees.ExpressionStatementTree;
import com.google.javascript.jscomp.parsing.parser.trees.ParseTree;
import com.google.javascript.jscomp.parsing.parser.trees.ProgramTree;
import com.google.javascript.jscomp.parsing.parser.trees.TemplateLiteralExpressionTree;
import com.google.javascript.jscomp.parsing.parser.trees.TemplateLiteralPortionTree;
import com.google.javascript.jscomp.parsing.parser.trees.TemplateSubstitutionTree;

/**
 * @author Gorkem Ercan
 *
 */
public class DOMTransformer {

	private final AST ast;
	
	public DOMTransformer(AST t){
		this.ast = t;
	}
	
	public JavaScriptUnit transform(ProgramTree root){
		JavaScriptUnit $  = ast.newJavaScriptUnit();
		for (ParseTree treeNode : root.sourceElements) {
			$.statements().add(process(treeNode));
		
		}
		return $;
	}
	
	private ASTNode process(ParseTree node ){
		ASTNode $ = null;
		switch (node.type) {
			case BINARY_OPERATOR:
				$ = convert(node.asBinaryOperator());
				break;
			case ARRAY_LITERAL_EXPRESSION:
				$ = convert(node.asArrayLiteralExpression());
				break;
			case TEMPLATE_LITERAL_EXPRESSION:
				$ = convert(node.asTemplateLiteralExpression());
				break;
			case TEMPLATE_LITERAL_PORTION:
				$ = convert(node.asTemplateLiteralPortion());
				break;
			case TEMPLATE_SUBSTITUTION:
				$ = convert(node.asTemplateSubstitution());
				break;
			case EXPRESSION_STATEMENT:
				$ = convert(node.asExpressionStatement());
				break;
			default :
				throw new UnimplementedException(node.type.toString() + " conversion is not implemented"); //$NON-NLS-1$
		}
		$.setSourceRange(node.location.start.offset, node.location.end.offset - node.location.start.offset);
		return $;
		
	}

	/**
	 * @param asTemplateSubstitution
	 * @return
	 */
	private ASTNode convert(TemplateSubstitutionTree asTemplateSubstitution) {
		// TODO Auto-generated method stub
		return null;
	}

	private ASTNode convert(TemplateLiteralPortionTree tree) {
		TemplateElement $ = ast.newTemplateElement();
		$.setStructuralProperty(TemplateElement.RAW_VALUE_PROPERTY, tree.value.asLiteral().value);
		return $;
	}

	private ASTNode convert(ExpressionStatementTree tree) {
		ExpressionStatement $ = ast.newExpressionStatement();
		$.setStructuralProperty(ExpressionStatement.EXPRESSION_PROPERTY, process(tree.expression));
		return $;
	}

	private ASTNode convert(TemplateLiteralExpressionTree tree) {
		TemplateLiteral $ = ast.newTemplateLiteral();
		for ( ParseTree pt : tree.elements) {
			$.elements().add(process(pt));
		}
		
		return $;
	}

	private ASTNode convert(ArrayLiteralExpressionTree tree) {
		ArrayInitializer $ = ast.newArrayInitializer();
		for ( ParseTree pe : tree.elements) {
			$.expressions().add(process(pe));
		}
		return $;
	}

	private ASTNode convert(BinaryOperatorTree tree) {
		InfixExpression $ = ast.newInfixExpression();
		$.setStructuralProperty(InfixExpression.LEFT_OPERAND_PROPERTY, process(tree.left));
		$.setStructuralProperty(InfixExpression.RIGHT_OPERAND_PROPERTY, process(tree.right));
		$.setStructuralProperty(InfixExpression.OPERATOR_PROPERTY, convertBinaryOperator(tree.operator));
		return $;
	}

	/**
	 * @param operator
	 * @return
	 */
	private InfixExpression.Operator convertBinaryOperator(Token operator) {
		return InfixExpression.Operator.toOperator(operator.toString());
	}
	
}
