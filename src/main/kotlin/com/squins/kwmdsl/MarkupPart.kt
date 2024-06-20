package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

internal sealed interface MarkupPart<TSupplierFacade : MarkupContainer>

internal class TextPart<TSupplierFacade : MarkupContainer> : MarkupPart<TSupplierFacade>, Appendable {
    private val builder = StringBuilder()

    override fun append(characterSequence: CharSequence?): TextPart<TSupplierFacade> {
        builder.append(characterSequence)
        return this
    }

    override fun append(characterSequence: CharSequence?, start: Int, end: Int): TextPart<TSupplierFacade> {
        builder.append(characterSequence, start, end)
        return this
    }

    override fun append(character: Char): TextPart<TSupplierFacade> {
        builder.append(character)
        return this
    }

    fun append(value: Boolean): TextPart<TSupplierFacade> {
        builder.append(value.toString())
        return this
    }

    override fun toString() = builder.toString()
}

internal class ChildPart<TSupplierFacade : MarkupContainer>(val child: ChildMarkupBuilder<*>) :
    MarkupPart<TSupplierFacade>

internal class ReferencePart<TSupplierFacade : MarkupContainer>(
    val referencingComponentPath: List<String>,
    val formComponentSupplier: (TSupplierFacade) -> Component,
) : MarkupPart<TSupplierFacade>

internal class DescendentReferencePart<TSupplierFacade : MarkupContainer>(
    val supplier: (TSupplierFacade) -> Component,
) : MarkupPart<TSupplierFacade>
