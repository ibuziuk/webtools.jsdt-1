// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.dom;

/**
Sets attribute for an element with given id.
 */
public class SetAttributeValueParams extends org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.output.WipParams {
  /**
   @param nodeId Id of the element to set attribute for.
   @param name Attribute name.
   @param value Attribute value.
   */
  public SetAttributeValueParams(long/*See org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.common.dom.NodeIdTypedef*/ nodeId, String name, String value) {
    this.put("nodeId", nodeId);
    this.put("name", name);
    this.put("value", value);
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.BasicConstants.Domain.DOM + ".setAttributeValue";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
