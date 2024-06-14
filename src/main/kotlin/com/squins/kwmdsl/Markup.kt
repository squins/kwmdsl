package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer

/**
 * @param children child elements that are associated with Wicket components that are to be retrieved during the addition of the root markup to the markup container.
 */
abstract class Markup<TSupplierFacade : MarkupContainer> internal constructor(
    private val children: List<ChildMarkup<TSupplierFacade>>,
) {
    /**
     * Adds the hierarchy of Wicket components, retrieved using the suppliers, to the given container. The suppliers will be invoked on the given supplier facade.
     *
     * If a child added to `container` is itself a container, then the children of the child are also added to the child, recursively.
     *
     * @param supplierFacade the supplier facade having the supplier functions or properties used to retrieve the components.
     * @param container the container to which the add the child components.
     */
    internal fun addTo(supplierFacade: TSupplierFacade, container: MarkupContainer) {
        children.forEach { childMarkup ->
            val component = childMarkup.retrieveComponent(supplierFacade)
            container.add(component)
            if (component is MarkupContainer) {
                childMarkup.addTo(supplierFacade, component)
            }
        }
    }
}
