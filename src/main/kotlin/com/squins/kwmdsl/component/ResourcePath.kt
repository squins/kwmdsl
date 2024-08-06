package com.squins.kwmdsl.component

import com.squins.kwmdsl.relativizePath
import kotlin.reflect.KClass

fun KClass<*>.resourcePath(scope: KClass<*>, resourceName: String) =
    relativizePath(qualifiedName!!.split('.'), scope.qualifiedName!!.split('.').toMutableList().apply {
        set(size - 1, resourceName)
    }.toList(), '/')

inline fun <reified TScope : Any> KClass<*>.resourcePath(resourceName: String) =
    resourcePath(TScope::class, resourceName)
