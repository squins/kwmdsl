package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

abstract class BaseFragmentBodyMarkupBuilder<TSupplier: MarkupContainer, TMarkup : IFragmentBodyMarkup<TSupplier>> : MarkupBuilder<TSupplier>() {
    // TODO("Check if this is correct for fragment bodies")
    override fun getPathAsList() = emptyList<String>()

    // TODO("Check if this is correct for fragment bodies")
    override fun pathFromRootOfAsList(supplier: (TSupplier) -> Component) =
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

    internal abstract fun createMarkup(
        markupText: String,
        children: List<ChildMarkup<TSupplier>>
    ): TMarkup
}
