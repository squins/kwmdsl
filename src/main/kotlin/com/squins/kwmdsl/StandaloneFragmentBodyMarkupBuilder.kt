package com.squins.kwmdsl

import org.apache.wicket.markup.html.panel.Fragment

class StandaloneFragmentBodyMarkupBuilder<TSupplier : Fragment> : BaseFragmentBodyMarkupBuilder<TSupplier, StandaloneFragmentBodyMarkup<TSupplier>>() {
    override fun createMarkup(
        markupText: String,
        children: List<ChildMarkup<TSupplier>>
    ): StandaloneFragmentBodyMarkup<TSupplier> =
        StandaloneFragmentBodyMarkup(markupText, children)
}
