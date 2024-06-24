package com.squins.kwmdsl

import org.apache.wicket.util.resource.StringResourceStream

interface IRootMarkup {
    val stream: StringResourceStream

    fun isCompatibleVariant(variantMarkup: Markup<*>): Boolean

    fun getComponentHierarchyString(): String
}
