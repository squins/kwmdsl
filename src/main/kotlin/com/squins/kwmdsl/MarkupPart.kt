package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

internal sealed interface MarkupPart<TSupplier : MarkupContainer>

internal class TextPart<TSupplier : MarkupContainer> : MarkupPart<TSupplier>, Appendable {
    private val builder = StringBuilder()

    override fun append(characterSequence: CharSequence?): TextPart<TSupplier> {
        builder.append(characterSequence)
        return this
    }

    override fun append(characterSequence: CharSequence?, start: Int, end: Int): TextPart<TSupplier> {
        builder.append(characterSequence, start, end)
        return this
    }

    override fun append(character: Char): TextPart<TSupplier> {
        builder.append(character)
        return this
    }

    fun append(value: Boolean): TextPart<TSupplier> {
        builder.append(value.toString())
        return this
    }

    override fun toString() = builder.toString()
}

internal class ChildPart<TSupplier : MarkupContainer>(val child: ChildMarkupBuilder<*>) :
    MarkupPart<TSupplier>

internal class ForComponentReferencePart<TSupplier : MarkupContainer>(
    val referencingComponentPath: List<String>,
    val forComponentSupplier: (TSupplier) -> Component,
) : MarkupPart<TSupplier>

internal class DescendentReferencePart<TSupplier : MarkupContainer>(
    val supplier: (TSupplier) -> Component,
) : MarkupPart<TSupplier>
