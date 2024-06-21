package com.squins.kwmdsl

import org.apache.wicket.Component
import kotlin.reflect.KCallable

class Repeated(supplier: KCallable<Component>) {
    val wicketId: String = supplier.name
}
