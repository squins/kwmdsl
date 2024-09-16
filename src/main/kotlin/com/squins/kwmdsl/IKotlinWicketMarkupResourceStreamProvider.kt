package com.squins.kwmdsl

import org.apache.wicket.util.resource.IResourceStream

interface IKotlinWicketMarkupResourceStreamProvider {
    /**
     * The resource stream containing the markup text.
     */
    val stream: IResourceStream
}
