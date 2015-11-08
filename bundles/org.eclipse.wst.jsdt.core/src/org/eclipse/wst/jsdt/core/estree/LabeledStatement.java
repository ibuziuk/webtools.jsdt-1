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
 * @author gercan
 *
 */
public class LabeledStatement extends Statement {
	private Identifier label;
	private Statement body;
	public Identifier getLabel() {
		return label;
	}
	public void setLabel(Identifier aLabel) {
		this.label = aLabel;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement aBody) {
		this.body = aBody;
	}
}
