package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

/**
 * The body markup of a fragment embedded in the markup of another component.
 *
 * @param TSupplier the component type having the supplier properties and functions for the components in the markup.
 */
interface IFragmentBodyMarkup<TSupplier: MarkupContainer> {
    /**
     * The text of the markup.
     */
    val markupText: String
}
