
package org.eclipse.wst.jsdt.internal.compiler.parser;

import org.eclipse.wst.jsdt.internal.compiler.ast.ASTNode;
import org.eclipse.wst.jsdt.internal.compiler.ast.CompilationUnitDeclaration;

import com.shapesecurity.functional.data.ImmutableList;
import com.shapesecurity.functional.data.Maybe;
import com.shapesecurity.shift.ast.ArrayBinding;
import com.shapesecurity.shift.ast.ArrayExpression;
import com.shapesecurity.shift.ast.ArrowExpression;
import com.shapesecurity.shift.ast.AssignmentExpression;
import com.shapesecurity.shift.ast.BinaryExpression;
import com.shapesecurity.shift.ast.BindingIdentifier;
import com.shapesecurity.shift.ast.BindingPropertyIdentifier;
import com.shapesecurity.shift.ast.BindingPropertyProperty;
import com.shapesecurity.shift.ast.BindingWithDefault;
import com.shapesecurity.shift.ast.Block;
import com.shapesecurity.shift.ast.BlockStatement;
import com.shapesecurity.shift.ast.BreakStatement;
import com.shapesecurity.shift.ast.CallExpression;
import com.shapesecurity.shift.ast.CatchClause;
import com.shapesecurity.shift.ast.ClassDeclaration;
import com.shapesecurity.shift.ast.ClassElement;
import com.shapesecurity.shift.ast.ClassExpression;
import com.shapesecurity.shift.ast.CompoundAssignmentExpression;
import com.shapesecurity.shift.ast.ComputedMemberExpression;
import com.shapesecurity.shift.ast.ComputedPropertyName;
import com.shapesecurity.shift.ast.ConditionalExpression;
import com.shapesecurity.shift.ast.ContinueStatement;
import com.shapesecurity.shift.ast.DataProperty;
import com.shapesecurity.shift.ast.DebuggerStatement;
import com.shapesecurity.shift.ast.Directive;
import com.shapesecurity.shift.ast.DoWhileStatement;
import com.shapesecurity.shift.ast.EmptyStatement;
import com.shapesecurity.shift.ast.Export;
import com.shapesecurity.shift.ast.ExportAllFrom;
import com.shapesecurity.shift.ast.ExportDefault;
import com.shapesecurity.shift.ast.ExportFrom;
import com.shapesecurity.shift.ast.ExportSpecifier;
import com.shapesecurity.shift.ast.ExpressionStatement;
import com.shapesecurity.shift.ast.ForInStatement;
import com.shapesecurity.shift.ast.ForOfStatement;
import com.shapesecurity.shift.ast.ForStatement;
import com.shapesecurity.shift.ast.FormalParameters;
import com.shapesecurity.shift.ast.FunctionBody;
import com.shapesecurity.shift.ast.FunctionDeclaration;
import com.shapesecurity.shift.ast.FunctionExpression;
import com.shapesecurity.shift.ast.Getter;
import com.shapesecurity.shift.ast.IdentifierExpression;
import com.shapesecurity.shift.ast.IfStatement;
import com.shapesecurity.shift.ast.Import;
import com.shapesecurity.shift.ast.ImportNamespace;
import com.shapesecurity.shift.ast.ImportSpecifier;
import com.shapesecurity.shift.ast.LabeledStatement;
import com.shapesecurity.shift.ast.LiteralBooleanExpression;
import com.shapesecurity.shift.ast.LiteralInfinityExpression;
import com.shapesecurity.shift.ast.LiteralNullExpression;
import com.shapesecurity.shift.ast.LiteralNumericExpression;
import com.shapesecurity.shift.ast.LiteralRegExpExpression;
import com.shapesecurity.shift.ast.LiteralStringExpression;
import com.shapesecurity.shift.ast.Method;
import com.shapesecurity.shift.ast.Module;
import com.shapesecurity.shift.ast.NewExpression;
import com.shapesecurity.shift.ast.NewTargetExpression;
import com.shapesecurity.shift.ast.ObjectBinding;
import com.shapesecurity.shift.ast.ObjectExpression;
import com.shapesecurity.shift.ast.ReturnStatement;
import com.shapesecurity.shift.ast.Script;
import com.shapesecurity.shift.ast.Setter;
import com.shapesecurity.shift.ast.ShorthandProperty;
import com.shapesecurity.shift.ast.SpreadElement;
import com.shapesecurity.shift.ast.StaticMemberExpression;
import com.shapesecurity.shift.ast.StaticPropertyName;
import com.shapesecurity.shift.ast.Super;
import com.shapesecurity.shift.ast.SwitchCase;
import com.shapesecurity.shift.ast.SwitchDefault;
import com.shapesecurity.shift.ast.SwitchStatement;
import com.shapesecurity.shift.ast.SwitchStatementWithDefault;
import com.shapesecurity.shift.ast.TemplateElement;
import com.shapesecurity.shift.ast.TemplateExpression;
import com.shapesecurity.shift.ast.ThisExpression;
import com.shapesecurity.shift.ast.ThrowStatement;
import com.shapesecurity.shift.ast.TryCatchStatement;
import com.shapesecurity.shift.ast.TryFinallyStatement;
import com.shapesecurity.shift.ast.UnaryExpression;
import com.shapesecurity.shift.ast.UpdateExpression;
import com.shapesecurity.shift.ast.VariableDeclaration;
import com.shapesecurity.shift.ast.VariableDeclarationStatement;
import com.shapesecurity.shift.ast.VariableDeclarator;
import com.shapesecurity.shift.ast.WhileStatement;
import com.shapesecurity.shift.ast.WithStatement;
import com.shapesecurity.shift.ast.YieldExpression;
import com.shapesecurity.shift.ast.YieldGeneratorExpression;
import com.shapesecurity.shift.visitor.Reducer;

