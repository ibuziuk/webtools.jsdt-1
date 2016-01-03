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
package org.eclipse.wst.jsdt.internal.esprima;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.wst.jsdt.core.dom.JavaScriptUnit;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * @author Gorkem Ercan
 *
 */
public class EsprimaParser {
	private static ScriptEngine engine;
	private static CompiledScript compiledEsprima;
	private Bindings bindings;

	/**
	 * 
	 */
	private EsprimaParser() {
		this.bindings = engine.createBindings();
		try {
			compiledEsprima.eval(this.bindings);
		}
		catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static EsprimaParser newParser() {
		final ScriptEngineManager manager = new ScriptEngineManager();
		synchronized (EsprimaParser.class) {
			if (engine == null) {
				engine = manager.getEngineByName("nashorn"); //$NON-NLS-1$
				Compilable compilable = (Compilable) engine;
				try {
					InputStream in = EsprimaParser.class.getResourceAsStream("esprima.js"); //$NON-NLS-1$
					if (in == null) {
						throw new RuntimeException("Failed to load esprima.js file");
					}
					compiledEsprima = compilable.compile(new InputStreamReader(in));
				}
				catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new EsprimaParser();
	}

	public JavaScriptUnit parse(String content) {
		ScriptObjectMirror jsObject = internalParse(content);
		JavaScriptUnit result = translate(jsObject);
		return result;
	}

	/**
	 * @param jsObject
	 * @return
	 */
	private JavaScriptUnit translate(ScriptObjectMirror jsObject) {
		DOMASTConverter visitor = new DOMASTConverter();
		return visitor.convert(jsObject);
	}

	private ScriptObjectMirror internalParse(String content) {
		final ScriptObjectMirror esprima = (ScriptObjectMirror) this.bindings.get("esprima");
		if (esprima == null) {
			throw new RuntimeException("Esprima parser was not loaded correctly");
		}
		HashMap<String , Boolean> options = new HashMap<String, Boolean>();
		options.put("range", Boolean.TRUE);
		options.put("tolerant", Boolean.TRUE);
		final ScriptObjectMirror tree = (ScriptObjectMirror) esprima.callMember("parse", content, options);
		return tree;
	}

}
