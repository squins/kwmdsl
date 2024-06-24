package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.resource.StringResourceStream

abstract class BaseRootMarkup<TSupplierFacade : MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children), IRootMarkup {
    override val stream = StringResourceStream(markupText)

    override fun isCompatibleVariant(variantMarkup: Markup<*>) = areCompatible(this, variantMarkup)

    override fun getComponentHierarchyString() =
        StringBuilder(500).also { getComponentHierarchyString(it, 0) }.toString()
}
