package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

/**
 * A builder function for the markup of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border] : [borderMarkup]
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
fun <TSupplierFacade : MarkupContainer> markup(block: RootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    RootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

/**
 * Root markup for a [MarkupContainer].
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param markupText the markup text of the container.
 * @param children the tree of child markups associated with a Wicket component.
 */
class RootMarkup<TSupplierFacade : MarkupContainer> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : BaseRootMarkup<TSupplierFacade>(markupText, children) {
    fun addTo(rootComponent: TSupplierFacade) {
        addTo(rootComponent, rootComponent)
    }
}
