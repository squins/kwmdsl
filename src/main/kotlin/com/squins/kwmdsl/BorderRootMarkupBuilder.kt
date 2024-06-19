package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

class BorderRootMarkupBuilder<TSupplierFacade : Border> internal constructor() :
    MarkupBuilder<TSupplierFacade>() {
    internal fun build() =
        BorderRootMarkup<TSupplierFacade>(StringBuilder(1_000).apply {
            appendParts(this)
        }, buildChildren())
}
