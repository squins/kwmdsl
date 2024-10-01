package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import java.util.Locale
import kotlin.reflect.KClass

/**
 * A builder function for the markup of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markup(noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, null, null, block)

inline fun <reified TSupplier : MarkupContainer> markup(locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, null, locale, block)

inline fun <reified TSupplier : MarkupContainer> markupVariation(variation: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, variation, null, block)

inline fun <reified TSupplier : MarkupContainer> markupVariation(variation: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, variation, locale, block)

inline fun <reified TSupplier : MarkupContainer> markupStyle(style: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, null, null, block)

inline fun <reified TSupplier : MarkupContainer> markupStyle(style: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, null, locale, block)

inline fun <reified TSupplier : MarkupContainer> markupStyleAndVariation(style: String, variation: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, variation, null, block)

inline fun <reified TSupplier : MarkupContainer> markupStyleAndVariation(style: String, variation: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, variation, locale, block)

@Suppress("FunctionName")
fun <TSupplier : MarkupContainer> _markup(
    supplierClass: KClass<TSupplier>,
    style: String?,
    variation: String?,
    locale: Locale?,
    block: RootMarkupBuilder<TSupplier>.() -> Unit
): RootMarkup<TSupplier> {
    requireSupplierClassSpecified(supplierClass, MarkupContainer::class)
    return RootMarkupBuilder<TSupplier>().run {
        block()
        build(supplierClass, style, variation, locale)
    }
}

/**
 * Root markup for a [MarkupContainer].
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param markupText the markup text of the container.
 * @param children the tree of child markups associated with a Wicket component.
 */
class RootMarkup<TSupplier : MarkupContainer> internal constructor(
    supplierClass: KClass<TSupplier>,
    style: String?,
    variation: String?,
    locale: Locale?,
    markupText: String,
    children: List<ChildMarkup<TSupplier>>,
) : BaseRootMarkup<TSupplier>(supplierClass, style, variation, locale, markupText, children) {
    fun addTo(rootComponent: TSupplier) {
        addTo(rootComponent, rootComponent)
    }
}
