package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    MarkupBuilder<TSupplierFacade>(StringBuilder(1_000)) {
    fun wicketBorder(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:border>")
        block()
        builder.append("</wicket:border>")
    }

    fun wicketHead(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:head>")
        block()
        builder.append("</wicket:head>")
    }

    fun wicketPanel(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:panel>")
        block()
        builder.append("</wicket:panel>")
    }

    internal fun build() = RootMarkup<TSupplierFacade>(builder, buildChildren())
}
