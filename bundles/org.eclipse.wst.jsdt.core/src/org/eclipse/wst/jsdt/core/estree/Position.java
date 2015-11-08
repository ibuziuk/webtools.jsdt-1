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
 */
public class Position {
	private int line;
	private int column;
	
	public int getLine() {
		return line;
	}
	public void setLine(int aLine) {
		this.line = aLine;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int aColumn) {
		this.column = aColumn;
	}

}
