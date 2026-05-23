package com.squins.kwmdsl

import org.apache.wicket.Application
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment

fun <TSupplier : MarkupContainer> fragmentBodyMarkup(block: FragmentBodyMarkupBuilder<TSupplier>.() -> Unit) =
    FragmentBodyMarkupBuilder<TSupplier>().run {
        block()
        build()
    }

fun <TSupplier : MarkupContainer> fragmentBodyMarkup(
    markupOfWhichToMatchComponentHierarchy: FragmentBodyMarkup<TSupplier>,
    block: FragmentBodyMarkupBuilder<TSupplier>.() -> Unit
) =
    FragmentBodyMarkupBuilder<TSupplier>().run {
        block()
        build()
    }
        .apply {
            if (Application.get()?.usesDevelopmentConfig() == true) {
                check(areCompatible(this, markupOfWhichToMatchComponentHierarchy)) {
                    """The children do not match the children of the markup that must be matched. Component hierarchy:
${
    StringBuilder(500).also {
        getComponentHierarchyString(it, 0)
    }
}
Component hierarchy that must be matched:
${
    StringBuilder(500).also {
        markupOfWhichToMatchComponentHierarchy.getComponentHierarchyString(it, 0)
    }
}
"""
                }
            }
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
