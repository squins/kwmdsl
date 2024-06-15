package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class KotlinWicketMarkupWebPage<TSupplierFacade : KotlinWicketMarkupWebPage<TSupplierFacade>> : WebPage,
    IKotlinWicketMarkupProvider<TSupplierFacade>, IMarkupResourceStreamProvider {
    constructor() : super()

    constructor(model: IModel<*>?) : super(model)

    constructor(parameters: PageParameters) : super(parameters)

    override fun onInitialize() {
        super.onInitialize()

        @Suppress("UNCHECKED_CAST")
        getKotlinWicketMarkup().addTo(this as TSupplierFacade)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        getKotlinWicketMarkup().stream

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
