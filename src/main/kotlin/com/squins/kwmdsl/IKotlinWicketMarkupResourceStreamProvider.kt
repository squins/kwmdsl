package com.squins.kwmdsl

import org.apache.wicket.util.resource.IResourceStream

/**
 * Implementations of this interface provide a resource stream with the text of markup defined using the DSL.
 */
interface IKotlinWicketMarkupResourceStreamProvider {
    /**
     * The resource stream containing the markup text.
     */
    val stream: IResourceStream
}
