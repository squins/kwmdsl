package com.squins.kwmdsl.component

import kotlin.reflect.full.companionObjectInstance

fun findDslMarkup(containerClass: Class<*>) =
    (containerClass.kotlin.companionObjectInstance as? IKotlinWicketMarkupProvider)?.dslMarkup?.stream
