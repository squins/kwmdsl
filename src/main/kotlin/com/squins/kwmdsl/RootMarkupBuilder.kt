package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    MarkupBuilder<TSupplierFacade>(StringBuilder(1_000)) {
    fun wicketHead(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:head>")
        block()
        builder.append("</wicket:head>")
    }

    internal fun build() = RootMarkup<TSupplierFacade>(builder, buildChildren())
}
