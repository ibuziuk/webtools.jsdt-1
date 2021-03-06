// Copyright (c) 2009 The Chromium Authors. All rights reserved.
// This program and the accompanying materials are made available
// under the terms of the Eclipse Public License v1.0 which accompanies
// this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html

package org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Optional base interface for JSON type interface. Underlying JSON object becomes available
 * to user this way. The JSON type instance may be created from {@link JSONObject} only
 * (not from {@link JSONArray} or whatever).
 */
public interface JsonObjectBased extends AnyObjectBased {
  JSONObject getUnderlyingObject();
}
