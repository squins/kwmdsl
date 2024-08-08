package com.squins.kwmdsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.panel.Fragment
import java.io.Serializable
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

class WicketComponentsInFragment<TMarkupProvider : MarkupContainer, TSupplier : Fragment>(
    @Transient
    private val markupSupplier: KCallable<RootMarkup<TSupplier>>,
    @Transient
    private val fragmentFactory: (String, String, TMarkupProvider) -> TSupplier
) : Serializable {
    private lateinit var fragment: Fragment

    operator fun getValue(thisRef: TMarkupProvider, property: KProperty<*>): Fragment {
        if (!::fragment.isInitialized) {
            fragment = fragmentFactory(property.name, markupSupplier.name, thisRef)
        }
        return fragment
    }

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
