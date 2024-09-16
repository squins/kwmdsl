package com.squins.kwmdsl.component

import com.squins.kwmdsl.BaseRootMarkup
import com.squins.kwmdsl.BorderRootMarkup
import com.squins.kwmdsl.IKotlinWicketMarkupResourceStreamProvider
import com.squins.kwmdsl.IRootMarkup
import com.squins.kwmdsl.RootMarkup
import org.apache.wicket.Application
import org.apache.wicket.MarkupContainer
import org.apache.wicket.core.util.resource.locator.ResourceNameIterator
import org.apache.wicket.markup.html.border.Border
import java.util.Locale

interface IKotlinWicketMarkupProvider {
    val noVariantMarkup: IKotlinWicketMarkupResourceStreamProvider

    fun getVariantMarkup(style: String?, variation: String?, locale: Locale): IRootMarkup? = null
}

abstract class IRootMarkupVariants<TRootMarkup : BaseRootMarkup<*>> internal constructor(
    provider: IKotlinWicketMarkupProvider
) {
    private val noVariantRootMarkup = provider.noVariantMarkup

    private val rootMarkupsByVariantIdentifier = mutableMapOf<String, IRootMarkup>()

    fun add(rootMarkup: TRootMarkup) {
        val variation = rootMarkup.stream.variation
        val style = rootMarkup.stream.style
        val locale = rootMarkup.stream.locale
        check(variation != null || style != null || locale != null) { "The markup must be for at least one of variation,  style or locale."}
        val identifier = StringBuilder(50).run {
            appendVariantSuffix(style, variation, locale)

            toString()
        }
        add(identifier, rootMarkup)
    }

    fun get(style: String?, variation: String?, locale: Locale) =
        ResourceNameIterator("", style, variation, locale, null, false).asSequence()
            .firstNotNullOfOrNull { variantIdentifier ->
                if (variantIdentifier.isEmpty()) null else rootMarkupsByVariantIdentifier[variantIdentifier]
            }

    private fun add(identifier: String, rootMarkup: TRootMarkup) {
        check(!rootMarkupsByVariantIdentifier.containsKey(identifier)) { "Variant: $identifier, has already been added." }
        if (Application.get()?.usesDevelopmentConfig() == true && noVariantRootMarkup is IRootMarkup) {
            check(noVariantRootMarkup.isCompatible(rootMarkup)) {
                """The children of variant: $identifier, do not match the children of the no-variant markup. No-variant component hierarchy:
${noVariantRootMarkup.getComponentHierarchyString()}
Variant component hierarchy:
${rootMarkup.getComponentHierarchyString()}"""
            }
        }
        rootMarkupsByVariantIdentifier[identifier] = rootMarkup
    }
}

class RootMarkupVariants<TSupplier : MarkupContainer>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<RootMarkup<TSupplier>>(provider)

class BorderRootMarkupVariants<TSupplier : Border>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<BorderRootMarkup<TSupplier>>(provider)
