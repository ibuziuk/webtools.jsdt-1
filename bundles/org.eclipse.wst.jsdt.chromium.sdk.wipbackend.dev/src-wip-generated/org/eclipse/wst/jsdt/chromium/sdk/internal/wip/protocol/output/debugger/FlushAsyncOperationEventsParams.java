// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.debugger;

/**
Fires pending <code>asyncOperationStarted</code> events (if any), as if a debugger stepping session has just been started.
 */
public class FlushAsyncOperationEventsParams extends org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.WipParams {
  public FlushAsyncOperationEventsParams() {
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.BasicConstants.Domain.DEBUGGER + ".flushAsyncOperationEvents";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
