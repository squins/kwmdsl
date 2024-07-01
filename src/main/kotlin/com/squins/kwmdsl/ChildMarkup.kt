package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

/**
 * Child markup representing an element that is associated with a Wicket component.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param expectedWicketId the ID the component returned by [supplier] is expected to have.
 * @param supplier the supplier that will return the Wicket component.
 * @param children the tree of child markups associated with a Wicket component.
 */
internal class ChildMarkup<TSupplierFacade : MarkupContainer>(
    val expectedWicketId: String,
    val supplier: (TSupplierFacade) -> Component,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children) {
    /**
     * Retrieve the Wicket component that is associated with this markup, using [supplier]. The ID of the retrieved component will be compared to [expectedWicketId] (which is equal to the name of the supplier). If the IDs do not match, an [IllegalStateException] is thrown.
     *
     * @param supplierFacade the supplier facade having the supplier function or property used to retrieve the component.
     */
    internal fun retrieveComponent(supplierFacade: TSupplierFacade) =
        supplier(supplierFacade).apply {
            check(id == expectedWicketId) { "Expected component with ID: $expectedWicketId, but got: $id" }
        }
}
