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
public class SourceLocation {
	private String source;
	private Position start;
	private Position end;
	
	public String getSource() {
		return source;
	}
	public void setSource(String aSource) {
		this.source = aSource;
	}
	public Position getStart() {
		return start;
	}
	public void setStart(Position aStart) {
		this.start = aStart;
	}
	public Position getEnd() {
		return end;
	}
	public void setEnd(Position aEnd) {
		this.end = aEnd;
	}
}
