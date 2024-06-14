package com.squins.kwmdsl

import org.apache.wicket.Component
import java.io.Serializable
import kotlin.reflect.KProperty

class Wicket<TComponent: Component>(private val factory: (String) -> TComponent) : Serializable {
    private lateinit var component: TComponent

    operator fun getValue(owner: Any?, property: KProperty<*>): TComponent  {
        if (!::component.isInitialized) {
            component = factory(property.name)
        }
        return component
    }

    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
