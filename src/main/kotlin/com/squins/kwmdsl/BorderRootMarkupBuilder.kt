package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

/**
 * The builder for a border root markup.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 */
class BorderRootMarkupBuilder<TSupplierFacade : Border> internal constructor() :
    BaseRootMarkupBuilder<TSupplierFacade, BorderRootMarkup<TSupplierFacade>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplierFacade>>) =
        BorderRootMarkup(markupText, children)
}
