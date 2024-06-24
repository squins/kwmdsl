package com.squins.kwmdsl.component

import com.squins.kwmdsl.BaseRootMarkup
import com.squins.kwmdsl.BorderRootMarkup
import com.squins.kwmdsl.IRootMarkup
import com.squins.kwmdsl.RootMarkup
import org.apache.wicket.Application
import org.apache.wicket.MarkupContainer
import org.apache.wicket.core.util.resource.locator.ResourceNameIterator
import org.apache.wicket.markup.html.border.Border
import java.util.Locale

interface IKotlinWicketMarkupProvider {
    val noVariantMarkup: IRootMarkup

    fun getVariantMarkup(style: String?, variation: String?, locale: Locale): IRootMarkup? = null
}

abstract class IRootMarkupVariants<TRootMarkup : BaseRootMarkup<*>> internal constructor(
    provider: IKotlinWicketMarkupProvider
) {
    private val noVariantRootMarkup = provider.noVariantMarkup

    private val rootMarkupsByVariantIdentifier = mutableMapOf<String, IRootMarkup>()

    fun addStyleAndVariation(style: String, variation: String, locale: Locale, rootMarkup: TRootMarkup) {
        add("_${variation}_${style}_$locale", rootMarkup)
    }

    fun addStyleAndVariation(style: String, variation: String, rootMarkup: TRootMarkup) {
        add("_${variation}_$style", rootMarkup)
    }

    fun addStyle(style: String, locale: Locale, rootMarkup: TRootMarkup) {
        add("_${style}_$locale", rootMarkup)
    }

    fun addStyle(style: String, rootMarkup: TRootMarkup) {
        add("_$style", rootMarkup)
    }

    fun addVariation(variation: String, locale: Locale, rootMarkup: TRootMarkup) {
        add("_${variation}_$locale", rootMarkup)
    }

    fun addVariation(variation: String, rootMarkup: TRootMarkup) {
        add("_$variation", rootMarkup)
    }

    fun add(locale: Locale, rootMarkup: TRootMarkup) {
        add("_$locale", rootMarkup)
    }

    fun get(style: String?, variation: String?, locale: Locale) =
        ResourceNameIterator("", style, variation, locale, null, false).asSequence()
            .firstNotNullOfOrNull { variantIdentifier ->
                if (variantIdentifier.isEmpty()) null else rootMarkupsByVariantIdentifier[variantIdentifier]
            }

    private fun add(identifier: String, rootMarkup: TRootMarkup) {
        check(!rootMarkupsByVariantIdentifier.containsKey(identifier)) { "Variant: $identifier, has already been added." }
        if (Application.get()?.usesDevelopmentConfig() == true) {
            check(noVariantRootMarkup.isCompatibleVariant(rootMarkup)) {
                """The children of variant: $identifier, do not match the children of the no-variant markup. No-variant component hierarchy:
${noVariantRootMarkup.getComponentHierarchyString()}
Variant component hierarchy:
${rootMarkup.getComponentHierarchyString()}"""
            }
        }
        rootMarkupsByVariantIdentifier[identifier] = rootMarkup
    }
}

class RootMarkupVariants<TSupplierFacade : MarkupContainer>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<RootMarkup<TSupplierFacade>>(provider)

class BorderRootMarkupVariants<TSupplierFacade : Border>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<BorderRootMarkup<TSupplierFacade>>(provider)
