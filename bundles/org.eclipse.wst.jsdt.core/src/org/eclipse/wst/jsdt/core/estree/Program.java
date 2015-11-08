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

package org.eclipse.wst.jsdt.core.estree;

/**
 *
 */
public class Program extends Node{
	private String sourceType;
	private Node[] body; // [ Statement | ModuleDeclaration ];
	
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String aSourceType) {
		this.sourceType = aSourceType;
	}
	public Node[] getBody() {
		return body;
	}
	public void setBody(Node[] aBody) {
		this.body = aBody;
	}
}
