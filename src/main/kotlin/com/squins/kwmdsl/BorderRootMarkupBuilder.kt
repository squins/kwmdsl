package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

class BorderRootMarkupBuilder<TSupplierFacade : Border> internal constructor() :
    MarkupBuilder<TSupplierFacade>() {
    fun wicketHead(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:head>")
        block()
        currentTextPart.append("</wicket:head>")
    }

    internal fun build() =
        BorderRootMarkup<TSupplierFacade>(StringBuilder(1_000).apply {
            appendParts(this)
        }, buildChildren())
}
