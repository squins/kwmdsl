package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment

fun <TSupplier : MarkupContainer> fragmentBodyMarkup(block: FragmentBodyMarkupBuilder<TSupplier>.() -> Unit) =
    FragmentBodyMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

class FragmentBodyMarkup<TSupplier : MarkupContainer> internal constructor(
    override val markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : Markup<TSupplier>(children), IFragmentBodyMarkup<TSupplier> {
    fun addToFragment(fragment: Fragment, supplier: TSupplier) {
        addTo(supplier, fragment)
    }
}
