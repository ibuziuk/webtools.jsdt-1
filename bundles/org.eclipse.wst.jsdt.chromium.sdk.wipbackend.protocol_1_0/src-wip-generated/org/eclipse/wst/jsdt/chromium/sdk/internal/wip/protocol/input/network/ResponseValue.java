// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.sdk.internal.wip.tools.protocolgenerator.Generator
// Origin: http://svn.webkit.org/repository/webkit/trunk/Source/WebCore/inspector/Inspector.json@96703

package org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.network;

/**
 HTTP response data.
 */
@org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonType
public interface ResponseValue {
  /**
   Response URL.
   */
  String url();

  /**
   HTTP response status code.
   */
  Number status();

  /**
   HTTP response status text.
   */
  String statusText();

  /**
   HTTP response headers.
   */
  org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.network.HeadersValue headers();

  /**
   HTTP response headers text.
   */
  @org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonOptionalField
  String headersText();

  /**
   Resource mimeType as determined by the browser.
   */
  String mimeType();

  /**
   Refined HTTP request headers that were actually transmitted over the network.
   */
  @org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonOptionalField
  org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.network.HeadersValue requestHeaders();

  /**
   HTTP request headers text.
   */
  @org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonOptionalField
  String requestHeadersText();

  /**
   Specifies whether physical connection was actually reused for this request.
   */
  boolean connectionReused();

  /**
   Physical connection id that was actually used for this request.
   */
  Number connectionId();

  /**
   Specifies that the request was served from the disk cache.
   */
  @org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonOptionalField
  Boolean fromDiskCache();

  /**
   Timing information for the given request.
   */
  @org.eclipse.wst.jsdt.chromium.sdk.internal.protocolparser.JsonOptionalField
  org.eclipse.wst.jsdt.chromium.sdk.internal.wip.protocol.input.network.ResourceTimingValue timing();

}
