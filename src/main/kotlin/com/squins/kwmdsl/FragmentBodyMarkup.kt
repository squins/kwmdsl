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
    /**
     * Add the component hierarchy of the markup to [supplier], where `supplier` itself supplies the components.
     *
     * @param supplier a [Fragment] that supplies the components of the markup, and to which the component hierarchy
     * will be added. A runtime check will be made to ensure it is actually a `Fragment`.
     */
    fun addToFragment(supplier: TSupplier) {
        check(supplier is Fragment) {
            "The supplier must be a fragment for this function. Use `addToFragment(Fragment, TSupplier)` for fragments that to not provide markup themselves."
        }
        addTo(supplier, supplier)
    }

    /**
     * Add the component hierarchy of the markup to [fragment], where [supplier] supplies the components.
     *
     * @param fragment the [Fragment] to add the component hierarchy to.
     * @param supplier the supplier of the components.
     */
    fun addToFragment(fragment: Fragment, supplier: TSupplier) {
        addTo(supplier, fragment)
    }
}
