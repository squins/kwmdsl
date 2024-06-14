package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

internal class ChildMarkup<TSupplierFacade : MarkupContainer>(
    private val expectedWicketId: String,
    private val supplier: (TSupplierFacade) -> Component,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children) {
    /**
     * Retrieve the Wicket component that is associated with this markup, using the supplier. The ID of the retrieved component will be compared to the expected ID (which is the name of the supplier). If the IDs do not match, an `IllegalStateException` is thrown.
     *
     * @param supplierFacade the supplier facade having the supplier function or property used to retrieve the component.
     */
    internal fun retrieveComponent(supplierFacade: TSupplierFacade) =
        supplier(supplierFacade).apply {
            check(id == expectedWicketId) { "Expected component with ID: ${expectedWicketId}, but got: $id" }
        }
}
