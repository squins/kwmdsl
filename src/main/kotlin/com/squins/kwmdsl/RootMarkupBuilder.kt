package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    MarkupBuilder<TSupplierFacade>() {
    fun wicketHead(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:head>")
        block()
        currentTextPart.append("</wicket:head>")
    }

    internal fun build() =
        RootMarkup<TSupplierFacade>(StringBuilder(1_000).apply {
            appendParts(this)
        }, buildChildren())
}
