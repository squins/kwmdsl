package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.string.Strings
import java.nio.charset.Charset
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1
import kotlin.text.Charsets.UTF_8

/**
 * Markup that provides functions to add child markup to it. For all HTML elements, and elements that only have a `class` attribute, convenience extension functions are provided.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 */
@WicketMarkupBuilder
abstract class MarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor() {
    internal var currentTextPart = TextPart<TSupplierFacade>()

    private val parts = mutableListOf<MarkupPart<TSupplierFacade>>(currentTextPart)

    /**
     * Child elements that are associated with Wicket components that are to be retrieved during the addition of the root markup to the markup container.
     */
    internal val children = mutableListOf<ChildMarkupBuilder<TSupplierFacade>>()

    fun xmlDeclaration(version: String = "1.0", encoding: Charset = UTF_8) {
        currentTextPart
            .append("""<?xml version="""")
            .append(version)
            .append("""" encoding="""")
            .append(encoding.name())
            .append(""""?>""")
    }

    /**
     * Add a document type declaration to the markup.
     *
     * @param value the document type. **Warning**: there is no validation and no escaping, so make sure the document type is valid and safe.
     */
    fun docType(value: String) {
        currentTextPart
            .append("<!DOCTYPE ")
            .append(value)
            .append('>')
    }

    // TODO("Body: check if complete")
    /**
     * Add a `wicket:body` element to the markup.
     */
    fun wicketBody() {
        currentTextPart.append("<wicket:body></wicket:body>")
    }

    fun wicketBorder(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:border>")
        block()
        currentTextPart.append("</wicket:border>")
    }

    fun wicketChild(block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null) {
        currentTextPart.append("<wicket:child>")
        block?.invoke(this)
        currentTextPart.append("</wicket:child>")
    }

    /**
     * Add a `wicket:container` element to the markup. The client is responsible for adding the associated Wicket component to the correct parent.
     *
     * @param id the Wicket ID of the container. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        id: String,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        element("wicket:container", arrayOf("wicket:id" to id), block)
    }

    /**
     * Add a `wicket:container` element to the markup, and use the given supplier function to determine the Wicket ID to assign to the element, and to retrieve the associated Wicket component.
     *
     * @param supplier the function that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        supplier: KFunction1<TSupplierFacade, Component>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        element(supplier, "wicket:container", emptyArray(), block)
    }

    /**
     * Add a `wicket:container` element to the markup, and use the given supplier property to determine the Wicket ID to assign to the element, and to retrieve the associated Wicket component.
     *
     * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        supplier: KProperty1<TSupplierFacade, Component>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        element(supplier, "wicket:container", emptyArray(), block)
    }

    fun wicketEnclosure(childSupplier: ((TSupplierFacade) -> Component)? = null, block: (MarkupBuilder<TSupplierFacade>.() -> Unit)) {
        startTagPrefix("wicket:enclosure")
        if (childSupplier != null) {
            currentTextPart.append(""" child="""")
            parts += DescendentReferencePart(childSupplier)
            currentTextPart = TextPart()
            parts += currentTextPart
            currentTextPart.append('"')
        }
        currentTextPart.append('>')
        block()
        endTag("wicket:enclosure")
    }

    fun wicketExtend(block: (MarkupBuilder<TSupplierFacade>.() -> Unit)) {
        currentTextPart.append("<wicket:extend>")
        block()
        currentTextPart.append("</wicket:extend>")
    }

    fun wicketFragment(markupSupplier: KCallable<IRootMarkup>) {
        currentTextPart
            .append("""<wicket:fragment wicket:id="""")
            .append(markupSupplier.name)
            .append("""">""")
            .append(markupSupplier.call().stream.asString())
            .append("</wicket:fragment>")
    }

    // TODO("Document: for DSL fragments with their own markup only")
    // TODO("If a fragment supports multiple markups, the comment will be repeated. Use a separate FragmentRootMarkup (and builder)?")
    fun wicketFragment(markupSupplier: KCallable<IRootMarkup>, block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart
            .append("""<!-- Searches for the fragments start at 1, so make sure there is at least 1 element before the fragments. --><wicket:fragment wicket:id="""")
            .append(markupSupplier.name)
            .append("""">""")
        block()
        currentTextPart
            .append("</wicket:fragment>")
    }

    fun wicketHead(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:head>")
        block()
        currentTextPart.append("</wicket:head>")
    }

    fun wicketHeaderItems() {
        currentTextPart.append("<wicket:header-items/>")
    }

    /**
     * Add a `wicket:link` element to the markup.
     *
     * @param block the code for building the children of the element.
     */
    fun wicketLink(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:link>")
        block()
        currentTextPart.append("</wicket:link>")
    }

    /**
     * Add a `wicket:message` element to the markup.
     *
     * A convenience function to keep code short and readable.
     *
     * @param key the message key. **Warning**: there is no validation and no escaping, so make sure the key is valid and safe.
     * @param escape whether to escape the message. Optional, `true` by default.
     * @param block the (optional) code for building the children of the element.
     */
    fun m(
        key: String,
        escape: Boolean = true,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        wicketMessage(key, escape, block)
    }

    /**
     * Add a `wicket:message` element to the markup.
     *
     * @param key the message key. **Warning**: there is no validation and no escaping, so make sure the key is valid and safe.
     * @param escape whether to escape the message. Optional, `true` by default.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketMessage(
        key: String,
        escape: Boolean = true,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        currentTextPart
            .append("""<wicket:message key="""")
            .append(key)
            .append("""" escape="""")
            .append(escape)
            .append("""">""")
        if (block != null) {
            block()
        }
        currentTextPart.append("</wicket:message>")
    }

    fun wicketPanel(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:panel>")
        block()
        currentTextPart.append("</wicket:panel>")
    }

    /**
     * Add a `wicket:remove` element to the markup.
     *
     * @param block the code for building the children of the element.
     */
    // TODO("Can this go? The DSL is not useful for working with a designer. Commenting out can be done using Kotlin comments")
    fun wicketRemove(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        currentTextPart.append("<wicket:remove>")
        block()
        currentTextPart.append("</wicket:remove>")
    }

    // TODO("wicket:for attribute")
    // Allows reference to component anywhere in the hierarchy: https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:for

    /**
     * Add an element with the given name to the markup.
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    fun element(
        name: String,
        attributes: Array<out Pair<String, String>>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        startTagPrefix(name)
        attributes(attributes)
        currentTextPart.append('>')
        if (block != null) {
            block()
        }
        endTag(name)
    }

    /**
     * Add an element with the given name and associated with a Wicket component to the markup.
     *
     * @param supplier the function that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    fun element(
        supplier: KFunction1<TSupplierFacade, Component>,
        name: String,
        attributes: Array<out Pair<String, String>>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        val childMarkup = ChildMarkupBuilder(supplier)
        wicketElement(childMarkup, name, supplier.name, attributes, block)
    }

    /**
     * Add an element with the given name and associated with a Wicket component to the markup.
     *
     * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    fun element(
        supplier: KProperty1<TSupplierFacade, Component>,
        name: String,
        attributes: Array<out Pair<String, String>>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null
    ) {
        val childMarkup = ChildMarkupBuilder(supplier)
        wicketElement(childMarkup, name, supplier.name, attributes, block)
    }

    /**
     * Add a void element with the given name to the markup.
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    fun voidElement(name: String, attributes: Array<out Pair<String, String>>) {
        startTagPrefix(name)
        attributes(attributes)
        currentTextPart.append('>')
    }

    /**
     * Add a void element with the given name and associated with a Wicket component to the markup.
     *
     * @param supplier the function that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    fun voidElement(
        supplier: KFunction1<TSupplierFacade, Component>,
        name: String,
        attributes: Array<out Pair<String, String>>,
    ) {
        val childMarkup = ChildMarkupBuilder(supplier)
        voidWicketElement(childMarkup, name, supplier.name, attributes)
    }

    /**
     * Add a void element with the given name and associated with a Wicket component to the markup.
     *
     * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    fun voidElement(
        supplier: KProperty1<TSupplierFacade, Component>,
        name: String,
        attributes: Array<out Pair<String, String>>,
    ) {
        val childMarkup = ChildMarkupBuilder(supplier)
        voidWicketElement(childMarkup, name, supplier.name, attributes)
    }

    /**
     * Add text to the markup. Contrary to the other functions, the text will be escaped before it is added.
     *
     * @param text the text to add.
     */
    fun text(text: String) {
        currentTextPart.append(Strings.escapeMarkup(text))
    }

    // TODO("Unescaped text?")

    internal fun appendParts(builder: StringBuilder) {
        parts.forEach { part ->
            when (part) {
                is ChildPart -> part.child.appendParts(builder)
                // All of the children are searched, instead of only the children of the tag containing the reference,
                // as it is more difficult to store which range of children must be searched when creating the
                // reference. Although this does affect performance a bit, it is acceptable:
                //
                // * Not many pages have more than a few dozen components.
                // * The search only runs once for all component instances.
                is DescendentReferencePart -> builder.append(children.firstNotNullOf { child -> child.pathOf(part.supplier) })
                is TextPart -> builder.append(part)
            }
        }
    }
    internal fun buildChildren(): List<ChildMarkup<TSupplierFacade>> = children.map { it.build() }

    /**
     * Add an element with the given name and associated with a Wicket component to the markup.
     *
     * @param childMarkupBuilder the child markup representing the element.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    private fun wicketElement(
        childMarkupBuilder: ChildMarkupBuilder<TSupplierFacade>,
        name: String,
        wicketId: String,
        attributes: Array<out Pair<String, String>>,
        block: (MarkupBuilder<TSupplierFacade>.() -> Unit)?
    ) {
        children += childMarkupBuilder

        startTagPrefix(name, wicketId, attributes)
        currentTextPart.append('>')
        if (block != null) {
            parts += ChildPart(childMarkupBuilder)
            currentTextPart = TextPart()
            parts += currentTextPart

            childMarkupBuilder.block()
        }
        endTag(name)
    }

    /**
     * Add a void element with the given name and associated with a Wicket component to the markup.
     *
     * @param childMarkupBuilder the child markup representing the element.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun voidWicketElement(
        childMarkupBuilder: ChildMarkupBuilder<TSupplierFacade>,
        name: String,
        wicketId: String,
        attributes: Array<out Pair<String, String>>,
    ) {
        children += childMarkupBuilder

        startTagPrefix(name, wicketId, attributes)
        currentTextPart.append('>')
    }

    /**
     * Append the prefix of a start tag to the markup text. The client has to add the closing greater-than sign (`>`) (after adding additional attributes).
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun startTagPrefix(name: String, wicketId: String, attributes: Array<out Pair<String, String>>) {
        startTagPrefix(name)
        currentTextPart
            .append(""" wicket:id="""")
            .append(wicketId)
            .append('"')
        attributes(attributes)
    }

    /**
     * Append the prefix of a start tag to the markup text. The client has to add the closing greater-than sign (`>`) (after adding additional attributes).
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     */
    private fun startTagPrefix(name: String) {
        currentTextPart
            .append('<')
            .append(name)
    }

    /**
     * Append the given attributes to the markup text.
     *
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun attributes(attributes: Array<out Pair<String, String>>) {
        attributes.forEach { (name, value) ->
            attribute(name, value)
        }
    }

    /**
     * Append the given attribute to the markup text.
     *
     * @param attribute the attribute to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun attribute(name: String, value: String) {
        currentTextPart
            .append(' ')
            .append(name)
            .append("""="""")
            .append(value)
            .append('"')
    }

    /**
     * Append an end tag to the markup text.
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     */
    private fun endTag(name: String) {
        currentTextPart
            .append("</")
            .append(name)
            .append('>')
    }
}
