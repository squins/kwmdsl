package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

/**
 * A builder function for the markup of a [`wicket:border`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_surrounding_existing_markup_with_border) container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the border root markup.
 */
fun <TSupplier : Border> borderMarkup(block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    BorderRootMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

/**
 * Root markup for a [Border].
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 * @param markupText the markup text of the border.
 * @param children the tree of child markups associated with a Wicket component.
 */
class BorderRootMarkup<TSupplier : Border> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : BaseRootMarkup<TSupplier>(markupText, children) {
    /**
     * Adds the child Wicket components to the [Border] instance. The direct children will be added to the border using [Border.addToBorder], and descendents will be added to their parent using [MarkupContainer.add][org.apache.wicket.MarkupContainer.add].
     *
     * @param rootBorder the border to add the children to.
     */
    fun addToBorder(rootBorder: TSupplier) {
        children.forEach { childMarkup ->
            val component = childMarkup.retrieveComponent(rootBorder)
            rootBorder.addToBorder(component)
            addChildMarkupToComponentIfContainer(childMarkup, rootBorder, component)
        }
    }
}
