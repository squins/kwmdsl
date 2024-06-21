package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border

fun <TSupplierFacade : Border> borderMarkup(block: BorderRootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    BorderRootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

class BorderRootMarkup<TSupplierFacade : Border> internal constructor(
    markupText: String,
    children: List<ChildMarkup<TSupplierFacade>>,
) : BaseRootMarkup<TSupplierFacade>(markupText, children) {
    fun addToBorder(rootComponent: TSupplierFacade) {
        addToBorder(rootComponent, rootComponent)
    }

    private fun addToBorder(supplierFacade: TSupplierFacade, container: Border) {
        children.forEach { childMarkup ->
            val component = childMarkup.retrieveComponent(supplierFacade)
            container.addToBorder(component)
            addChildMarkupToComponentIfContainer(childMarkup, supplierFacade, component)
        }
    }
}
