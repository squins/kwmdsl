package com.squins.kwmdsl

/**
 * Add an HTML document type declaration with document type `html` to the markup.
 */
fun MarkupBuilder<*>.docTypeHtml() {
    docType("html")
}

/**
 * Add an HTML `meta` element named `viewport` to the markup, specifying that the width is the device width, and the initial scale is 1:
 *
 * ```html
 * <meta name="viewport" content="width=device-width, initial-scale=1">
 * ```
 */
fun MarkupBuilder<*>.metaViewportDeviceWidthInitialScale1() {
    meta(
        attr("name", "viewport"),
        attr("content", "width=device-width, initial-scale=1"),
    )
}

/**
 * Add an HTML `meta` element that is the equivalent of HTTP header `Content-type`, specifying that the document contains HTML text encoded in UTF-8:
 *
 * ```html
 * <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 * ```
 */
fun MarkupBuilder<*>.metaTextHtmlUtf8() {
    meta(
        attr("http-equiv", "Content-Type"),
        attr("content", "text/html; charset=UTF-8"),
    )
}
