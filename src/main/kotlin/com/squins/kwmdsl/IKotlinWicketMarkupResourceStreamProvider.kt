package com.squins.kwmdsl

import org.apache.wicket.util.resource.StringResourceStream

interface IKotlinWicketMarkupResourceStreamProvider {
    /**
     * The resource stream containing the markup text.
     */
    val stream: StringResourceStream
}
