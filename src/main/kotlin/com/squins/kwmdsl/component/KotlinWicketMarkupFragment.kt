package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.panel.Fragment
import org.apache.wicket.model.IModel

abstract class KotlinWicketMarkupFragment(
    id: String,
    markupId: String,
    model: IModel<*>?
) : Fragment(id, markupId, null, model), IMarkupResourceStreamProvider {
    constructor(id: String, markupId: String) : this(id, markupId, null)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    override fun chooseMarkup(provider: MarkupContainer?) = getAssociatedMarkup()

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
