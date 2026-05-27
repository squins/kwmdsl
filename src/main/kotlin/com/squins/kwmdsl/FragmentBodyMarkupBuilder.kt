package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import kotlin.reflect.KProperty1

/**
 * The builder for a body markup of a fragment embedded in the markup of another component.
 *
 * @param TSupplier the type having the properties and functions to get the Wicket components.
 */
class FragmentBodyMarkupBuilder<TSupplier : MarkupContainer> : MarkupBuilder<TSupplier>() {
    // TODO("Check if this is correct for fragment bodies")
    override fun getPathAsList() = emptyList<String>()

    // TODO("Check if this is correct for fragment bodies")
    override fun pathFromRootOfAsList(supplier: KProperty1<TSupplier, Component>) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    /**
     * Build the root markup using the concatenation of its parts and the tree of child markups associated with Wicket components.
     *
     * @return the root markup.
     */
    internal fun build(): FragmentBodyMarkup<TSupplier> {
        val markupText = StringBuilder(1_000).run {
            appendParts(this)
            toString()
        }
        return FragmentBodyMarkup(markupText, buildChildren())
    }
}
