package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.markup.html.border.Border

class BorderRootMarkupBuilder<TSupplierFacade : Border> internal constructor() :
    MarkupBuilder<TSupplierFacade>() {
    override fun getPathAsList() = emptyList<String>()

    override fun pathFromRootOfAsList(supplier: (TSupplierFacade) -> Component) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    internal fun build() =
        BorderRootMarkup<TSupplierFacade>(StringBuilder(1_000).apply {
            appendParts(this)
        }, buildChildren())
}
