package com.squins.kwmdsl.component

import java.util.Locale

internal fun StringBuilder.appendVariantSuffix(
    style: String?,
    variation: String?,
    locale: Locale?
) {
    if (variation != null) {
        append('_')
        append(variation)
    }

    if (style != null) {
        append('_')
        append(style)
    }

    // See `LocaleResourceNameIterator`
    if (locale != null) {
        append('_')
        append(locale.language)
        if (!locale.country.isNullOrBlank()) {
            append('_')
            append(locale.country)
            if (!locale.variant.isNullOrBlank()) {
                append('_')
                append(locale.variant)
            }
        }
    }
}
