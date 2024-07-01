package com.squins.kwmdsl


/**
 * Create a [`wicket:enclosure`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:enclosure) attribute without a child path.
 *
 * @return an 'attribute': a pair of the attribute name and the attribute value.
 */
fun wicketEnclosureAttribute() = attr("wicket:enclosure", "")

/**
 * Create a [`wicket:enclosure`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:enclosure) attribute with child path [path].
 *
 * @path the path to the child Wicket component that determines the visibility of the element with this attribute. The client is responsible for making sure the path references an actual Wicket component.
 * @return an 'attribute': a pair of the attribute name and the attribute value.
 */
fun wicketEnclosureAttribute(path: String) =
    "wicket:enclosure" to Text(path)

/**
 * Create a [`wicket:for`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:for) attribute with child path [path].
 *
 * @path the path to the Wicket form component that the label containing this attribute is for. The client is responsible for making sure the path references an actual Wicket component.
 * @return an 'attribute': a pair of the attribute name and the attribute value.
 */
fun wicketForAttribute(path: String) =
    "wicket:for" to Text(path)

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
