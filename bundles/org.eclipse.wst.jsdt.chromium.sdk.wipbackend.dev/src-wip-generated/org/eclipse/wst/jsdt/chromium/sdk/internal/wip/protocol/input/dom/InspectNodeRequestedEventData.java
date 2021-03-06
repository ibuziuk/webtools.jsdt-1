// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.dom;

/**
 Fired when the node should be inspected. This happens after call to <code>setInspectMode</code>.
 */
@org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonType
public interface InspectNodeRequestedEventData {
  /**
   Id of the node to inspect.
   */
  long/*See org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.common.dom.BackendNodeIdTypedef*/ backendNodeId();

  public static final org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.WipEventType<org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.dom.InspectNodeRequestedEventData> TYPE
      = new org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.WipEventType<org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.dom.InspectNodeRequestedEventData>("DOM.inspectNodeRequested", org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.dom.InspectNodeRequestedEventData.class) {
    @Override public org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.dom.InspectNodeRequestedEventData parse(org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.WipGeneratedParserRoot parser, org.json.simple.JSONObject obj) throws org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonProtocolParseException {
      return parser.parseDOMInspectNodeRequestedEventData(obj);
    }
  };
}
