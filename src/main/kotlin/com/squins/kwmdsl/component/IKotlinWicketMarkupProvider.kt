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

/**
 * Interface to be implemented by the companion object of components that want to use the DSL for writing markup.
 */
interface IKotlinWicketMarkupProvider {
    /**
     * The default markup (no style, variation and locale), used when markup for a specific variant cannot be found.
     */
    val noVariantMarkup: IKotlinWicketMarkupResourceStreamProvider

    /**
     * Get the markup for a variant. This returns `null` by default. To use variants, create an instance of [RootMarkupVariants] or [BorderRootMarkupVariants] in the companion object, override this function and call [IRootMarkupVariants.get].
     *
     * @param style the style for which to return the markup.
     * @param variation the variation for which to return the markup.
     * @param locale the locale for which to return the markup.
     * @return the markup or `null` if no markup for the variant can be found.
     */
    fun getVariantMarkup(style: String?, variation: String?, locale: Locale): IRootMarkup? = null
}

/**
 * The markup variants of a component.
 *
 * @param TRootMarkup the type of the root markup.
 * @param provider the markup provider these variants are for.
 */
abstract class IRootMarkupVariants<TRootMarkup : BaseRootMarkup<*>> internal constructor(
    provider: IKotlinWicketMarkupProvider
) {
    /**
     * The no-variant markup. Only used in the development configuration to check if the component hierarchy matches the variants added to this class.
     */
    private val noVariantRootMarkup = provider.noVariantMarkup

    /**
     * The markup variants by their identifier: the suffix for the combintatiion of style, variation and locale.
     */
    private val rootMarkupsByVariantIdentifier = mutableMapOf<String, IRootMarkup>()

    /**
     * Add a markup variant. The style, variation and locale are retrieved from the resource stream of the markup. At least one of them must be present.
     *
     * A variant can only be added once. Adding it again will result in an exception.
     *
     * In the development configuration, a check is performed to ensure that the component hierarchy of the markup variant matches the component hierarchy of the no-variant markup.
     *
     * @param rootMarkup the markup variant.
     */
    fun add(rootMarkup: TRootMarkup) {
        val style = rootMarkup.stream.style
        val variation = rootMarkup.stream.variation
        val locale = rootMarkup.stream.locale
        check(style != null || variation != null || locale != null) { "The markup must be for at least one of style, variation or locale."}
        val identifier = StringBuilder(50).run {
            appendVariantSuffix(style, variation, locale)

            toString()
        }
        add(identifier, rootMarkup)
    }

    /**
     * Get the markup for the given variant: the combination of style, variation and locale.
     *
     * @param style the style for which to return the markup.
     * @param variation the variation for which to return the markup.
     * @param locale the locale for which to return the markup.
     * @return the markup or `null` if no markup for the variant can be found.
     */
    fun get(style: String?, variation: String?, locale: Locale) =
        ResourceNameIterator("", style, variation, locale, null, false).asSequence()
            .firstNotNullOfOrNull { variantIdentifier ->
                if (variantIdentifier.isEmpty()) null else rootMarkupsByVariantIdentifier[variantIdentifier]
            }

    /**
     * Add a markup variant with the given identifier (based on the style, variation and locale).
     *
     * A variant can only be added once. Adding it again will result in an exception.
     *
     * In the development configuration, a check is performed to ensure that the component hierarchy of the markup variant matches the component hierarchy of the no-variant markup.
     *
     * @param identifier the variant identifier.
     * @param rootMarkup the markup variant.
     */
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

/**
 * Markup variants for most components.
 *
 * For some markup container types, a specialized markup variants class must be used:
 *
 * * [Border]s: [BorderRootMarkupVariants]
 *
 * @param provider the markup provider these variants are for.
 */
class RootMarkupVariants<TSupplier : MarkupContainer>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<RootMarkup<TSupplier>>(provider)

/**
 * Markup variants for [Border]s.
 *
 * @param provider the markup provider these variants are for.
 */
class BorderRootMarkupVariants<TSupplier : Border>(provider: IKotlinWicketMarkupProvider) :
    IRootMarkupVariants<BorderRootMarkup<TSupplier>>(provider)
