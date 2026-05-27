package com.squins.kwmdsl

import org.apache.wicket.markup.html.panel.Fragment
import org.apache.wicket.util.resource.StringResourceStream

/**
 * A builder function for the markup of a stand-alone [Fragment] (that is a fragment with its own markup) that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 * * Other components: [markup]
 *
 * @param TSupplier the fragment type having the properties and functions to get the Wicket components.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the stand-alone fragment markup.
 */
fun <TSupplier : Fragment> standaloneFragmentMarkup(block: StandaloneFragmentMarkupBuilder<TSupplier>.() -> Unit) =
    StandaloneFragmentMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

/**
 * Markup for a stand-alone [Fragment], that is, a fragment with its own markup.
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param markupText the markup text of the container.
 * @param children the tree of child markups associated with a Wicket component.
 */
class StandaloneFragmentMarkup<TSupplier : Fragment> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IKotlinWicketMarkupResourceStreamProvider {
    override val stream = StringResourceStream(markupText)
}
