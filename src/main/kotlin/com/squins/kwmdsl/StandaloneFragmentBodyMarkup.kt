package com.squins.kwmdsl

import org.apache.wicket.markup.html.panel.Fragment

// TODO("If another body is supplied, the component hierarchies can be compared")
// TODO("Else document that there is no check to see if fragment markup bodies have the same component hierarchy.")
fun <TSupplier : Fragment> standaloneFragmentBodyMarkup(block: StandaloneFragmentBodyMarkupBuilder<TSupplier>.() -> Unit) =
    StandaloneFragmentBodyMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

class StandaloneFragmentBodyMarkup<TSupplier: Fragment> internal constructor(
    override val markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IFragmentBodyMarkup<TSupplier> {
    fun addToFragment(supplier: TSupplier) {
        addTo(supplier, supplier)
    }
}
