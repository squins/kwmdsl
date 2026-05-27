package com.squins.kwmdsl.component

import com.squins.kwmdsl.relativizePath
import kotlin.reflect.KClass

/**
 * Get the resource path from the receiver class to the resource with the given name [scope] as the scope.
 *
 * @receiver the class referencing the resource.
 * @param scope the scope type of the resource.
 * @param resourceName the name of the resource relative to `scope`.
 * @return a relative path from the receiver to a resource of `scope`.
 */
fun KClass<*>.resourcePath(scope: KClass<*>, resourceName: String) =
    relativizePath(qualifiedName!!.split('.'), scope.qualifiedName!!.split('.').toMutableList().apply {
        set(size - 1, resourceName)
    }.toList(), '/')

/**
 * Get the resource path from the receiver class to the resource with the given name and [TScope] as the scope.
 *
 * @receiver the class referencing the resource.
 * @param TScope the scope type of the resource.
 * @param resourceName the name of the resource relative to `TScope`.
 * @return a relative path from the receiver to a resource of `TScope`.
 */
inline fun <reified TScope : Any> KClass<*>.resourcePath(resourceName: String) =
    resourcePath(TScope::class, resourceName)
