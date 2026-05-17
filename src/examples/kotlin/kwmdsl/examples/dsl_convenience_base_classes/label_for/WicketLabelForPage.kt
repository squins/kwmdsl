package kwmdsl.examples.dsl_convenience_base_classes.label_for

import com.squins.kwmdsl.InputType.TEXT
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrType
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.form
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.h3
import com.squins.kwmdsl.i
import com.squins.kwmdsl.input
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model

class WicketLabelForPage : ExamplesConvenienceBasePage() {
    private val rootSibling: TextField<String> =
        TextField<String>(::rootSibling.name).apply {
            label = Model.of("Sibling")
        }

    private val rootSiblingOfParentLabelContainer: WebMarkupContainer = WebMarkupContainer(::rootSiblingOfParentLabelContainer.name)
    private val rootSiblingOfParent: TextField<String> =
        TextField<String>(::rootSiblingOfParent.name).apply {
            label = Model.of("Sibling of parent")
        }

    private val rootChildOfSiblingLabelContainer: WebMarkupContainer = WebMarkupContainer(::rootChildOfSiblingLabelContainer.name)
    private val rootChildOfSiblingInputContainer: WebMarkupContainer = WebMarkupContainer(::rootChildOfSiblingInputContainer.name)
    private val rootChildOfSibling: TextField<String> =
        TextField<String>(::rootChildOfSibling.name).apply {
            label = Model.of("Child of sibling")
        }

