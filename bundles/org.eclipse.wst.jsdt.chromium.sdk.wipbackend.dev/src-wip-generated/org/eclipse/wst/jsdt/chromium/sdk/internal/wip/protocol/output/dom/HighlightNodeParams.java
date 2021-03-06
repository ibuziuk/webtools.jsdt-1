// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.dom;

/**
Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
 */
public class HighlightNodeParams extends org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.WipParams {
  /**
   @param highlightConfig A descriptor for the highlight appearance.
   @param nodeIdOpt Identifier of the node to highlight.
   @param backendNodeIdOpt Identifier of the backend node to highlight.
   @param objectIdOpt JavaScript object id of the node to be highlighted.
   */
  public HighlightNodeParams(org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.dom.HighlightConfigParam highlightConfig, Long/*See org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.common.dom.NodeIdTypedef*/ nodeIdOpt, Long/*See org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.common.dom.BackendNodeIdTypedef*/ backendNodeIdOpt, String/*See org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.common.runtime.RemoteObjectIdTypedef*/ objectIdOpt) {
    this.put("highlightConfig", highlightConfig);
    if (nodeIdOpt != null) {
      this.put("nodeId", nodeIdOpt);
    }
    if (backendNodeIdOpt != null) {
      this.put("backendNodeId", backendNodeIdOpt);
    }
    if (objectIdOpt != null) {
      this.put("objectId", objectIdOpt);
    }
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.BasicConstants.Domain.DOM + ".highlightNode";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
