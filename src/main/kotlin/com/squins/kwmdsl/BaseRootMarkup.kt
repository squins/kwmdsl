package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.resource.StringResourceStream

/**
 * A base class for root markups, containing functionality provided by all root markups.
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param markupText the markup text.
 * @param children the child markups that are associated with a Wicket component.
 */
abstract class BaseRootMarkup<TSupplier : MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IRootMarkup {
    override val stream = StringResourceStream(markupText)

    override fun isCompatible(other: Markup<*>) = areCompatible(this, other)

    override fun getComponentHierarchyString() =
        StringBuilder(500).also { getComponentHierarchyString(it, 0) }.toString()
}
