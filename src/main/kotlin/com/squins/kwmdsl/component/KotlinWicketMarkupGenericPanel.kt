package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.panel.GenericPanel
import org.apache.wicket.model.IModel

/**
 * A convenience base class for [GenericPanel]s that want to use the DSL. This class handles the creation of the markup resource stream, so the subclass only has to define the markup and ask the markup to add the component hierarchy to itself.
 *
 * If another base class is already used for a generic panel, the generic panel can still use the DSL by implementing [IMarkupResourceStreamProvider] and calling [findMarkup].
 *
 * @param TModelValue the model value type.
 * @param id the ID of this component.
 * @param model the component's model.
 */
abstract class KotlinWicketMarkupGenericPanel<TModelValue>(
    id: String,
    model: IModel<TModelValue>?
) : GenericPanel<TModelValue>(id, model), IMarkupResourceStreamProvider {
    /**
     * Create an instance using only an ID.
     *
     * @param id the ID of this component.
     */
    constructor(id: String) : this(id, null)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
