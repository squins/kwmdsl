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

/**
 * A builder function for the markup for a specific locale of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param locale the locale for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markup(locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, null, locale, block)

/**
 * A builder function for the markup for a specific variation of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param variation the variation for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupVariation(variation: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, variation, null, block)

/**
 * A builder function for the markup for a specific variation and locale of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupVariation(variation: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, null, variation, locale, block)

/**
 * A builder function for the markup for a specific style of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param style the style for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupStyle(style: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, null, null, block)

/**
 * A builder function for the markup for a specific style and locale of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param style the style for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupStyle(style: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, null, locale, block)

/**
 * A builder function for the markup for a specific style and variation of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupStyleAndVariation(style: String, variation: String, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, variation, null, block)

/**
 * A builder function for the markup for a specific style, variation and locale of a markup container that allows specifying the markup and the component hierarchy using the DSL.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
inline fun <reified TSupplier : MarkupContainer> markupStyleAndVariation(style: String, variation: String, locale: Locale, noinline block: RootMarkupBuilder<TSupplier>.() -> Unit) =
    _markup(TSupplier::class, style, variation, locale, block)

/**
 * The actual builder function for the markup for a specific style, variation and locale of a markup container that allows specifying the markup and the component hierarchy using the DSL. Not intended to be used directly. Use one of the variation without an underscore as those allow for terser code.
 *
 * For some markup container types, a specialized builder function must be used:
 *
 * * [Border][org.apache.wicket.markup.html.border.Border]s: [_borderMarkup]
 * * [Fragment][org.apache.wicket.markup.html.panel.Fragment]s that have their own markup: [standaloneFragmentMarkup]
 * * The body of `Fragment`s: [fragmentBodyMarkup]
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param supplierClass the class of the component with the member properties and functions supplying the components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
 * @param block the code specifying the markup and the component hierarchy.
 * @return the root markup.
 */
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
 * @param supplierClass the class of the component with the member properties and functions supplying the components.
 * @param style the style for which the markup must be used.
 * @param variation the variation for which the markup must be used.
 * @param locale the locale for which the markup must be used.
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
    /**
     * Add the component hierarchy defined in this markup to the given component (which also defines the supplier properties and functions).
     *
     * @param rootComponent the component to add the component hierarchy to. The supplier properties and functions of this component will be used to actually create the components in the hierarchy.
     */
    fun addTo(rootComponent: TSupplier) {
        addTo(rootComponent, rootComponent)
    }
}
