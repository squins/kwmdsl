package com.squins.kwmdsl

fun ma(vararg attributeDefinitions: Pair<String, String>) = wicketMessageAttribute(*attributeDefinitions)

fun wicketMessageAttribute(vararg attributeDefinitions: Pair<String, String>): Pair<String, AttributeValue> {
    require(attributeDefinitions.isNotEmpty()) { "At least one attribute definition must be passed" }
    return attr("wicket:message", attributeDefinitions.joinToString(",") { (key, value) -> "$key:$value" })
}
