package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import kotlin.reflect.KProperty1

/**
 * A value for an attribute.
 */
sealed interface AttributeValue

/**
 * A text value for an attribute.
 *
 * @param text the value.
 */
class Text(internal val text: String) : AttributeValue

/**
 * An attribute value referring to a descendent Wicket component. Used for [`wicket:enclosure`](https://nightlies.apache.org/wicket/guide/9.x/single.html#_hiding_decorating_elements_with_the_wicketenclosure_tag) elements and attributes.
 *
 * @param descendentSupplier the supplier of the descendent to refer to.
 */
class DescendentReference<TSupplier : MarkupContainer>(
    internal val descendentSupplier: KProperty1<TSupplier, Component>,
) : AttributeValue

/**
 * An attribute value referring to a form component somewhere in the markup hierarchy. Used for [`wicket:for`](https://cwiki.apache.org/confluence/display/WICKET/Wicket's+XHTML+tags#Wicket'sXHTMLtags-Attributewicket:for) attributes of HTML `label` elements, and `for` attributes of `wicket:label` elements.
 *
 * @param forComponentSupplier the supplier of the form component to refer to.
 */
class ForComponentReference<TSupplier : MarkupContainer>(
    internal val forComponentSupplier: KProperty1<TSupplier, MarkupContainer>,
) : AttributeValue
