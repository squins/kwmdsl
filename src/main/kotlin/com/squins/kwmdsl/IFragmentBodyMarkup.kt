package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

interface IFragmentBodyMarkup<TSupplier: MarkupContainer> {
    val markupText: String
}
