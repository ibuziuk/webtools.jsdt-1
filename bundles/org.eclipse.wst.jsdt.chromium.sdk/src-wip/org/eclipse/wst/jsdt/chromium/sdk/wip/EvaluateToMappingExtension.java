// Copyright (c) 2011 The Chromium Authors. All rights reserved.
// This program and the accompanying materials are made available
// under the terms of the Eclipse Public License v1.0 which accompanies
// this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html

package org.eclipse.wst.jsdt.chromium.sdk.wip;

import java.util.Map;

import org.eclipse.wst.jsdt.chromium.sdk.JsEvaluateContext;
import org.eclipse.wst.jsdt.chromium.sdk.JsObject;
import org.eclipse.wst.jsdt.chromium.sdk.JsValue;
import org.eclipse.wst.jsdt.chromium.sdk.RelayOk;
import org.eclipse.wst.jsdt.chromium.sdk.RemoteValueMapping;
import org.eclipse.wst.jsdt.chromium.sdk.SyncCallback;
import org.eclipse.wst.jsdt.chromium.sdk.JsEvaluateContext.EvaluateCallback;
import org.eclipse.wst.jsdt.chromium.sdk.util.MethodIsBlockingException;

/**
 * An extension to evaluate methods, that allows to specify {@link RemoteValueMapping}
 * as an additional argument 'targetMapping'.
 * The extension is available from
 * {@link WipJavascriptVm#getEvaluateWithDestinationMappingExtension()}.
 */
public interface EvaluateToMappingExtension {
  /**
   * Synchronously evaluates an arbitrary JavaScript {@code expression} in
   * the particular context.
   * Previously loaded {@link JsObject}s can be addressed from the expression if listed in
   * additionalContext parameter.
   * The evaluation result is reported to the specified {@code evaluateCallback}.
   * The method will block until the evaluation result is available.
   *
   * @param expression to evaluate
   * @param additionalContext a name-to-value map that adds new values to an expression
   *     scope; may be null
   * @param targetMapping mapping the result must belong to
   * @param evaluateCallback to report the evaluation result to
   * @throws MethodIsBlockingException if called from a callback because it blocks
   *         until remote VM returns result
   */
  void evaluateSync(JsEvaluateContext evaluateContext, String expression,
      Map<String, ? extends JsValue> additionalContext, RemoteValueMapping targetMapping,
      EvaluateCallback evaluateCallback) throws MethodIsBlockingException;

  /**
   * Asynchronously evaluates an arbitrary JavaScript {@code expression} in
   * the particular context.
   * Previously loaded {@link JsObject}s can be addressed from the expression if listed in
   * additionalContext parameter.
   * The evaluation result is reported to the specified {@code evaluateCallback}.
   * The method doesn't block.
   *
   * @param expression to evaluate
   * @param additionalContext a name-to-value map that adds new values to an expression
   *     scope; may be null
   * @param targetMapping mapping the result must belong to
   * @param evaluateCallback to report the evaluation result to
   * @param syncCallback to report the end of any processing
   */
  RelayOk evaluateAsync(JsEvaluateContext evaluateContext, String expression,
      Map<String, ? extends JsValue> additionalContext, RemoteValueMapping targetMapping,
      EvaluateCallback evaluateCallback, SyncCallback syncCallback);
}
