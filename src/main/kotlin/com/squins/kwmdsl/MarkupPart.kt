package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import kotlin.reflect.KProperty1

/**
 * The markup being built consists of different parts as the processing of parts may differ.
 */
internal sealed interface MarkupPart<TSupplier : MarkupContainer>

/**
 * A part that is purely text, that is, there are no Wicket constructs in this part.
 */
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

    /**
     * Append the given value to the part.
     *
     * @param value the value. The value will be output as either `false` or `true`.
     * @return this text part.
     */
    fun append(value: Boolean): TextPart<TSupplier> {
        builder.append(value.toString())
        return this
    }

    override fun toString() = builder.toString()
}

/**
 * A part that is markup for a child component. Child components have their own markup builder so the component hierarchy can be constructed during markup specification.
 *
 * @param child the markup builder for the child component.
 */
internal class ChildPart<TSupplier : MarkupContainer>(val child: ChildMarkupBuilder<*>) :
    MarkupPart<TSupplier>

/**
 * A part that has a reference to a component in a `for` or `wicket:for` attribute.
 *
 * @param referencingComponentPath the path of the component that is referencing the component supplied by
 * [forComponentSupplier].
 * @param forComponentSupplier the supplier of the component being referred to.
 */
internal class ForComponentReferencePart<TSupplier : MarkupContainer>(
    val referencingComponentPath: List<String>,
    val forComponentSupplier: KProperty1<TSupplier, MarkupContainer>,
) : MarkupPart<TSupplier>

/**
 * A part that has a reference to a descendent component, that is, a [`wicket:enclosure`](https://nightlies.apache.org/wicket/guide/9.x/single.html#_hiding_decorating_elements_with_the_wicketenclosure_tag) element or attribute.
 *
 * @param supplier the supplier of the component being referred to.
 */
internal class DescendentReferencePart<TSupplier : MarkupContainer>(
    val supplier: KProperty1<TSupplier, Component>,
) : MarkupPart<TSupplier>
