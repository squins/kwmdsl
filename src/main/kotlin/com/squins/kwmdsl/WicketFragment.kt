package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment
import org.apache.wicket.model.IModel
import java.io.Serializable
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

class WicketFragment<TSupplier : MarkupContainer>(
    @Transient
    private val markupSupplier: KCallable<RootMarkup<TSupplier>>,
    @Transient
    private val model: IModel<*>? = null,
) : Serializable {
    private lateinit var fragment: Fragment

    operator fun getValue(thisRef: TSupplier, property: KProperty<*>): Fragment {
        if (!::fragment.isInitialized) {
            fragment = Fragment(property.name, markupSupplier.name, thisRef, model).apply {
                markupSupplier.call().addTo(thisRef, this)
            }
        }
        return fragment
    }

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
