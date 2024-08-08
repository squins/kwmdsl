package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

// TODO("Delete if no members are added")
abstract class BaseFragmentBodyMarkup<TSupplier : MarkupContainer> internal constructor(
    children: List<ChildMarkup<TSupplier>>
) : Markup<TSupplier>(children), IFragmentBodyMarkup<TSupplier>
