package com.squins.kwmdsl.component

import org.apache.wicket.Application
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.ContainerInfo
import org.apache.wicket.markup.MarkupResourceStream
import org.apache.wicket.util.resource.AbstractStringResourceStream
import org.apache.wicket.util.resource.IResourceStream
import org.apache.wicket.util.time.Time
import kotlin.reflect.full.companionObjectInstance

/**
 * Adapted from [`DefaultMarkupResourceStreamProvider`](org.apache.wicket.markup.DefaultMarkupResourceStreamProvider).
 */
fun findMarkup(container: MarkupContainer, containerClass: Class<*>): IResourceStream? {
    val locator = Application.get().resourceSettings.resourceStreamLocator

    val style = container.style
    val variation = container.variation
    val locale = container.locale

    val markupType = container.markupType
    val ext = markupType?.extension

    return sequence {
        var currentContainerClass = containerClass
        while (currentContainerClass != MarkupContainer::class.java) {
            yield(currentContainerClass)
            currentContainerClass = currentContainerClass.superclass
        }
    }.firstNotNullOfOrNull { currentContainerClass ->
        ((currentContainerClass.kotlin.companionObjectInstance as? IKotlinWicketMarkupProvider)
            ?.let { markupProvider ->
                if (container.application.usesDevelopmentConfig() && markupProvider is ReloadableKotlinWicketMarkupProvider<*>) {
                    object : AbstractStringResourceStream() {
                        override fun lastModifiedTime() = Time.now()
                        override fun getString(): String = markupProvider.createDslMarkup().stream.asString()
                    }
                } else {
                    markupProvider.dslMarkup.stream
                }
            }
            ?: locator.locate(
                currentContainerClass,
                currentContainerClass.getName().replace('.', '/'),
                style,
                variation,
                locale,
                ext,
                false
            ))?.let { MarkupResourceStream(it, ContainerInfo(currentContainerClass, container), currentContainerClass) }
    }
}
