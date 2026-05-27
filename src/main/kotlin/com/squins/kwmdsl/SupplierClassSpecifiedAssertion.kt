package com.squins.kwmdsl

import kotlin.reflect.KClass

/**
 * Check that the actual supplier class has been specified. The supplier class can be specified implicitly using type inference, or explicitly as a generic parameter. When using type inference, and using supplier properties or functions of different classes, the wrong type is inferred (as the code is invalid).
 *
 * The supplier class of a markup must be unique as otherwise the same cache keys for the markup will be used, which will result in errors.
 *
 * @param supplierClass the class defining the supplier properties and functions.
 * @param classIndicatingSupplierClassHasNotBeenSpecified the base class that indicates that the wrong type has been inferred, a generic parameter is missing or the generic parameter has the wrong value.
 */
internal fun requireSupplierClassSpecified(supplierClass: KClass<*>, classIndicatingSupplierClassHasNotBeenSpecified: KClass<*>) {
    require(supplierClass != classIndicatingSupplierClassHasNotBeenSpecified) {
        "The supplier class is incorrect. This would result in duplicate cache keys, and a mix-up of markup. Either specify the owner of the markup explicitly using type parameters, or refer to at least one property or function of the markup owner in the markup itself."
    }
}
