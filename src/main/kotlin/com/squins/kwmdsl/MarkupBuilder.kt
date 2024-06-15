package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.string.Strings
import java.nio.charset.Charset
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1
import kotlin.text.Charsets.UTF_8

/**
 * Markup that provides functions to add child markup to it. For all HTML elements, and elements that only have a `class` attribute, convenience extension functions are provided.
 *
 * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.
 * @param builder the string builder for the markup text.
 */
@WicketMarkupBuilder
abstract class MarkupBuilder<TSupplierFacade : MarkupContainer> internal constructor(protected val builder: StringBuilder) {
    /**
     * Child elements that are associated with Wicket components that are to be retrieved during the addition of the root markup to the markup container.
     */
    private val children = mutableListOf<ChildMarkupBuilder<TSupplierFacade>>()

    fun xmlDeclaration(version: String = "1.0", encoding: Charset = UTF_8) {
        builder.append("""<?xml version="$version" encoding="${encoding.name()}"?>""")
    }

    /**
     * Add a document type declaration to the markup.
     *
     * @param value the document type. **Warning**: there is no validation and no escaping, so make sure the document type is valid and safe.
     */
    fun docType(value: String) {
        builder
            .append("<!DOCTYPE ")
            .append(value)
            .append('>')
    }

    // TODO("Body: check if complete")
    /**
     * Add a `wicket:body` element to the markup.
     */
    fun wicketBody() {
        builder.append("<wicket:body></wicket:body>")
    }

    fun wicketBorder(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:border>")
        block()
        builder.append("</wicket:border>")
    }

    fun wicketChild(block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null) {
        builder.append("<wicket:child>")
        block?.invoke(this)
        builder.append("</wicket:child>")
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

    // TODO("Enclosure, with support for `child`. How to use a reference (to a possibly nested component) instead of a literal? Or skip it to force use of `EnclosureContainer`?")

    fun wicketExtend(block: (MarkupBuilder<TSupplierFacade>.() -> Unit)) {
        builder.append("<wicket:extend>")
        block()
        builder.append("</wicket:extend>")
    }

    // TODO("Fragment, with `wicket:id`")

    // TODO("Header items. Only allowed in `<head>`")

    /**
     * Add a `wicket:link` element to the markup.
     *
     * @param block the code for building the children of the element.
     */
    fun wicketLink(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:link>")
        block()
        builder.append("</wicket:link>")
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
        builder
            .append("<wicket:message key=\"")
            .append(key)
            .append("\" escape=\"")
            .append(escape)
            .append("\">")
        if (block != null) {
            block()
        }
        builder.append("</wicket:message>")
    }

    fun wicketPanel(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:panel>")
        block()
        builder.append("</wicket:panel>")
    }

    /**
     * Add a `wicket:remove` element to the markup.
     *
     * @param block the code for building the children of the element.
     */
    fun wicketRemove(block: MarkupBuilder<TSupplierFacade>.() -> Unit) {
        builder.append("<wicket:remove>")
        block()
        builder.append("</wicket:remove>")
    }

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
        builder.append('>')
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
        val childMarkup = ChildMarkupBuilder(supplier, builder)
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
        val childMarkup = ChildMarkupBuilder(supplier, builder)
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
        builder.append('>')
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
        val childMarkup = ChildMarkupBuilder(supplier, builder)
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
        val childMarkup = ChildMarkupBuilder(supplier, builder)
        voidWicketElement(childMarkup, name, supplier.name, attributes)
    }

    /**
     * Add text to the markup. Contrary to the other functions, the text will be escaped before it is added.
     *
     * @param text the text to add.
     */
    fun text(text: String) {
        builder.append(Strings.escapeMarkup(text))
    }

    // TODO("Unescaped text?")

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
        builder.append('>')
        if (block != null) {
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
        builder.append('>')
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
        builder
            .append(" wicket:id=\"")
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
        builder
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
        builder
            .append(' ')
            .append(name)
            .append("=\"")
            .append(value)
            .append('"')
    }

    /**
     * Append an end tag to the markup text.
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     */
    private fun endTag(name: String) {
        builder
            .append("</")
            .append(name)
            .append('>')
    }
}
