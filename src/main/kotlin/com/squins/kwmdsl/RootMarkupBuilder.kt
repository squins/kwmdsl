package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    BaseRootMarkupBuilder<TSupplierFacade, RootMarkup<TSupplierFacade>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplierFacade>>) =
        RootMarkup<TSupplierFacade>(markupText, children)
}
