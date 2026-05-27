package com.squins.kwmdsl.component

import kotlin.reflect.KClass

/**
 * Get the link path of a page. Only works for receivers of which the type has a qualified name.
 *
 * @receiver the class of the page to link to.
 * @return the link path of the page.
 */
fun KClass<*>.linkPath() =
    "/${qualifiedName!!.replace('.', '/')}.html"
