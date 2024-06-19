package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class RootMarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() :
    MarkupBuilder<TSupplierFacade>() {
    internal fun build() =
        RootMarkup<TSupplierFacade>(StringBuilder(1_000).apply {
            appendParts(this)
        }, buildChildren())
}
