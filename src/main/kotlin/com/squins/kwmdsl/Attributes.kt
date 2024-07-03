package com.squins.kwmdsl

/**
 * Create a [`wicket:message`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_reading_bundles_with_tag_wicket_message) attribute for one or more attributes.
 *
 * A convenience function for [wicketMessageAttribute] to keep internationalization  code short and readable.
 *
 * @param attributeDefinitions at least one pair of attribute name and message key.
 * @return an 'attribute': a pair of the attribute name and the attribute value.
 */
fun ma(vararg attributeDefinitions: Pair<String, String>) = wicketMessageAttribute(*attributeDefinitions)

/**
 * Create a [`wicket:message`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_reading_bundles_with_tag_wicket_message) attribute for one or more attributes.
 *
 * @param attributeDefinitions at least one pair of attribute name and message key.
 * @return an 'attribute': a pair of the attribute name and the attribute value.
 */
fun wicketMessageAttribute(vararg attributeDefinitions: Pair<String, String>): Pair<String, AttributeValue> {
    require(attributeDefinitions.isNotEmpty()) { "At least one attribute definition must be passed" }
    return attr("wicket:message", attributeDefinitions.joinToString(",") { (attributeName, messageKey) ->
        "$attributeName:$messageKey"
    })
}
