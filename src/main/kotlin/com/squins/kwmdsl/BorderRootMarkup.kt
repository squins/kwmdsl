package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border
import java.util.Locale
import kotlin.reflect.KClass

/**
 * A builder function for the markup of a [`wicket:border`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_surrounding_existing_markup_with_border) container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the border root markup.
 */
inline fun <reified TSupplier : Border> borderMarkup(noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, null, null, null, block)

inline fun <reified TSupplier : Border> borderMarkup(locale: Locale, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, null, null, locale, block)

inline fun <reified TSupplier : Border> borderMarkupVariation(variation: String, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, null, variation, null, block)

inline fun <reified TSupplier : Border> borderMarkupVariation(variation: String, locale: Locale, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, null, variation, locale, block)

inline fun <reified TSupplier : Border> borderMarkupStyle(style: String, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, style, null, null, block)

inline fun <reified TSupplier : Border> borderMarkupStyle(style: String, locale: Locale, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, style, null, locale, block)

inline fun <reified TSupplier : Border> borderMarkupStyleAndVariation(style: String, variation: String, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, style, variation, null, block)

inline fun <reified TSupplier : Border> borderMarkupStyleAndVariation(style: String, variation: String, locale: Locale, noinline block: BorderRootMarkupBuilder<TSupplier>.() -> Unit) =
    _borderMarkup(TSupplier::class, style, variation, locale, block)

@Suppress("FunctionName")
fun <TSupplier : Border> _borderMarkup(
    supplierClass: KClass<TSupplier>,
    style: String?,
    variation: String?,
    locale: Locale?,
    block: BorderRootMarkupBuilder<TSupplier>.() -> Unit
): BorderRootMarkup<TSupplier> {
    requireSupplierClassSpecified(supplierClass, Border::class)
    return BorderRootMarkupBuilder<TSupplier>().run {
        block()
        build(supplierClass, style, variation, locale)
    }
}

/**
 * Root markup for a [Border].
 *
 * @param TSupplier the border type having the properties and functions to get the Wicket components.
 * @param markupText the markup text of the border.
 * @param children the tree of child markups associated with a Wicket component.
 */
class BorderRootMarkup<TSupplier : Border> internal constructor(
    supplierClass: KClass<TSupplier>,
    style: String?,
    variation: String?,
    locale: Locale?,
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : BaseRootMarkup<TSupplier>(supplierClass, style, variation, locale, markupText, children) {
    /**
     * Adds the child Wicket components to the [Border] instance. The direct children will be added to the border using [Border.addToBorder], and descendents will be added to their parent using [MarkupContainer.add][org.apache.wicket.MarkupContainer.add].
     *
     * @param rootBorder the border to add the children to.
     */
    fun addToBorder(rootBorder: TSupplier) {
        children.forEach { childMarkup ->
            val component = childMarkup.retrieveComponent(rootBorder)
            rootBorder.addToBorder(component)
            addChildMarkupToComponentIfContainer(childMarkup, rootBorder, component)
        }
    }
}
