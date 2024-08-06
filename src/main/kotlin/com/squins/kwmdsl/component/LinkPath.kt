package com.squins.kwmdsl.component

import kotlin.reflect.KClass

fun KClass<*>.linkPath() =
    "/${qualifiedName!!.replace('.', '/')}.html"
