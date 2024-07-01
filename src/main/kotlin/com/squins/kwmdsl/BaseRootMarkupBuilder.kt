package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

/**
 * A base class for root markup builders, containing functionality provided and shared by all root markup builders.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param TMarkup the markup type built by this builder.
 */
abstract class BaseRootMarkupBuilder<TSupplierFacade : MarkupContainer, TMarkup : BaseRootMarkup<TSupplierFacade>> protected constructor() :
    MarkupBuilder<TSupplierFacade>() {
    override fun getPathAsList() = emptyList<String>()

    override fun pathFromRootOfAsList(supplier: (TSupplierFacade) -> Component) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    /**
     * Build the root markup using the concatenation of its parts and the tree of child markups associated with Wicket components.
     *
     * @return the root markup.
     */
    internal fun build(): TMarkup {
        val markupText = StringBuilder(1_000).apply {
            appendParts(this)
        }.toString()
        return createMarkup(markupText, buildChildren())
    }

    /**
     * Factory function to create an instance of the actual root markup type.
     *
     * @param markupText the text of the root markup to create.
     * @param children the tree of child markups associated with a Wicket component.
     * @return the root markup.
     */
    internal abstract fun createMarkup(
        markupText: String,
        children: List<ChildMarkup<TSupplierFacade>>
    ): TMarkup
}