    private val nestedSiblingRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedSiblingRootContainer.name)
    private val nestedSibling: TextField<String> =
        TextField<String>(::nestedSibling.name).apply {
            label = Model.of("Sibling")
        }

    private val nestedSiblingOfParentRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedSiblingOfParentRootContainer.name)
    private val nestedSiblingOfParentLabelContainer: WebMarkupContainer = WebMarkupContainer(::nestedSiblingOfParentLabelContainer.name)
    private val nestedSiblingOfParent: TextField<String> =
        TextField<String>(::nestedSiblingOfParent.name).apply {
            label = Model.of("Sibling of parent")
        }

    private val nestedChildOfSiblingRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedChildOfSiblingRootContainer.name)
    private val nestedChildOfSiblingLabelContainer: WebMarkupContainer = WebMarkupContainer(::nestedChildOfSiblingLabelContainer.name)
    private val nestedChildOfSiblingInputContainer: WebMarkupContainer = WebMarkupContainer(::nestedChildOfSiblingInputContainer.name)
    private val nestedChildOfSibling: TextField<String> =
        TextField<String>(::nestedChildOfSibling.name).apply {
            label = Model.of("Child of sibling")
        }

    private val nestedNoCommonPathPartsLabelRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsLabelRootContainer.name)
    private val nestedNoCommonPathPartsLabelContainer: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsLabelContainer.name)
    private val nestedNoCommonPathPartsInputRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsInputRootContainer.name)
    private val nestedNoCommonPathPartsInputContainer: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsInputContainer.name)
    private val nestedNoCommonPathParts: TextField<String> =
        TextField<String>(::nestedNoCommonPathParts.name).apply {
            label = Model.of("No common path parts")
        }

    private val rootBorder: Border = LabelForDivBorder(::rootBorder.name)
    private val labelLevel1Border: Border = LabelForStrongBorder(::labelLevel1Border.name)
    private val labelLevel2Border: Border = LabelForStrongBorder(::labelLevel2Border.name)
    private val labelLevel3Border: Border = LabelForStrongBorder(::labelLevel3Border.name)
    private val inputLevel1Border: Border = LabelForStrongBorder(::inputLevel1Border.name)
    private val inputLevel2Border: Border = LabelForStrongBorder(::inputLevel2Border.name)
    private val inputLevel3Border: Border = LabelForStrongBorder(::inputLevel3Border.name)
    private val nestedNoCommonPathPartsLabelRootContainerManyBorders: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsLabelRootContainerManyBorders.name)
    private val nestedNoCommonPathPartsLabelContainerManyBorders: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsLabelContainerManyBorders.name)
    private val nestedNoCommonPathPartsInputRootContainerManyBorders: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsInputRootContainerManyBorders.name)
    private val nestedNoCommonPathPartsInputContainerManyBorders: WebMarkupContainer = WebMarkupContainer(::nestedNoCommonPathPartsInputContainerManyBorders.name)
    private val nestedNoCommonPathPartsManyBorders: TextField<String> =
        TextField<String>(::nestedNoCommonPathPartsManyBorders.name).apply {
            label = Model.of("No common path parts, with many borders")
        }

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Wicket Label for Form Component (Convenience Base Classes)") }

    p {
        text("Shows the use of the ")
        code { text("<wicket:label>") }
        text(" element to output the label of a form component. The labels cannot be used to activate the form component, as ")
        code { text("<wicket:label>")}
        text(" does not output a ")
        code { text("<label>") }
        text(".")
    }

    div(attrClass("content")) {
        form {
            h2 { text("At Root") }

            h3 { text("Sibling") }

            p {
                wicketLabel(WLFPS::rootSibling)
                text(": ")
                input(WLFPS::rootSibling, attrType(TEXT))
            }

            h3 { text("Sibling of Parent") }

            p {
                span(WLFPS::rootSiblingOfParentLabelContainer) {
                    wicketLabel(WLFPS::rootSiblingOfParent)
                }
                text(": ")
                input(WLFPS::rootSiblingOfParent, attrType(TEXT))
            }

            h3 { text("Child of Sibling") }

            p {
                span(WLFPS::rootChildOfSiblingLabelContainer) {
                    wicketLabel(WLFPS::rootChildOfSibling)
                }
                span(WLFPS::rootChildOfSiblingInputContainer) {
                    text(": ")
                    input(WLFPS::rootChildOfSibling, attrType(TEXT))
                }
            }

            h3 { text("No Common Path Parts") }

            p {
                text("This is the same case as ")
                i { text("At Root") }
                text(" » ")
                i { text("Child of Sibling") }
                text(" above.")
            }

            h2 { text("Nested") }

            h3 { text("Sibling") }

            p {
                span(WLFPS::nestedSiblingRootContainer) {
                    wicketLabel(WLFPS::nestedSibling)
                    text(": ")
                    input(WLFPS::nestedSibling, attrType(TEXT))
                }
            }

            h3 { text("Sibling of Parent") }

            p {
                span(WLFPS::nestedSiblingOfParentRootContainer) {
                    span(WLFPS::nestedSiblingOfParentLabelContainer) {
                        wicketLabel(WLFPS::nestedSiblingOfParent)
                    }
                    text(": ")
                    input(WLFPS::nestedSiblingOfParent, attrType(TEXT))
                }
            }

            h3 { text("Child of Sibling") }

            p {
                span(WLFPS::nestedChildOfSiblingRootContainer) {
                    span(WLFPS::nestedChildOfSiblingLabelContainer) {
                        wicketLabel(WLFPS::nestedChildOfSibling)
                    }
                    span(WLFPS::nestedChildOfSiblingInputContainer) {
                        text(": ")
                        input(WLFPS::nestedChildOfSibling, attrType(TEXT))
                    }
                }
            }

            h3 { text("No Common Path Parts") }

            p {
                span(WLFPS::nestedNoCommonPathPartsLabelRootContainer) {
                    span(WLFPS::nestedNoCommonPathPartsLabelContainer) {
                        wicketLabel(WLFPS::nestedNoCommonPathParts)
                    }
                }
                span(WLFPS::nestedNoCommonPathPartsInputRootContainer) {
                    span(WLFPS::nestedNoCommonPathPartsInputContainer) {
                        text(": ")
                        input(WLFPS::nestedNoCommonPathParts, attrType(TEXT))
                    }
                }
            }
        }

        h3 { text("No Common Path Parts, With Many Borders") }

        div(WLFPS::rootBorder) {
            p {
                span(WLFPS::labelLevel1Border) {
                    span(WLFPS::nestedNoCommonPathPartsLabelRootContainerManyBorders) {
                        span(WLFPS::labelLevel2Border) {
                            span(WLFPS::nestedNoCommonPathPartsLabelContainerManyBorders) {
                                span(WLFPS::labelLevel3Border) {
                                    wicketLabel(WLFPS::nestedNoCommonPathPartsManyBorders)
                                }
                            }
                        }
                    }
                }
                span(WLFPS::inputLevel1Border) {
                    span(WLFPS::nestedNoCommonPathPartsInputRootContainerManyBorders) {
                        span(WLFPS::inputLevel2Border) {
                            span(WLFPS::nestedNoCommonPathPartsInputContainerManyBorders) {
                                text(": ")
                                span(WLFPS::inputLevel3Border) {
                                    input(WLFPS::nestedNoCommonPathPartsManyBorders, attrType(TEXT))
                                }
                            }
                        }
                    }
                }
            }
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

private typealias WLFPS = WicketLabelForPage
