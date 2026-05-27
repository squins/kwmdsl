package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import java.util.Locale
import kotlin.reflect.KClass

/**
 * A builder for the root markup of a component.
 *
 * @param TSupplier the component type the markup is for. This component type also defines the supplier properties and functions that create the descendent components.
 */
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
