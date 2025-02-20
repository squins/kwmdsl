package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.panel.GenericPanel
import org.apache.wicket.model.IModel

abstract class KotlinWicketMarkupPanel<TModelValue>(
    id: String,
    model: IModel<TModelValue>?
) : GenericPanel<TModelValue>(id, model), IMarkupResourceStreamProvider {
    constructor(id: String) : this(id, null)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
