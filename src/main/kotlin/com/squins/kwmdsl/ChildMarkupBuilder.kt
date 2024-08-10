package com.squins.kwmdsl

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.border.Border
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubclassOf

/**
 * Builder for markup that cannot be added directly to the parent markup builder, because the added element is associated with a Wicket component that is to be retrieved during the addition of the root markup to the markup container.
 *
 * @param TSupplier the markup container type having the properties and functions to get the Wicket components.
 * @param expectedWicketId the Wicket ID that the component returned by [supplier] is expected to have. During retrieval of the component, it is checked that the component ID matches this ID. This is done to find ID mismatches between the markup and the components quickly.
 * @param supplier the property that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
 */
internal class ChildMarkupBuilder<TSupplier : MarkupContainer> private constructor(
    private val parent: MarkupBuilder<TSupplier>,
    private val expectedWicketId: String,
    private val supplier: KProperty1<TSupplier, Component>,
    private val isForBorder: Boolean,
) : MarkupBuilder<TSupplier>() {
    /**
     * Create an instance with a property supplier.
     *
     * @param supplier the property that will be used to retrieve the Wicket component when the component associated with this markup has to be added to its parent.
     */
    internal constructor(parent: MarkupBuilder<TSupplier>, supplier: KProperty1<TSupplier, Component>) :
            this(parent, supplier.name, supplier, doesSupplierReturnBorder(supplier))

    override fun getPathAsList() =
        if (isForBorder) {
            parent.getPathAsList().toMutableList().apply {
                add(expectedWicketId)
                add("${expectedWicketId}_body")
            }
        } else {
            (parent.getPathAsList() + expectedWicketId)
        }

    /**
     * Build the child markup specified using this builder.
     *
     * @return the child markup.
     */
    internal fun build() = ChildMarkup(expectedWicketId, supplier, buildChildren())

    /**
     * Get the Wicket component path of the given supplier relative to the Wicket component of this builder. [supplier] must be the supplier of this builder or of a descendent builder.
     *
     * @param supplier the suppler of the component for which to get the path.
     * @return the Wicket component path (never containing parent operators (`..`)) of [supplier], or `null` if the supplier is not associated with this builder or a descendent builder.
     */
    internal fun pathOf(supplier: KProperty1<TSupplier, Component>): String? =
        if (supplier == this@ChildMarkupBuilder.supplier) {
            expectedWicketId
        } else {
            children.firstNotNullOfOrNull { child -> child.pathOf(supplier) }
                ?.let { subPath -> "$expectedWicketId:$subPath" }
        }

    /**
     * Get the Wicket component path of the given supplier relative to the Wicket component of this builder, as a list of strings. [supplier] must be the supplier of this builder or of a descendent builder.
     *
     * @param supplier the suppler of the component for which to get the path.
     * @return the Wicket component path (never containing parent operators (`..`)) of [supplier] as a list, or `null` if the supplier is not associated with this builder or a descendent builder.
     */
    internal fun pathOfAsList(supplier: KProperty1<TSupplier, Component>): List<String>? =
        if (supplier == this@ChildMarkupBuilder.supplier) {
            listOf(expectedWicketId)
        } else {
            children.firstNotNullOfOrNull { child -> child.pathOfAsList(supplier) }
                ?.let { subPath ->
                    subPath.toMutableList().apply {
                        if (isForBorder) {
                            add(0, "${expectedWicketId}_body")
                        }
                        add(0, expectedWicketId)
                    }
                }
        }

    override fun pathFromRootOfAsList(supplier: KProperty1<TSupplier, Component>) = parent.pathFromRootOfAsList(supplier)
}

private fun doesSupplierReturnBorder(supplier: KCallable<Component>) =
    (supplier.returnType.classifier as? KClass<*>)?.isSubclassOf(Border::class) == true
