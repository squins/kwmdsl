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
    private val parent: MarkupBuilder<TSupplierFacade>,
    private val expectedWicketId: String,
    private val supplier: (TSupplierFacade) -> Component,
) : MarkupBuilder<TSupplierFacade>() {
    /**
     * Create an instance with a function supplier.
     *
     * @param supplier the function that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
     */
    internal constructor(parent: MarkupBuilder<TSupplierFacade>, supplier: KFunction1<TSupplierFacade, Component>) :
            this(parent, supplier.name, supplier)

    /**
     * Create an instance with a property supplier.
     *
     * @param supplier the property that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
     */
    internal constructor(parent: MarkupBuilder<TSupplierFacade>, supplier: KProperty1<TSupplierFacade, Component>) :
            this(parent, supplier.name, supplier)

    override fun getPathAsList() = (parent.getPathAsList() + expectedWicketId)

    internal fun build() = ChildMarkup(expectedWicketId, supplier, buildChildren())

    internal fun pathOf(supplier: (TSupplierFacade) -> Component): String? =
        if (supplier == this@ChildMarkupBuilder.supplier) {
            expectedWicketId
        } else {
            children.firstNotNullOfOrNull { child -> child.pathOf(supplier) }
                ?.let { subPath -> "$expectedWicketId:$subPath" }
        }

    internal fun pathOfAsList(supplier: (TSupplierFacade) -> Component): List<String>? =
        if (supplier == this@ChildMarkupBuilder.supplier) {
            listOf(expectedWicketId)
        } else {
            children.firstNotNullOfOrNull { child -> child.pathOfAsList(supplier) }
                ?.let { subPath -> subPath.toMutableList().apply { add(0, expectedWicketId) } }
        }

    override fun pathFromRootOfAsList(supplier: (TSupplierFacade) -> Component) = parent.pathFromRootOfAsList(supplier)
}
