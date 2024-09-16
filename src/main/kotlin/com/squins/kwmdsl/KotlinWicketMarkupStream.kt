package com.squins.kwmdsl

import com.squins.kwmdsl.component.appendVariantSuffix
import org.apache.wicket.util.lang.Bytes
import org.apache.wicket.util.resource.IFixedLocationResourceStream
import org.apache.wicket.util.resource.IResourceStream
import java.io.ByteArrayInputStream
import java.time.Instant
import java.util.Locale
import kotlin.reflect.KClass

internal class KotlinWicketMarkupStream(
    private val supplierClass: KClass<*>,
    private val style: String?,
    private val variation: String?,
    private val locale: Locale?,
    markupText: String,
) : IResourceStream, IFixedLocationResourceStream {
    private val location = StringBuilder(250).run {
        append("kwmdsl:")
        append(supplierClass.qualifiedName)
        appendVariantSuffix(style, variation, locale)
        append(".html")

        toString()
    }

    private val utf8Bytes = markupText.toByteArray()

    override fun getInputStream() = ByteArrayInputStream(utf8Bytes)

    override fun close() {
        // No action needed. Nothing to close.
    }

    override fun locationAsString() = location

    override fun getContentType() = null

    override fun getLocale() = locale

    override fun setLocale(locale: Locale?) {
        throw UnsupportedOperationException()
    }

    override fun getStyle() = style

    override fun setStyle(p0: String?) {
        throw UnsupportedOperationException()
    }

    override fun getVariation() = variation

    override fun setVariation(p0: String?) {
        throw UnsupportedOperationException()
    }

    override fun length(): Bytes = Bytes.bytes(utf8Bytes.size.toLong())

    override fun lastModifiedTime(): Instant = LAST_MODIFIED_TIME
}

// TODO: explain why a time is returned: so Wicket can detect newer markup when a new version is rolled out. And explain
// why this time is chosen: changes are no longer possible after the JVM has started
private val LAST_MODIFIED_TIME = Instant.now()
