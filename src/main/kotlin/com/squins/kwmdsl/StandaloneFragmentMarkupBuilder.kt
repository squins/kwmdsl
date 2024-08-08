package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.markup.html.panel.Fragment

class StandaloneFragmentMarkupBuilder<TSupplier: Fragment> : MarkupBuilder<TSupplier>() {
    init {
        // Searches for the fragments start at 1, so make sure there is at least 1 node before the fragments.
        comment("")
    }

    override fun getPathAsList() = emptyList<String>()

    override fun pathFromRootOfAsList(supplier: (TSupplier) -> Component) =
        children.firstNotNullOfOrNull { it.pathOfAsList(supplier) }

    internal fun build(): StandaloneFragmentMarkup<TSupplier> {
        val markupText = StringBuilder(1_000).apply {
            appendParts(this)
        }.toString()
        return StandaloneFragmentMarkup(markupText, buildChildren())
    }
}
