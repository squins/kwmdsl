package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

abstract class BaseRootMarkupBuilder<TSupplierFacade : MarkupContainer, TMarkup : BaseRootMarkup<TSupplierFacade>> protected constructor() :
    MarkupBuilder<TSupplierFacade>() {
    override fun getPathAsList() = emptyList<String>()

    override fun pathFromRootOfAsList(supplier: (TSupplierFacade) -> Component) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    internal fun build(): TMarkup {
        val markupText = StringBuilder(1_000).apply {
            appendParts(this)
        }.toString()
        return createMarkup(markupText, buildChildren())
    }

    internal abstract fun createMarkup(
        markupText: String,
        children: List<ChildMarkup<TSupplierFacade>>
    ): TMarkup
}
