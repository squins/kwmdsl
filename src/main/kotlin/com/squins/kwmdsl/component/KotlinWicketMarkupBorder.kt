package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.model.IModel

abstract class KotlinWicketMarkupBorder<TSupplierFacade : KotlinWicketMarkupBorder<TSupplierFacade>>(
    id: String,
    model: IModel<*>?
) : Border(id, model), IKotlinWicketMarkupProvider<TSupplierFacade>,
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
