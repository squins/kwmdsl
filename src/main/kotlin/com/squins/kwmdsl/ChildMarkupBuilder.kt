package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1

/**
 * Markup that is not the root markup **and** that is associated with a Wicket component that is to be retrieved during the addition of the root markup to the markup container.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param expectedWicketId the Wicket ID that the component returned by the supplier is expected to have. During retrieval of the component, it is checked that the component ID matches this ID. This is done to find errors quickly.
 * @param supplier the function or property that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
 */
internal class ChildMarkupBuilder<TSupplierFacade : MarkupContainer> private constructor(
    private val expectedWicketId: String,
    private val supplier: (TSupplierFacade) -> Component,
) : MarkupBuilder<TSupplierFacade>() {
    /**
     * Create an instance with a function supplier.
     *
     * @param supplier the function that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
     */
    internal constructor(supplier: KFunction1<TSupplierFacade, Component>) : this(supplier.name, supplier)

    /**
     * Create an instance with a property supplier.
     *
     * @param supplier the property that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
     */
    internal constructor(supplier: KProperty1<TSupplierFacade, Component>) : this(supplier.name, supplier)

    internal fun build() = ChildMarkup(expectedWicketId, supplier, buildChildren())

    internal fun pathOf(function: (TSupplierFacade) -> Component): String? =
        if (function == supplier) {
            expectedWicketId
        } else {
            children.firstNotNullOfOrNull { child -> child.pathOf(function) }
                ?.let { subPath -> "$expectedWicketId:$subPath" }
        }
}
