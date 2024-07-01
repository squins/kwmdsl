package com.squins.kwmdsl

/**
 * Determine whether the hierarchy of the first markup is compatible with the second markup. 2 hierarchies are compatible if 2 roots or parents recursively contain the same set of suppliers for their children.
 *
 * @param first the first markup hierarchy.
 * @param second the second markup hierarchy.
 */
internal fun areCompatible(first: Markup<*>, second: Markup<*>) =
    first.children.size == second.children.size && areChildrenCompatible(first.children, second.children)

/**
 * Determine whether the 2 collections of children and their descendents are compatible. Collections are compatible if the sets of (expected) Wicket IDs are equal, the suppliers of the matching children are equal, and the hierarchy of each child matches the hierarchy of the corresponding child in the other markup.
 *
 * @param firstChildren the first collection of children.
 * @param secondChildren the second collection of children.
 */
private fun areChildrenCompatible(
    firstChildren: Collection<ChildMarkup<*>>,
    secondChildren: Collection<ChildMarkup<*>>
): Boolean {
    val firstChildrenByWicketId = associateByWicketId(firstChildren)
    val secondChildrenByWicketId = associateByWicketId(secondChildren)
    return firstChildrenByWicketId.keys == secondChildrenByWicketId.keys &&
            firstChildrenByWicketId.all { (childOfFirstWicketId, childOfFirst) ->
                val childOfSecond = secondChildrenByWicketId.getValue(childOfFirstWicketId)
                childOfFirst.supplier == childOfSecond.supplier && areCompatible(childOfFirst, childOfSecond)
            }
}

/**
 * Associate the collection of children by their (expected) Wicket ID.
 *
 * @param children the children to associate by their Wicket ID.
 * @return a map of child markups by Wicket ID.
 */
private fun associateByWicketId(children: Collection<ChildMarkup<*>>) =
    children.associateBy { markup -> markup.expectedWicketId }
