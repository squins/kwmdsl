package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.resource.StringResourceStream

abstract class BaseRootMarkup<TSupplierFacade : MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children), IRootMarkup {
    override val stream = StringResourceStream(markupText)
}
