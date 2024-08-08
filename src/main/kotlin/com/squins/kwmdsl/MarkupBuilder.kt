package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.apache.wicket.util.string.Strings
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1

/**
 * Markup builder that provides functions to add child markup, possibly associated with Wicket components, to it. For all (non-deprecated) HTML elements convenience extension functions are provided. There are additional convenience extension functions for elements where often only a `class` attribute is used: `class<element name>(...)`.
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 */
@WicketMarkupBuilder
abstract class MarkupBuilder<TSupplier : MarkupContainer> internal constructor() {
    /**
     * The text part to which new literal markup text must be added.
     */
    internal var currentTextPart = TextPart<TSupplier>()

    /**
     * The parts added to this markup builder.
     */
    private val parts = mutableListOf<MarkupPart<TSupplier>>(currentTextPart)

    /**
     * Child elements that are associated with Wicket components that are to be retrieved during the addition of the root markup to the markup container.
     */
    internal val children = mutableListOf<ChildMarkupBuilder<TSupplier>>()

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

    /**
     * Add a [`wicket:body`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_surrounding_existing_markup_with_border) element to the markup.
     */
    fun wicketBody() {
        currentTextPart.append("<wicket:body></wicket:body>")
    }

    /**
     * Add a [`wicket:border`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_surrounding_existing_markup_with_border) element to the markup, creating the children of the element using [block].
     *
     * @param block the code to build the children of the element.
     */
    fun wicketBorder(block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart.append("<wicket:border>")
        block()
        currentTextPart.append("</wicket:border>")
    }

    /**
     * Add a [`wicket:child`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_markup_inheritance_with_the_wicket_extend_tag) element to the markup, (optionally) creating the children of the element using [block].
     *
     * @param block the (optional) code to build the children of the element.
     */
    fun wicketChild(block: (MarkupBuilder<TSupplier>.() -> Unit)? = null) {
        currentTextPart.append("<wicket:child>")
        block?.invoke(this)
        currentTextPart.append("</wicket:child>")
    }

    /**
     * Add a [`wicket:container`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_put_javascript_inside_page_body) element to the markup. The client is responsible for adding the associated Wicket component to the correct parent.
     *
     * @param id the Wicket ID of the container. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        id: String,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        element("wicket:container", attr("wicket:id", id), block = block)
    }

    fun wicketContainer(
        repeated: Repeated,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        element("wicket:container", attr("wicket:id", repeated.wicketId), block = block)
    }

    /**
     * Add a [`wicket:container`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_put_javascript_inside_page_body) element to the markup, and use [supplier] to determine the Wicket ID to assign to the element, and to retrieve the associated Wicket component.
     *
     * @param supplier the function that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        supplier: KFunction1<TSupplier, Component>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        element(supplier, "wicket:container", block = block)
    }

    /**
     * Add a [`wicket:container`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_put_javascript_inside_page_body) element to the markup, and use [supplier] to determine the Wicket ID to assign to the element, and to retrieve the associated Wicket component.
     *
     * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param block the (optional) code for building the children of the element.
     */
    fun wicketContainer(
        supplier: KProperty1<TSupplier, Component>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        element(supplier, "wicket:container", block = block)
    }

