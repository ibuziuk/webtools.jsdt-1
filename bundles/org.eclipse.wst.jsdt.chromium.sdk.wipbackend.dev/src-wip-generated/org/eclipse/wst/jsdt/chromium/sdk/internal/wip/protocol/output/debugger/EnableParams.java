// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.debugger;

/**
Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
 */
public class EnableParams extends org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.WipParams {
  public EnableParams() {
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.BasicConstants.Domain.DEBUGGER + ".enable";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
