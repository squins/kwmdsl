package com.squins.kwmdsl.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.Markup
import org.apache.wicket.markup.html.panel.Fragment
import org.apache.wicket.model.IModel

/**
 * A convenience base class for [Fragment]s that want to use the DSL. This class handles the creation of the markup resource stream, so the subclass only has to define the markup and ask the markup to add the component hierarchy to itself.
 *
 * If another base class is already used for a fragment, the fragment can still use the DSL by implementing [IMarkupResourceStreamProvider] and calling [findMarkup].
 *
 * @param id the ID of this component.
 * @param markupId the associated ID of the associated markup fragment.
 * @param model the component's model.
 */
abstract class KotlinWicketMarkupFragment(
    id: String,
    markupId: String,
    model: IModel<*>?
) : Fragment(id, markupId, null, model), IMarkupResourceStreamProvider {
    /**
     * Create an instance using only an ID and a markup ID.
     *
     * @param id the ID of this component.
     * @param markupId the associated ID of the associated markup fragment.
     */
    constructor(id: String, markupId: String) : this(id, markupId, null)

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    override fun chooseMarkup(provider: MarkupContainer?): Markup = associatedMarkup

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
