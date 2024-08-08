package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

/**
 * The builder for a border root markup.
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 */
class BorderRootMarkupBuilder<TSupplier : Border> internal constructor() :
    BaseRootMarkupBuilder<TSupplier, BorderRootMarkup<TSupplier>>() {
    override fun createMarkup(markupText: String, children: List<ChildMarkup<TSupplier>>) =
        BorderRootMarkup(markupText, children)
}
