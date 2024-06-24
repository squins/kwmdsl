package com.squins.kwmdsl

internal fun areCompatible(first: Markup<*>, second: Markup<*>) =
    first.children.size == second.children.size && areChildrenCompatible(first.children, second.children)

private fun areChildrenCompatible(firstChildren: List<ChildMarkup<*>>, secondChildren: List<ChildMarkup<*>>): Boolean {
    val firstChildrenByWicketId = associateByWicketId(firstChildren)
    val secondChildrenByWicketId = associateByWicketId(secondChildren)
    return firstChildrenByWicketId.keys == secondChildrenByWicketId.keys &&
            firstChildrenByWicketId.all { (childOfFirstWicketId, childOfFirst) ->
                val childOfSecond = secondChildrenByWicketId.getValue(childOfFirstWicketId)
                childOfFirst.supplier == childOfSecond.supplier && areCompatible(childOfFirst, childOfSecond)
            }
}

private fun associateByWicketId(children: List<ChildMarkup<*>>) =
    children.associateBy { markup -> markup.expectedWicketId }
