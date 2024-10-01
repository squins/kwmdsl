package com.squins.kwmdsl

import kotlin.reflect.KClass

internal fun requireSupplierClassSpecified(supplierClass: KClass<*>, classIndicatingSupplierClassHasNotBeenSpecified: KClass<*>) {
    require(supplierClass != classIndicatingSupplierClassHasNotBeenSpecified) {
        "The supplier class is incorrect. This would result in duplicate cache keys, and a mix-up of markup. Either specify the owner of the markup explicitly using type parameters, or refer to at least one property or function of the markup owner in the markup itself."
    }
}
