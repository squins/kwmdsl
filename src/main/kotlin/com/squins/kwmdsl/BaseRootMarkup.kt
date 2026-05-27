package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.resource.IResourceStream
import java.util.Locale
import kotlin.reflect.KClass

/**
 * A base class for root markups, containing functionality provided by all root markups.
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param supplierClass the class of the component with the member properties and functions supplying the components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param markupText the markup text.
 * @param children the child markups that are associated with a Wicket component.
 */
abstract class BaseRootMarkup<TSupplier : MarkupContainer> internal constructor(
    supplierClass: KClass<TSupplier>,
    style: String?,
    variation: String?,
    locale: Locale?,
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IRootMarkup {
    override val stream: IResourceStream =
        KotlinWicketMarkupStream(
            supplierClass,
            style,
            variation,
            locale,
            markupText,
        )

    override fun isCompatible(other: Markup<*>) = areCompatible(this, other)

    override fun getComponentHierarchyString() =
        StringBuilder(1_000).run {
            getComponentHierarchyString(this, 0)
            toString()
        }
}
