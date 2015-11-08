package org.eclipse.wst.jsdt.core.estree;

/**
 * @author gercan
 *
 */
public class BreakStatement extends Statement {
	private Identifier label;

	public Identifier getLabel() {
		return label;
	}

	public void setLabel(Identifier aLabel) {
		this.label = aLabel;
	}
}
