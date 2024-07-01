package com.squins.kwmdsl

import org.apache.wicket.util.resource.StringResourceStream

/**
 * The markup for a Wicket [MarkupContainer][org.apache.wicket.MarkupContainer]. Some markup container types require a specific markup structure or a specific way to add the Wicket components to them. There are specialized implementations for those cases.
 */
interface IRootMarkup {
    /**
     * The resource stream containing the markup text.
     */
    val stream: StringResourceStream

    /**
     * Determine whether the given markup is compatible with this markup. This is used to check whether a markup variant of a markup container is compatible with the no-variant markup of that same container.
     *
     * @param other the markup for which to check whether it is compatible with this markup.
     * @return `true` if [other] is compatible, `false` otherwise.
     */
    fun isCompatible(other: Markup<*>): Boolean

    /**
     * Get a canonical, human-readable representation of the Wicket component hierarchy. The hierarchy is output in such a way that comparing with a diff tool can be done quickly.
     *
     * The representations of the no-variant and the variant markup are output if they are not compatible, so the user can easily determine where the hierarchies do not match.
     *
     * @return a string representation of the Wicket component hierarchy.
     */
    fun getComponentHierarchyString(): String
}
