/*******************************************************************************
 * Copyright (c) 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.wst.jsdt.internal.core.validation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.core.compiler.CategorizedProblem;
import org.eclipse.wst.jsdt.internal.compiler.CompilationResult;
import org.eclipse.wst.jsdt.internal.compiler.Compiler;
import org.eclipse.wst.jsdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.wst.jsdt.internal.compiler.ICompilerRequestor;
import org.eclipse.wst.jsdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.wst.jsdt.internal.compiler.env.INameEnvironment;
import org.eclipse.wst.jsdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.wst.jsdt.internal.core.JavaProject;
import org.eclipse.wst.jsdt.internal.core.builder.ClasspathMultiDirectory;
import org.eclipse.wst.jsdt.internal.core.builder.NameEnvironment;
import org.eclipse.wst.jsdt.internal.core.builder.ProblemFactory;
import org.eclipse.wst.jsdt.internal.core.builder.SourceFile;
import org.eclipse.wst.jsdt.internal.core.util.Util;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;
import org.eclipse.wst.validation.ValidatorMessage;

/**
 * @author orlandor@mx1.ibm.com
 * 
 * JavaScript V2 Validator
 *
 */
public class JavaScriptValidator extends AbstractValidator implements ICompilerRequestor {
	private static Map <JavaProject, Compiler> projectToCompilerMap = new HashMap<JavaProject, Compiler>();
	
	private CategorizedProblem[] resourceProblems;
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.validation.AbstractValidator#validate(org.eclipse.core.resources.IResource, int, org.eclipse.wst.validation.ValidationState, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public ValidationResult validate(IResource resource, int kind, ValidationState state, IProgressMonitor monitor) {
		ValidationResult result = new ValidationResult();

		JavaProject project = (JavaProject) JavaScriptCore.create(resource.getProject());
		INameEnvironment nameEnvironment = new NameEnvironment(project);
		
		Compiler compiler = projectToCompilerMap.get(project);
		if (compiler == null) {
			compiler = newCompiler(project, nameEnvironment);
			projectToCompilerMap.put(project, compiler);
		}
		
		// Just care about javascript valid files
		if (resource instanceof IFile && Util.isJavaLikeFileName(resource.getName())) {
			SourceFile sf = findSourceFile((IFile) resource, true, nameEnvironment);
			if (sf == null) {
				return result;
			}
			
			compiler.compile(new ICompilationUnit[]{ sf });
			// Does not worth keep analyzing
			if (resourceProblems == null) {
				return result;
			}
			for (CategorizedProblem problem : resourceProblems) {
				ValidatorMessage vm = ValidatorMessage.create(problem.getMessage(), resource);
				vm.setAttribute(IMarker.SEVERITY, problem.isError() ? IMarker.SEVERITY_ERROR : IMarker.SEVERITY_WARNING);
				vm.setType(problem.getMarkerType());
				vm.setAttribute(IMarker.LINE_NUMBER, problem.getSourceLineNumber());
				vm.setAttribute(IMarker.CHAR_START, problem.getSourceStart());
				vm.setAttribute(IMarker.CHAR_END, problem.getSourceEnd() + 1);
				result.add(vm);
			}
		}

		return result;
	}
	
	/*
	 * Pretty much a copy of org.eclipse.wst.jsdt.internal.core.builder.AbstractImageBuilder.newCompiler()
	 */
	private Compiler newCompiler(JavaProject project, INameEnvironment nameEnvironment) {
		
		// disable entire javadoc support if not interested in diagnostics
		Map projectOptions = project.getOptions(true);
		String option = (String) projectOptions.get(JavaScriptCore.COMPILER_PB_INVALID_JAVADOC);
		if (option == null || option.equals(JavaScriptCore.IGNORE)) { // TODO (frederic) see why option is null sometimes while running model tests!?
			option = (String) projectOptions.get(JavaScriptCore.COMPILER_PB_MISSING_JAVADOC_TAGS);
			if (option == null || option.equals(JavaScriptCore.IGNORE)) {
				option = (String) projectOptions.get(JavaScriptCore.COMPILER_PB_MISSING_JAVADOC_COMMENTS);
				if (option == null || option.equals(JavaScriptCore.IGNORE)) {
					option = (String) projectOptions.get(JavaScriptCore.COMPILER_PB_UNUSED_IMPORT);
					if (option == null || option.equals(JavaScriptCore.IGNORE)) { // Unused import need also to look inside javadoc comment
						projectOptions.put(JavaScriptCore.COMPILER_DOC_COMMENT_SUPPORT, JavaScriptCore.DISABLED);
					}
				}
			}
		}

		// called once when the builder is initialized... can override if needed
		CompilerOptions compilerOptions = new CompilerOptions(projectOptions);
		compilerOptions.performMethodsFullRecovery = true;
		compilerOptions.performStatementsRecovery = true;		
		
		Compiler newCompiler = new Compiler(
			nameEnvironment,
			DefaultErrorHandlingPolicies.proceedWithAllProblems(),
			compilerOptions,
			this,
			ProblemFactory.getProblemFactory(Locale.getDefault()));
		CompilerOptions options = newCompiler.options;

		// enable the compiler reference info support
		options.produceReferenceInfo = true;

		return newCompiler;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.internal.compiler.ICompilerRequestor#acceptResult(org.eclipse.wst.jsdt.internal.compiler.CompilationResult)
	 */
	public void acceptResult(CompilationResult result) {
		resourceProblems = result.getAllProblems();
	}
	
	/*
	 * Pretty much a copy of org.eclipse.wst.jsdt.internal.core.builder.AbstractImageBuilder.findSourceFile(IFile, boolean)
	 */
	private SourceFile findSourceFile(IFile file, boolean mustExist, INameEnvironment nameEnvironment) {
		if (mustExist && !file.exists()) return null;
		if (file.isDerived()) return null;

		ClasspathMultiDirectory [] sourceLocations = ((NameEnvironment)nameEnvironment).getSourceLocations();
		
		// assumes the file exists in at least one of the source folders & is not excluded
		ClasspathMultiDirectory md = sourceLocations[0];
		if (sourceLocations.length > 1) {
			IPath sourceFileFullPath = file.getFullPath();
			for (int j = 0, m = sourceLocations.length; j < m; j++) {
				if (sourceLocations[j].getSourceFolder().getFullPath().isPrefixOf(sourceFileFullPath)) {
					md = sourceLocations[j];
					if (md.getExclusionPatterns() == null && md.getInclusionPatterns() == null)
						break;
					if (!Util.isExcluded(file, md.getInclusionPatterns(), md.getExclusionPatterns()))
						break;
				}
			}
		}
		
		if (!Util.isExcluded(file, md.getInclusionPatterns(), md.getExclusionPatterns())) {
			return new SourceFile(file, md);
		}
		
		return null;
	}	
}