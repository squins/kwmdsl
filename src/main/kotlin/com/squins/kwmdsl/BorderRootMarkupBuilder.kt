package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border
import java.util.Locale
import kotlin.reflect.KClass

/**
 * The builder for a border root markup.
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 */
class BorderRootMarkupBuilder<TSupplier : Border> internal constructor() :
    BaseRootMarkupBuilder<TSupplier, BorderRootMarkup<TSupplier>>() {
    override fun createMarkup(
        supplierClass: KClass<TSupplier>,
        style: String?,
        variation: String?,
        locale: Locale?,
        markupText: String,
        children: List<ChildMarkup<TSupplier>>
    ): BorderRootMarkup<TSupplier> =
        BorderRootMarkup(supplierClass, style, variation, locale, markupText, children)
}
