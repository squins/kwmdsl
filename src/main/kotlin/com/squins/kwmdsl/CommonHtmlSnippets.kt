package com.squins.kwmdsl

/**
 * Add a document type declaration with document type `html` to the markup.
 */
fun MarkupBuilder<*>.docTypeHtml() {
    docType("html")
}

fun MarkupBuilder<*>.metaTextHtmlUtf8() {
    meta(
        "http-equiv" to "Content-Type",
        "content" to "text/html; charset=UTF-8"
    )
}
