package com.squins.kwmdsl

import org.apache.wicket.markup.html.panel.Fragment
import org.apache.wicket.util.resource.StringResourceStream

fun <TSupplier : Fragment> standaloneFragmentMarkup(block: StandaloneFragmentMarkupBuilder<TSupplier>.() -> Unit) =
    StandaloneFragmentMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

class StandaloneFragmentMarkup<TSupplier : Fragment> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IKotlinWicketMarkupResourceStreamProvider {
    override val stream = StringResourceStream(markupText)
}
