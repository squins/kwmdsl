package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.markup.html.panel.Fragment
import kotlin.reflect.KProperty1

/**
 * A builder for the markup of a stand-alone [Fragment], that is, a fragment with its own markup.
 *
 * @param TSupplier the `Fragment` type the markup is for. This component type also defines the supplier properties and functions that create the descendent components.
 */
class StandaloneFragmentMarkupBuilder<TSupplier: Fragment> : MarkupBuilder<TSupplier>() {
    init {
        // Searches for the fragments start at 1, so make sure there is at least 1 node before the fragments.
        comment("")
    }

    override fun getPathAsList() = emptyList<String>()

    override fun pathFromRootOfAsList(supplier: KProperty1<TSupplier, Component>) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    /**
     * Build the stand-alone [Fragment] markup using the concatenation of its parts and the tree of child markups associated with Wicket components.
     *
     * @return the markup for the stand-alone `Fragment`.
     */
    internal fun build(): StandaloneFragmentMarkup<TSupplier> {
        val markupText = StringBuilder(1_000).run {
            appendParts(this)
            toString()
        }
        return StandaloneFragmentMarkup(markupText, buildChildren())
    }
}
