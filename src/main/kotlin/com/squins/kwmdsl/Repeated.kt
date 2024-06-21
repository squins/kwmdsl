package com.squins.kwmdsl

import org.apache.wicket.Component
import kotlin.reflect.KFunction

class Repeated(supplier: KFunction<Component>) {
    val wicketId: String = supplier.name
}
