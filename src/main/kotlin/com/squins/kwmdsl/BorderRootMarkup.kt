package com.squins.kwmdsl

import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.util.resource.StringResourceStream

fun <TSupplierFacade : Border> borderMarkup(block: BorderRootMarkupBuilder<TSupplierFacade>.() -> Unit) =
    BorderRootMarkupBuilder<TSupplierFacade>().run {
        block()
        build()
    }

class BorderRootMarkup<TSupplierFacade : Border> internal constructor(
    markupTextBuilder: StringBuilder,
    children: List<ChildMarkup<TSupplierFacade>>,
) : Markup<TSupplierFacade>(children), IRootMarkup {
    override val stream = StringResourceStream(markupTextBuilder.toString())

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
