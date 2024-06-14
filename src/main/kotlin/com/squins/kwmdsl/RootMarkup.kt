package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.resource.StringResourceStream

fun <TSupplierFacade : MarkupContainer> markup(block: RootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    RootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

class RootMarkup<TSupplierFacade : MarkupContainer> internal constructor(
    markupTextBuilder: StringBuilder,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children) {
    val stream = StringResourceStream(markupTextBuilder.toString())

    fun addTo(rootComponent: TSupplierFacade) {
        addTo(rootComponent, rootComponent)
    }
}
