package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.GenericWebPage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters

/**
 * A convenience base class for [GenericWebPage]s that want to use the DSL. This class handles the creation of the markup resource stream, so the subclass only has to define the markup and ask the markup to add the component hierarchy to itself.
 *
 * If another base class is already used for a generic web page, the generic web page can still use the DSL by implementing [IMarkupResourceStreamProvider] and calling [findMarkup].
 *
 * @param TModelValue the model value type.
 */
abstract class KotlinWicketMarkupGenericWebPage<TModelValue> : GenericWebPage<TModelValue>, IMarkupResourceStreamProvider {
    /**
     * Create an instance without page parameters and without a model.
     */
    constructor() : super()

    /**
     * Create an instance with the given page parameters.
     *
     * @param pageParameters the page parameters.
     */
    constructor(pageParameters: PageParameters?) : super(pageParameters)

    /**
     * Create an instance with the given model.
     *
     * @param model the model.
     */
    constructor(model: IModel<TModelValue>): super(model)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
