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

    /**
     * Return an [Instant] so Wicket can detect newer markup when a new version is rolled out. The instant will created
     * only once in each JVM run. As the markup cannot change while the JVM is running, the same instant can be returned
     * for all resource streams for the whole lifetime of the JVM.
     *
     * @return the same `Instant` for all resource streams.
     */
    override fun lastModifiedTime(): Instant = LAST_MODIFIED_TIME
}

private val LAST_MODIFIED_TIME = Instant.now()
