package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

class FragmentBodyMarkup<TSupplier : MarkupContainer> internal constructor(
    override val markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : BaseFragmentBodyMarkup<TSupplier>(children) {
}
