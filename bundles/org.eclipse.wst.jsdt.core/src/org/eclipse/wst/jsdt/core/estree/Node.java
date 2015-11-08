/*******************************************************************************
 * Copyright (c) 2013, 2015 Red Hat, Inc. 
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
 *
 */
public abstract class Node {
	
	private String type;
	private SourceLocation loc;

	public String getType() {
		return type;
	}

	public void setType(String aType) {
		this.type = aType;
	}
	
	public SourceLocation getLoc() {
		return loc;
	}

	public void setLoc(SourceLocation aLoc) {
		this.loc = aLoc;
	}
	
}