/**
 * @author gercan
 *
 */
public class CUDeclarationReducer implements Reducer<ASTNode> {
	
	private CompilationUnitDeclaration  unit;
	public CUDeclarationReducer(CompilationUnitDeclaration cu){
		this.unit = cu;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceArrayBinding(com.shapesecurity.shift.ast.ArrayBinding, com.shapesecurity.functional.data.ImmutableList, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceArrayBinding(ArrayBinding node, ImmutableList<Maybe<ASTNode>> elements, Maybe<ASTNode> restElement) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceArrayExpression(com.shapesecurity.shift.ast.ArrayExpression, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceArrayExpression(ArrayExpression node, ImmutableList<Maybe<ASTNode>> elements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceArrowExpression(com.shapesecurity.shift.ast.ArrowExpression, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceArrowExpression(ArrowExpression node, ASTNode params, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceAssignmentExpression(com.shapesecurity.shift.ast.AssignmentExpression, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceAssignmentExpression(AssignmentExpression node, ASTNode binding, ASTNode expression) {
//		Assignment assignment = new Assignment((Expression)binding, (Expression)expression, 0);
		// TODO Auto-generated method stub
//		return assignment;
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBinaryExpression(com.shapesecurity.shift.ast.BinaryExpression, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceBinaryExpression(BinaryExpression node, ASTNode left, ASTNode right) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBindingIdentifier(com.shapesecurity.shift.ast.BindingIdentifier)
	 */
	public ASTNode reduceBindingIdentifier(BindingIdentifier node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBindingPropertyIdentifier(com.shapesecurity.shift.ast.BindingPropertyIdentifier, java.lang.Object, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceBindingPropertyIdentifier(BindingPropertyIdentifier node, ASTNode binding, Maybe<ASTNode> init) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBindingPropertyProperty(com.shapesecurity.shift.ast.BindingPropertyProperty, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceBindingPropertyProperty(BindingPropertyProperty node, ASTNode name, ASTNode binding) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBindingWithDefault(com.shapesecurity.shift.ast.BindingWithDefault, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceBindingWithDefault(BindingWithDefault node, ASTNode binding, ASTNode init) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBlock(com.shapesecurity.shift.ast.Block, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceBlock(Block node, ImmutableList<ASTNode> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBlockStatement(com.shapesecurity.shift.ast.BlockStatement, java.lang.Object)
	 */
	public ASTNode reduceBlockStatement(BlockStatement node, ASTNode block) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceBreakStatement(com.shapesecurity.shift.ast.BreakStatement)
	 */
	public ASTNode reduceBreakStatement(BreakStatement node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceCallExpression(com.shapesecurity.shift.ast.CallExpression, java.lang.Object, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceCallExpression(CallExpression node, ASTNode callee, ImmutableList<ASTNode> arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceCatchClause(com.shapesecurity.shift.ast.CatchClause, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceCatchClause(CatchClause node, ASTNode binding, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceClassDeclaration(com.shapesecurity.shift.ast.ClassDeclaration, java.lang.Object, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceClassDeclaration(ClassDeclaration node, ASTNode name, Maybe<ASTNode> _super, ImmutableList<ASTNode> elements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceClassElement(com.shapesecurity.shift.ast.ClassElement, java.lang.Object)
	 */
	public ASTNode reduceClassElement(ClassElement node, ASTNode method) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceClassExpression(com.shapesecurity.shift.ast.ClassExpression, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceClassExpression(ClassExpression node, Maybe<ASTNode> name, Maybe<ASTNode> _super, ImmutableList<ASTNode> elements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceCompoundAssignmentExpression(com.shapesecurity.shift.ast.CompoundAssignmentExpression, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceCompoundAssignmentExpression(CompoundAssignmentExpression node, ASTNode binding, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceComputedMemberExpression(com.shapesecurity.shift.ast.ComputedMemberExpression, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceComputedMemberExpression(ComputedMemberExpression node, ASTNode expression, ASTNode object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceComputedPropertyName(com.shapesecurity.shift.ast.ComputedPropertyName, java.lang.Object)
	 */
	public ASTNode reduceComputedPropertyName(ComputedPropertyName node, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceConditionalExpression(com.shapesecurity.shift.ast.ConditionalExpression, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceConditionalExpression(ConditionalExpression node, ASTNode test, ASTNode consequent, ASTNode alternate) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceContinueStatement(com.shapesecurity.shift.ast.ContinueStatement)
	 */
	public ASTNode reduceContinueStatement(ContinueStatement node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceDataProperty(com.shapesecurity.shift.ast.DataProperty, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceDataProperty(DataProperty node, ASTNode value, ASTNode name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceDebuggerStatement(com.shapesecurity.shift.ast.DebuggerStatement)
	 */
	public ASTNode reduceDebuggerStatement(DebuggerStatement node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceDirective(com.shapesecurity.shift.ast.Directive)
	 */
	public ASTNode reduceDirective(Directive node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceDoWhileStatement(com.shapesecurity.shift.ast.DoWhileStatement, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceDoWhileStatement(DoWhileStatement node, ASTNode test, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceEmptyStatement(com.shapesecurity.shift.ast.EmptyStatement)
	 */
	public ASTNode reduceEmptyStatement(EmptyStatement node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExport(com.shapesecurity.shift.ast.Export, java.lang.Object)
	 */
	public ASTNode reduceExport(Export node, ASTNode declaration) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExportAllFrom(com.shapesecurity.shift.ast.ExportAllFrom)
	 */
	public ASTNode reduceExportAllFrom(ExportAllFrom node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExportDefault(com.shapesecurity.shift.ast.ExportDefault, java.lang.Object)
	 */
	public ASTNode reduceExportDefault(ExportDefault node, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExportFrom(com.shapesecurity.shift.ast.ExportFrom, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceExportFrom(ExportFrom node, ImmutableList<ASTNode> namedExports) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExportSpecifier(com.shapesecurity.shift.ast.ExportSpecifier)
	 */
	public ASTNode reduceExportSpecifier(ExportSpecifier node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceExpressionStatement(com.shapesecurity.shift.ast.ExpressionStatement, java.lang.Object)
	 */
	public ASTNode reduceExpressionStatement(ExpressionStatement node, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceForInStatement(com.shapesecurity.shift.ast.ForInStatement, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceForInStatement(ForInStatement node, ASTNode left, ASTNode right, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceForOfStatement(com.shapesecurity.shift.ast.ForOfStatement, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceForOfStatement(ForOfStatement node, ASTNode left, ASTNode right, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceForStatement(com.shapesecurity.shift.ast.ForStatement, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.Maybe, java.lang.Object)
	 */
	public ASTNode reduceForStatement(ForStatement node, Maybe<ASTNode> init, Maybe<ASTNode> test, Maybe<ASTNode> update, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceFormalParameters(com.shapesecurity.shift.ast.FormalParameters, com.shapesecurity.functional.data.ImmutableList, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceFormalParameters(FormalParameters node, ImmutableList<ASTNode> items, Maybe<ASTNode> rest) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceFunctionBody(com.shapesecurity.shift.ast.FunctionBody, com.shapesecurity.functional.data.ImmutableList, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceFunctionBody(FunctionBody node, ImmutableList<ASTNode> directives, ImmutableList<ASTNode> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceFunctionDeclaration(com.shapesecurity.shift.ast.FunctionDeclaration, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceFunctionDeclaration(FunctionDeclaration node, ASTNode name, ASTNode params, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceFunctionExpression(com.shapesecurity.shift.ast.FunctionExpression, com.shapesecurity.functional.data.Maybe, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceFunctionExpression(FunctionExpression node, Maybe<ASTNode> name, ASTNode parameters, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceGetter(com.shapesecurity.shift.ast.Getter, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceGetter(Getter node, ASTNode body, ASTNode name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceIdentifierExpression(com.shapesecurity.shift.ast.IdentifierExpression)
	 */
	public ASTNode reduceIdentifierExpression(IdentifierExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceIfStatement(com.shapesecurity.shift.ast.IfStatement, java.lang.Object, java.lang.Object, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceIfStatement(IfStatement node, ASTNode test, ASTNode consequent, Maybe<ASTNode> alternate) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceImport(com.shapesecurity.shift.ast.Import, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceImport(Import node, Maybe<ASTNode> defaultBinding, ImmutableList<ASTNode> namedImports) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceImportNamespace(com.shapesecurity.shift.ast.ImportNamespace, com.shapesecurity.functional.data.Maybe, java.lang.Object)
	 */
	public ASTNode reduceImportNamespace(ImportNamespace node, Maybe<ASTNode> defaultBinding, ASTNode namespaceBinding) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceImportSpecifier(com.shapesecurity.shift.ast.ImportSpecifier, java.lang.Object)
	 */
	public ASTNode reduceImportSpecifier(ImportSpecifier node, ASTNode binding) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLabeledStatement(com.shapesecurity.shift.ast.LabeledStatement, java.lang.Object)
	 */
	public ASTNode reduceLabeledStatement(LabeledStatement node, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralBooleanExpression(com.shapesecurity.shift.ast.LiteralBooleanExpression)
	 */
	public ASTNode reduceLiteralBooleanExpression(LiteralBooleanExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralInfinityExpression(com.shapesecurity.shift.ast.LiteralInfinityExpression)
	 */
	public ASTNode reduceLiteralInfinityExpression(LiteralInfinityExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralNullExpression(com.shapesecurity.shift.ast.LiteralNullExpression)
	 */
	public ASTNode reduceLiteralNullExpression(LiteralNullExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralNumericExpression(com.shapesecurity.shift.ast.LiteralNumericExpression)
	 */
	public ASTNode reduceLiteralNumericExpression(LiteralNumericExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralRegExpExpression(com.shapesecurity.shift.ast.LiteralRegExpExpression)
	 */
	public ASTNode reduceLiteralRegExpExpression(LiteralRegExpExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceLiteralStringExpression(com.shapesecurity.shift.ast.LiteralStringExpression)
	 */
	public ASTNode reduceLiteralStringExpression(LiteralStringExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceMethod(com.shapesecurity.shift.ast.Method, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceMethod(Method node, ASTNode params, ASTNode body, ASTNode name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceModule(com.shapesecurity.shift.ast.Module, com.shapesecurity.functional.data.ImmutableList, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceModule(Module node, ImmutableList<ASTNode> directives, ImmutableList<ASTNode> items) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceNewExpression(com.shapesecurity.shift.ast.NewExpression, java.lang.Object, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceNewExpression(NewExpression node, ASTNode callee, ImmutableList<ASTNode> arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceNewTargetExpression(com.shapesecurity.shift.ast.NewTargetExpression)
	 */
	public ASTNode reduceNewTargetExpression(NewTargetExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceObjectBinding(com.shapesecurity.shift.ast.ObjectBinding, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceObjectBinding(ObjectBinding node, ImmutableList<ASTNode> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceObjectExpression(com.shapesecurity.shift.ast.ObjectExpression, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceObjectExpression(ObjectExpression node, ImmutableList<ASTNode> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceReturnStatement(com.shapesecurity.shift.ast.ReturnStatement, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceReturnStatement(ReturnStatement node, Maybe<ASTNode> expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceScript(com.shapesecurity.shift.ast.Script, com.shapesecurity.functional.data.ImmutableList, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceScript(Script node, ImmutableList<ASTNode> directives, ImmutableList<ASTNode> statements) {
//		this.unit.statements = (ProgramElement[]) statements.toArray(new ProgramElement[statements.length]);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSetter(com.shapesecurity.shift.ast.Setter, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceSetter(Setter node, ASTNode params, ASTNode body, ASTNode name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceShorthandProperty(com.shapesecurity.shift.ast.ShorthandProperty)
	 */
	public ASTNode reduceShorthandProperty(ShorthandProperty node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSpreadElement(com.shapesecurity.shift.ast.SpreadElement, java.lang.Object)
	 */
	public ASTNode reduceSpreadElement(SpreadElement node, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceStaticMemberExpression(com.shapesecurity.shift.ast.StaticMemberExpression, java.lang.Object)
	 */
	public ASTNode reduceStaticMemberExpression(StaticMemberExpression node, ASTNode object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceStaticPropertyName(com.shapesecurity.shift.ast.StaticPropertyName)
	 */
	public ASTNode reduceStaticPropertyName(StaticPropertyName node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSuper(com.shapesecurity.shift.ast.Super)
	 */
	public ASTNode reduceSuper(Super node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSwitchCase(com.shapesecurity.shift.ast.SwitchCase, java.lang.Object, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceSwitchCase(SwitchCase node, ASTNode test, ImmutableList<ASTNode> consequent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSwitchDefault(com.shapesecurity.shift.ast.SwitchDefault, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceSwitchDefault(SwitchDefault node, ImmutableList<ASTNode> consequent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSwitchStatement(com.shapesecurity.shift.ast.SwitchStatement, java.lang.Object, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceSwitchStatement(SwitchStatement node, ASTNode discriminant, ImmutableList<ASTNode> cases) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceSwitchStatementWithDefault(com.shapesecurity.shift.ast.SwitchStatementWithDefault, java.lang.Object, com.shapesecurity.functional.data.ImmutableList, java.lang.Object, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceSwitchStatementWithDefault(SwitchStatementWithDefault node, ASTNode discriminant, ImmutableList<ASTNode> preDefaultCases, ASTNode defaultCase, ImmutableList<ASTNode> postDefaultCases) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceTemplateElement(com.shapesecurity.shift.ast.TemplateElement)
	 */
	public ASTNode reduceTemplateElement(TemplateElement node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceTemplateExpression(com.shapesecurity.shift.ast.TemplateExpression, com.shapesecurity.functional.data.Maybe, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceTemplateExpression(TemplateExpression node, Maybe<ASTNode> tag, ImmutableList<ASTNode> elements) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceThisExpression(com.shapesecurity.shift.ast.ThisExpression)
	 */
	public ASTNode reduceThisExpression(ThisExpression node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceThrowStatement(com.shapesecurity.shift.ast.ThrowStatement, java.lang.Object)
	 */
	public ASTNode reduceThrowStatement(ThrowStatement node, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceTryCatchStatement(com.shapesecurity.shift.ast.TryCatchStatement, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceTryCatchStatement(TryCatchStatement node, ASTNode block, ASTNode catchClause) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceTryFinallyStatement(com.shapesecurity.shift.ast.TryFinallyStatement, java.lang.Object, com.shapesecurity.functional.data.Maybe, java.lang.Object)
	 */
	public ASTNode reduceTryFinallyStatement(TryFinallyStatement node, ASTNode block, Maybe<ASTNode> catchClause, ASTNode finalizer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceUnaryExpression(com.shapesecurity.shift.ast.UnaryExpression, java.lang.Object)
	 */
	public ASTNode reduceUnaryExpression(UnaryExpression node, ASTNode operand) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceUpdateExpression(com.shapesecurity.shift.ast.UpdateExpression, java.lang.Object)
	 */
	public ASTNode reduceUpdateExpression(UpdateExpression node, ASTNode operand) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceVariableDeclaration(com.shapesecurity.shift.ast.VariableDeclaration, com.shapesecurity.functional.data.ImmutableList)
	 */
	public ASTNode reduceVariableDeclaration(VariableDeclaration node, ImmutableList<ASTNode> declarators) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceVariableDeclarationStatement(com.shapesecurity.shift.ast.VariableDeclarationStatement, java.lang.Object)
	 */
	public ASTNode reduceVariableDeclarationStatement(VariableDeclarationStatement node, ASTNode declaration) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceVariableDeclarator(com.shapesecurity.shift.ast.VariableDeclarator, java.lang.Object, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceVariableDeclarator(VariableDeclarator node, ASTNode binding, Maybe<ASTNode> init) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceWhileStatement(com.shapesecurity.shift.ast.WhileStatement, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceWhileStatement(WhileStatement node, ASTNode test, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceWithStatement(com.shapesecurity.shift.ast.WithStatement, java.lang.Object, java.lang.Object)
	 */
	public ASTNode reduceWithStatement(WithStatement node, ASTNode object, ASTNode body) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceYieldExpression(com.shapesecurity.shift.ast.YieldExpression, com.shapesecurity.functional.data.Maybe)
	 */
	public ASTNode reduceYieldExpression(YieldExpression node, Maybe<ASTNode> expression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shapesecurity.shift.visitor.Reducer#reduceYieldGeneratorExpression(com.shapesecurity.shift.ast.YieldGeneratorExpression, java.lang.Object)
	 */
	public ASTNode reduceYieldGeneratorExpression(YieldGeneratorExpression node, ASTNode expression) {
		// TODO Auto-generated method stub
		return null;
	}


}
