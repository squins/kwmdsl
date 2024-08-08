package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class ExternalComponentsFragmentRootMarkupBuilder<TSupplier : MarkupContainer> internal constructor() :
    BaseRootMarkupBuilder<TSupplier, ExternalComponentsFragmentRootMarkup<TSupplier>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplier>>) =
        ExternalComponentsFragmentRootMarkup(markupText, children)
}
