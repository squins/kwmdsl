package com.squins.kwmdsl

import com.squins.kwmdsl.component.appendVariantSuffix
import org.apache.wicket.util.lang.Bytes
import org.apache.wicket.util.resource.IFixedLocationResourceStream
import org.apache.wicket.util.resource.IResourceStream
import java.io.ByteArrayInputStream
import java.time.Instant
import java.util.Locale
import kotlin.reflect.KClass

/**
 * A resource stream for markup defined using the DSL.
 *
 * @param supplierClass the class of the component with the member properties and functions supplying the components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param markupText the markup text.
 */
internal class KotlinWicketMarkupStream(
    private val supplierClass: KClass<*>,
    private val style: String?,
    private val variation: String?,
    private val locale: Locale?,
    markupText: String,
) : IResourceStream, IFixedLocationResourceStream {
    /**
     * The location of this markup.
     */
    private val location = StringBuilder(250).run {
        append("kwmdsl:")
        append(supplierClass.qualifiedName)
        appendVariantSuffix(style, variation, locale)
        append(".html")

        toString()
    }

    /**
     * The text of this markup in UTF-8 bytes.
     */
    private val utf8Bytes = markupText.toByteArray()

    /**
     * The length of the UTF-8 bytes of the markup text.
     */
    private val lengthInBytes = Bytes.bytes(utf8Bytes.size.toLong())

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

    override fun setStyle(style: String?) {
        throw UnsupportedOperationException()
    }

    override fun getVariation() = variation

    override fun setVariation(variation: String?) {
        throw UnsupportedOperationException()
    }

    override fun length(): Bytes = lengthInBytes

    /**
     * Return an [Instant] so Wicket can detect newer markup when a new version is rolled out. The instant will be created only once in each JVM run. As the markup cannot change while the JVM is running, the same instant can be returned for all resource streams for the whole lifetime of the JVM.
     *
     * @return the same `Instant` for all resource streams.
     */
    override fun lastModifiedTime(): Instant = LAST_MODIFIED_TIME
}

/**
 * The last-modified time for all resource stream during this run of the JVM.
 */
private val LAST_MODIFIED_TIME = Instant.now()
