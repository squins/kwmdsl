package com.squins.kwmdsl

import org.apache.wicket.markup.html.panel.Fragment
import java.io.Serializable
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

class WicketOwnMarkupFragment(
    @Transient
    private val wicketIdSupplier: KCallable<Unit>,
    @Transient
    private val fragmentFactory: (String, String) -> Fragment
) : Serializable {
    private lateinit var fragment: Fragment

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Fragment {
        if (!::fragment.isInitialized) {
            fragment = fragmentFactory(property.name, wicketIdSupplier.name)
        }
        return fragment
    }

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
