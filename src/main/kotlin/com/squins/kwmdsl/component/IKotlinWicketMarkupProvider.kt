package com.squins.kwmdsl.component

import com.squins.kwmdsl.RootMarkup
import org.apache.wicket.MarkupContainer

interface IKotlinWicketMarkupProvider<TSupplierFacade : MarkupContainer> {
    fun getKotlinWicketMarkup(): RootMarkup<TSupplierFacade>
}
