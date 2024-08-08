package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment

fun <TSupplier : MarkupContainer> externalComponentsFragmentMarkup(block: ExternalComponentsFragmentRootMarkupBuilder<TSupplier>.() -> Unit) =
    ExternalComponentsFragmentRootMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

class ExternalComponentsFragmentRootMarkup<TSupplier: MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : BaseRootMarkup<TSupplier>(markupText, children) {
    fun addToFragment(fragment: Fragment, parent: TSupplier) {
        addTo(parent, fragment)
    }
}