    /**
     * Add a [`wicket:enclosure`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_hiding_decorating_elements_with_the_wicket_enclosure_tag) element to the markup, and, if given, using [childSupplier] to determine the component path to use for the `child` attribute.
     *
     * @param childSupplier the supplier of the child Wicket component that determines the visibility of this enclosure.
     * @param block the code for building the children of the element.
     */
    fun wicketEnclosure(
        childSupplier: ((TSupplier) -> Component)? = null,
        block: MarkupBuilder<TSupplier>.() -> Unit
    ) {
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

    /**
     * Create a [`wicket:enclosure`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:enclosure) attribute without a child path.
     *
     * @return an 'attribute': a pair of the attribute name and the attribute value.
     */
    fun wicketEnclosureAttribute() = attr("wicket:enclosure", "")

    /**
     * Add a [`wicket:enclosure`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:enclosure) attribute, using [childSupplier] to determine the component path to use for this attribute.
     *
     * @param childSupplier the supplier of the child Wicket component that determines the visibility of the element with this attribute.
     * @return an 'attribute': a pair of the attribute name and the attribute value.
     */
    fun wicketEnclosureAttribute(childSupplier: (TSupplier) -> Component) =
        "wicket:enclosure" to DescendentReference(childSupplier)

    /**
     * Create a [`wicket:enclosure`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:enclosure) attribute with child path [path].
     *
     * @path the path to the child Wicket component that determines the visibility of the element with this attribute. The client is responsible for making sure the path references an actual Wicket component.
     * @return an 'attribute': a pair of the attribute name and the attribute value.
     */
    fun wicketEnclosureAttribute(path: String) =
        "wicket:enclosure" to Text(path)

    /**
     * Add a [`wicket:extend`](https://nightlies.apache.org/wicket/guide/8.x/single.html#_markup_inheritance_with_the_wicket_extend_tag) element to the markup, creating the children of the element using [block].
     *
     * @param block the code to build the children of the element.
     */
    fun wicketExtend(block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart.append("<wicket:extend>")
        block()
        currentTextPart.append("</wicket:extend>")
    }

    /**
     * Add a [`wicket:for`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:for) attribute, using [forComponentSupplier] to determine the component path to use for this attribute.
     *
     * @param forComponentSupplier the supplier of the Wicket form component that the label containing this attribute is for.
     * @return an 'attribute': a pair of the attribute name and the attribute value.
     */
    fun wicketForAttribute(forComponentSupplier: (TSupplier) -> Component) =
        "wicket:for" to ForComponentReference(forComponentSupplier)

    /**
     * Create a [`wicket:for`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:for) attribute with child path [path].
     *
     * @path the path to the Wicket form component that the label containing this attribute is for. The client is responsible for making sure the path references an actual Wicket component.
     * @return an 'attribute': a pair of the attribute name and the attribute value.
     */
    fun wicketForAttribute(path: String) =
        "wicket:for" to Text(path)

    // TODO("Document: for the location in container markup where to inline the markup of a DSL fragment. Embedded")
    // TODO("Rename to `embedFragmentSnippet`")
    fun embedWicketFragment(markupSupplier: KCallable<IRootMarkup>) {
        currentTextPart
            .append("""<wicket:fragment wicket:id="""")
            .append(markupSupplier.name)
            .append("""">""")
            .append(markupSupplier.call().stream.asString())
            .append("</wicket:fragment>")
    }

    // TODO("Document: for defining DSL fragments outside of the component they are instantiated in: stand-alone")
    fun wicketFragment(wicketIdSupplier: KCallable<Unit>, block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart
            // TODO("Remove the Kotlin and HTML comment, as `standaloneFragmentMarkup` must add the comment (or `wicket:remove`)")
            // Searches for the fragments start at 1, so make sure there is at least 1 node before the fragments.
            .append("""<!-- --><wicket:fragment wicket:id="""")
            .append(wicketIdSupplier.name)
            .append("""">""")
        block()
        currentTextPart
            .append("</wicket:fragment>")
    }

    fun wicketHead(block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart.append("<wicket:head>")
        block()
        currentTextPart.append("</wicket:head>")
    }

    fun wicketHeaderItems() {
        currentTextPart.append("<wicket:header-items/>")
    }

    fun wicketLabel(
        forComponentSupplier: ((TSupplier) -> Component)? = null,
        key: String? = null,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        currentTextPart.append("<wicket:label")
        if (forComponentSupplier != null) {
            currentTextPart.append(""" for="""")
            parts += ForComponentReferencePart(
                // The `<wicket:label>` containing `for` is seen as a Wicket component, so add a path part for it.
                getPathAsList() + "",
                forComponentSupplier
            )
            currentTextPart = TextPart()
            parts += currentTextPart
            currentTextPart.append('"')
        }
        if (key != null) {
            currentTextPart
                .append(""" key="""")
                .append(key)
                .append('"')
        }
        currentTextPart.append(">")
        if (block != null) {
            block()
        }
        currentTextPart.append("</wicket:label>")
    }

    /**
     * Add a `wicket:link` element to the markup.
     *
     * @param block the code for building the children of the element.
     */
    fun wicketLink(block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart.append("<wicket:link>")
        block()
        currentTextPart.append("</wicket:link>")
    }

    /**
     * Add a `wicket:message` element to the markup.
     *
     * A convenience function for [wicketMessage] to keep internationalization code short and readable.
     *
     * @param key the message key. **Warning**: there is no validation and no escaping, so make sure the key is valid and safe.
     * @param escape whether to escape the message. Optional, `true` by default.
     * @param block the (optional) code for building the children of the element.
     */
    fun m(
        key: String,
        escape: Boolean = true,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
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
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
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

    fun wicketPanel(block: MarkupBuilder<TSupplier>.() -> Unit) {
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
    fun wicketRemove(block: MarkupBuilder<TSupplier>.() -> Unit) {
        currentTextPart.append("<wicket:remove>")
        block()
        currentTextPart.append("</wicket:remove>")
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
        vararg attributes: Pair<String, AttributeValue>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        startTagPrefix(name)
        attributes(*attributes)
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
        supplier: KFunction1<TSupplier, Component>,
        name: String,
        vararg attributes: Pair<String, AttributeValue>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        val childMarkup = ChildMarkupBuilder(this, supplier)
        wicketElement(childMarkup, name, supplier.name, attributes = attributes, block)
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
        supplier: KProperty1<TSupplier, Component>,
        name: String,
        vararg attributes: Pair<String, AttributeValue>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)? = null
    ) {
        val childMarkup = ChildMarkupBuilder(this, supplier)
        wicketElement(childMarkup, name, supplier.name, attributes = attributes, block)
    }

    /**
     * Add a void element with the given name to the markup.
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    fun voidElement(name: String, vararg attributes: Pair<String, AttributeValue>) {
        startTagPrefix(name)
        attributes(*attributes)
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
        supplier: KFunction1<TSupplier, Component>,
        name: String,
        vararg attributes: Pair<String, AttributeValue>,
    ) {
        val childMarkup = ChildMarkupBuilder(this, supplier)
        voidWicketElement(childMarkup, name, supplier.name, *attributes)
    }

    /**
     * Add a void element with the given name and associated with a Wicket component to the markup.
     *
     * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    fun voidElement(
        supplier: KProperty1<TSupplier, Component>,
        name: String,
        vararg attributes: Pair<String, AttributeValue>,
    ) {
        val childMarkup = ChildMarkupBuilder(this, supplier)
        voidWicketElement(childMarkup, name, supplier.name, *attributes)
    }

    /**
     * Add text to the markup. Contrary to the other functions, the text will be escaped before it is added.
     *
     * @param text the text to add.
     */
    fun text(text: String) {
        currentTextPart.append(Strings.escapeMarkup(text))
    }

    /**
     * Add text to the markup.
     *
     * @param text the text to add. **Warning**: there is no validation and no escaping, so make sure the text is valid and safe.
     */
    fun unsafeText(text: String) {
        currentTextPart.append(text)
    }

    internal fun appendParts(builder: StringBuilder) {
        parts.forEach { part ->
            when (part) {
                is ChildPart -> part.child.appendParts(builder)
                // All of the children are searched, instead of only the children of the element containing the
                // reference, as it is more difficult to store which range of children must be searched when creating
                // the reference. Although this does affect performance a bit, it is acceptable:
                //
                // * Not many pages have more than a few dozen components.
                // * The search only runs once for all component instances.
                is DescendentReferencePart -> builder.append(children.firstNotNullOf { child -> child.pathOf(part.supplier) })
                is ForComponentReferencePart -> builder.append(
                    relativizePath(
                        part.referencingComponentPath,
                        checkNotNull(pathFromRootOfAsList(part.forComponentSupplier)) {
                            "Could not find reference to: ${part.forComponentSupplier}, for referencing path: ${part.referencingComponentPath}"
                        }
                    )
                )

                is TextPart -> builder.append(part)
            }
        }
    }

    /**
     * Get the Wicket component path of this markup, as a list of strings.
     *
     * @return the Wicket component path, as a list.
     */
    internal abstract fun getPathAsList(): List<String>

    /**
     * Get the Wicket component path of the given supplier relative to the Wicket component of this builder, as a list of strings. The supplier may appear anywhere in the markup hierarchy.
     *
     * @param supplier the suppler of the component for which to get the path.
     * @return the Wicket component path of [supplier], as a list, or `null` if the supplier is not associated with a builder anywhere in the markup hierarchy.
     */
    internal abstract fun pathFromRootOfAsList(supplier: (TSupplier) -> Component): List<String>?

    internal fun buildChildren(): List<ChildMarkup<TSupplier>> = children.map { it.build() }

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
        childMarkupBuilder: ChildMarkupBuilder<TSupplier>,
        name: String,
        wicketId: String,
        vararg attributes: Pair<String, AttributeValue>,
        block: (MarkupBuilder<TSupplier>.() -> Unit)?
    ) {
        children += childMarkupBuilder

        startTagPrefix(name, wicketId, *attributes)
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
        childMarkupBuilder: ChildMarkupBuilder<TSupplier>,
        name: String,
        wicketId: String,
        vararg attributes: Pair<String, AttributeValue>,
    ) {
        children += childMarkupBuilder

        startTagPrefix(name, wicketId, *attributes)
        currentTextPart.append('>')
    }

    /**
     * Append the prefix of a start tag to the markup text. The client has to add the closing greater-than sign (`>`) (after adding additional attributes).
     *
     * @param name the element name. **Warning**: there is no validation and no escaping, so make sure the name is valid and safe.
     * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.
     * @param attributes the attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun startTagPrefix(name: String, wicketId: String, vararg attributes: Pair<String, AttributeValue>) {
        startTagPrefix(name)
        currentTextPart
            .append(""" wicket:id="""")
            .append(wicketId)
            .append('"')
        attributes(*attributes)
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
    private fun attributes(vararg attributes: Pair<String, AttributeValue>) {
        attributes.forEach { (name, value) ->
            attribute(name, value)
        }
    }

    /**
     * Append the given attribute to the markup text.
     *
     * @param attribute the attribute to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.
     */
    private fun attribute(name: String, value: AttributeValue) {
        currentTextPart
            .append(' ')
            .append(name)
            .append("""="""")
        when (value) {
            is DescendentReference<*> -> {
                @Suppress("UNCHECKED_CAST")
                parts += DescendentReferencePart(value.descendentSupplier as (TSupplier) -> Component)
                currentTextPart = TextPart()
                parts += currentTextPart
            }

            is ForComponentReference<*> -> {
                @Suppress("UNCHECKED_CAST")
                parts += ForComponentReferencePart(
                    // The element containing `wicket:for` is seen as a Wicket component, so add a path part for it.
                    getPathAsList() + "",
                    value.forComponentSupplier as (TSupplier) -> Component
                )
                currentTextPart = TextPart()
                parts += currentTextPart
            }

            is Text -> currentTextPart.append(value.text)
        }

        currentTextPart
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
