// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.debugger;

/**
Enables or disables async call stacks tracking.
 */
public class SetAsyncCallStackDepthParams extends org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.WipParams {
  /**
   @param maxDepth Maximum depth of async call stacks. Setting to <code>0</code> will effectively disable collecting async call stacks (default).
   */
  public SetAsyncCallStackDepthParams(long maxDepth) {
    this.put("maxDepth", maxDepth);
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.BasicConstants.Domain.DEBUGGER + ".setAsyncCallStackDepth";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
