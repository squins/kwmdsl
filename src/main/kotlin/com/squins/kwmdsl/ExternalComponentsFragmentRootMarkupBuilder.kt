package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class ExternalComponentsFragmentRootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    BaseRootMarkupBuilder<TSupplierFacade, ExternalComponentsFragmentRootMarkup<TSupplierFacade>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplierFacade>>) =
        ExternalComponentsFragmentRootMarkup(markupText, children)
}
