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
package org.eclipse.wst.jsdt.internal.core;

import org.eclipse.wst.jsdt.core.dom.ASTNode;
import org.eclipse.wst.jsdt.core.dom.ASTVisitor;
import org.eclipse.wst.jsdt.core.dom.Block;
import org.eclipse.wst.jsdt.core.dom.FunctionDeclaration;
import org.eclipse.wst.jsdt.core.dom.VariableDeclarationFragment;

/**
 * 
 * @author Gorkem Ercan
 *
 */
public class DOMNodeFinder extends ASTVisitor {

	private int offset;
	public ASTNode found;
	
	public DOMNodeFinder(int position){
		this.offset = position;
	}
	
	public boolean visit(FunctionDeclaration node){
		if(isInside(node)){
			found = node;
			return true;
		}
		return false;
	}
	
	public boolean visit(VariableDeclarationFragment node){
		if(isInside(node)){
			found = node;
			return true;
		}
		return false;
	}
	
	
	public boolean visit(Block node){
		return isInside(node);
	}
	
	private boolean isInside(ASTNode node){
		return (node.getStartPosition() <= this.offset && this.offset <= node.getStartPosition() + node.getLength() -1);
	}

	
	
}
