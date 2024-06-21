package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.model.IModel

abstract class KotlinWicketMarkupBorder(
    id: String,
    model: IModel<*>?
) : Border(id, model), IMarkupResourceStreamProvider {
    constructor(id: String) : this(id, null)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
