package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.GenericWebPage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class KotlinWicketMarkupWebPage<TModelValue> : GenericWebPage<TModelValue>, IMarkupResourceStreamProvider {
    constructor() : super()
    constructor(pageParameters: PageParameters?) : super(pageParameters)
    constructor(model: IModel<TModelValue>): super(model)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
