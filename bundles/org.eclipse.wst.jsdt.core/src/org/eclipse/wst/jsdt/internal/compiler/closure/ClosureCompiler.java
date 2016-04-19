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

import org.eclipse.wst.jsdt.core.IJavaScriptUnit;
import org.eclipse.wst.jsdt.core.JavaScriptModelException;
import org.eclipse.wst.jsdt.core.dom.AST;
import org.eclipse.wst.jsdt.core.dom.ASTNode;
import org.eclipse.wst.jsdt.core.dom.JavaScriptUnit;

import com.google.javascript.jscomp.parsing.parser.Parser;
import com.google.javascript.jscomp.parsing.parser.Parser.Config;
import com.google.javascript.jscomp.parsing.parser.SourceFile;
import com.google.javascript.jscomp.parsing.parser.trees.ProgramTree;
import com.google.javascript.jscomp.parsing.parser.util.SourcePosition;

/**
 * @author Gorkem Ercan
 *
 */
public class ClosureCompiler {
	
	

	private static class TestErrorReporter extends com.google.javascript.jscomp.parsing.parser.util.ErrorReporter {

		TestErrorReporter() {
			super();
		}

		@Override
		protected void reportError(SourcePosition location, String message) {
			this.report(message, location.source.name, location.line + 1, location.column);
		}

		protected void reportWarning(SourcePosition location, String message) {
			report(message, location.source.name, location.line + 1, location.column);
		}

		private void report(String message, String name, int line, int column) {
			System.out.println(message + " at " + name + " line: " + line + ":" + column);  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
		}
	}
	
	private String rawContent;
	private IJavaScriptUnit unit;
	
	public ClosureCompiler setSource(String content){
		this.unit = null;
		this.rawContent = content;
		return this;
	}

	public ClosureCompiler setSource(IJavaScriptUnit content){
		this.rawContent = null;
		this.unit = content;
		return this;
	}

	public JavaScriptUnit parse() {

		Config config = new Config(com.google.javascript.jscomp.parsing.parser.Parser.Config.Mode.ES6);
		SourceFile source = getSourceFile(); 
		com.google.javascript.jscomp.parsing.parser.util.ErrorReporter err = new TestErrorReporter();
		Parser parser = new Parser(config, err, source);
		ProgramTree tree = parser.parseProgram();
		AST ast = AST.newAST(AST.JLS3);
		ast.setDefaultNodeFlag(ASTNode.ORIGINAL);
		DOMTransformer transformer = new DOMTransformer(ast);
		ast.setDefaultNodeFlag(0);
		return transformer.transform(tree);
		
	}
	
	private SourceFile getSourceFile(){
		String content = rawContent;
		String filename = ""; //$NON-NLS-1$
		if(unit != null )
			try {
				content = unit.getSource();
				filename= unit.getDisplayName();
			}
			catch (JavaScriptModelException e) {
				e.printStackTrace();
			}
		return(new SourceFile(filename,content));
	}
	

}
