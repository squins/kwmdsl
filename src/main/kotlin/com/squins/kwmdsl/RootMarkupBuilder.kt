package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplier : MarkupContainer> internal constructor() :
    BaseRootMarkupBuilder<TSupplier, RootMarkup<TSupplier>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplier>>) =
        RootMarkup(markupText, children)
}
