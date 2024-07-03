package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment

fun <TSupplierFacade : MarkupContainer> externalComponentsFragmentMarkup(block: ExternalComponentsFragmentRootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    ExternalComponentsFragmentRootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

class ExternalComponentsFragmentRootMarkup<TSupplierFacade: MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : BaseRootMarkup<TSupplierFacade>(markupText, children) {
    fun addToFragment(fragment: Fragment, parent: TSupplierFacade) {
        addTo(parent, fragment)
    }
}
