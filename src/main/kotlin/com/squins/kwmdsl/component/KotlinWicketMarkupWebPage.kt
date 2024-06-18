package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class KotlinWicketMarkupWebPage : WebPage, IMarkupResourceStreamProvider {
    constructor() : super()

    constructor(model: IModel<*>?) : super(model)

    constructor(parameters: PageParameters) : super(parameters)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findDslMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
