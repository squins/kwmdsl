package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment

// TODO("If another body is supplied, the component hierarchies can be compared")
// TODO("Else document that there is no check to see if fragment markup bodies have the same component hierarchy.")
fun <TSupplier : MarkupContainer> fragmentBodyMarkup(block: FragmentBodyMarkupBuilder<TSupplier>.() -> Unit) =
    FragmentBodyMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

class FragmentBodyMarkup<TSupplier : MarkupContainer> internal constructor(
    override val markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IFragmentBodyMarkup<TSupplier> {
    // TODO("Document: for situations where the markup is provided by fragment itself")
    fun addToFragment(supplier: TSupplier) {
        check(supplier is Fragment) {
            "The supplier must be a fragment for this function. Use `addToFragment(Fragment, TSupplier)` for fragments that to not provide markup themselves."
        }
        addTo(supplier, supplier)
    }

    // TODO("Document: for situations where the markup is not of the fragment itself. This is most common")
    fun addToFragment(fragment: Fragment, supplier: TSupplier) {
        addTo(supplier, fragment)
    }
}
