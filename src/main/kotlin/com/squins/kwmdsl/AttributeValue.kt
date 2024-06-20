package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

sealed interface AttributeValue

class Text(internal val text: String) : AttributeValue

class DescendentReference<TSupplierFacade : MarkupContainer>(
    internal val childSupplier: (TSupplierFacade) -> Component,
) : AttributeValue

class Reference<TSupplierFacade : MarkupContainer>(
    internal val formComponentSupplier: (TSupplierFacade) -> Component,
) : AttributeValue
