package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import java.util.Locale
import kotlin.reflect.KClass

class RootMarkupBuilder<TSupplier : MarkupContainer> internal constructor() :
    BaseRootMarkupBuilder<TSupplier, RootMarkup<TSupplier>>() {
    override fun createMarkup(
        supplierClass: KClass<TSupplier>,
        style: String?,
        variation: String?,
        locale: Locale?,
        markupText: String,
        children: List<ChildMarkup<TSupplier>>
    ): RootMarkup<TSupplier> =
        RootMarkup(supplierClass, style, variation, locale, markupText, children)
}
