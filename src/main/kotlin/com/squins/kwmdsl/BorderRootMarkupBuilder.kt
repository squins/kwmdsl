package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

class BorderRootMarkupBuilder<TSupplierFacade : Border> internal constructor() :
    BaseRootMarkupBuilder<TSupplierFacade, BorderRootMarkup<TSupplierFacade>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplierFacade>>) =
        BorderRootMarkup<TSupplierFacade>(markupText, children)
}
