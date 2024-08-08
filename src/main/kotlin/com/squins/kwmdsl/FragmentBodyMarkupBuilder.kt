package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class FragmentBodyMarkupBuilder<TSupplier : MarkupContainer> : BaseFragmentBodyMarkupBuilder<TSupplier, FragmentBodyMarkup<TSupplier>>() {
    override fun createMarkup(
        markupText: String,
        children: List<ChildMarkup<TSupplier>>
    ): FragmentBodyMarkup<TSupplier> =
        FragmentBodyMarkup(markupText, children)
}
