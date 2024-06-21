package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

fun <TSupplierFacade : MarkupContainer> markup(block: RootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    RootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

class RootMarkup<TSupplierFacade : MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : BaseRootMarkup<TSupplierFacade>(markupText, children) {
    fun addTo(rootComponent: TSupplierFacade) {
        addTo(rootComponent, rootComponent)
    }
}
