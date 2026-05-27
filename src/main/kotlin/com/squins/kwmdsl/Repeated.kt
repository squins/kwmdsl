package com.squins.kwmdsl

import org.apache.wicket.Component
import kotlin.reflect.KFunction

/**
 * A reference to a component that is repeated, that is, it is a descendent of a [repeater](https://nightlies.apache.org/wicket/guide/9.x/single.html#_displaying_multiple_items_with_repeaters).
 *
 * @param supplier the function that will create a new instance of the component. The DSL will not call this for you. You have to call this function from `populateItem(...)`. The signature of the function does not matter. Only its name is used for the Wicket ID by the DSL.
 */
class Repeated(supplier: KFunction<Component>) {
    /**
     * The Wicket ID of the component that will be attached to the element to which this instance is passed.
     */
    val wicketId: String = supplier.name
}
