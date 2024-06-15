package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel

abstract class KotlinWicketMarkupPanel<TSupplierFacade : KotlinWicketMarkupPanel<TSupplierFacade>>(
    id: String,
    model: IModel<*>?
) : Panel(id, model), IKotlinWicketMarkupProvider<TSupplierFacade>,
    IMarkupResourceStreamProvider {
    constructor(id: String) : this(id, null)

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
