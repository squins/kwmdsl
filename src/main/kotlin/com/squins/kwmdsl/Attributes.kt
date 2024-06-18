package com.squins.kwmdsl

fun ma(vararg attributeDefinitions: Pair<String, String>) = wicketMessageAttribute(*attributeDefinitions)

fun wicketMessageAttribute(vararg attributeDefinitions: Pair<String, String>): Pair<String, String> {
    require(attributeDefinitions.isNotEmpty()) { "At least one attribute definition must be passed" }
    return "wicket:message" to attributeDefinitions.joinToString(",") { (key, value) -> "$key:$value" }
}
