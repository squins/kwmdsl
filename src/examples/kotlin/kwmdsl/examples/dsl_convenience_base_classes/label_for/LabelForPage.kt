package kwmdsl.examples.dsl_convenience_base_classes.label_for

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupBorder
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.dsl_convenience_base_classes.firstSourceCodeLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model

class LabelForPage : ExamplesConvenienceBasePage() {
    private val rootSibling: TextField<String> =
        TextField<String>(::rootSibling.name).apply {
            label = Model.of("Sibling")
        }

    private val rootChild: TextField<String> =
        TextField<String>(::rootChild.name).apply {
            label = Model.of("Child")
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

    private val nestedChildRootContainer: WebMarkupContainer = WebMarkupContainer(::nestedChildRootContainer.name)
    private val nestedChild: TextField<String> =
        TextField<String>(::nestedChild.name).apply {
            label = Model.of("Child")
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
    classH1("title") { text("Label for Form Component") }

    classDiv("content") {
        form {
            h2 { text("At Root") }

            h3 { text("Sibling") }

            p {
                label(wicketForAttribute(LFPS::rootSibling)) { wicketLabel() }
                text(": ")
                input(LFPS::rootSibling, attr("type", "text"))
            }

            h3 { text("Child") }

            p {
                label(wicketForAttribute(LFPS::rootChild)) {
                    wicketLabel()
                    text(": ")
                    input(LFPS::rootChild, attr("type", "text"))
                }
            }

            h3 { text("Sibling of Parent") }

            p {
                span(LFPS::rootSiblingOfParentLabelContainer) {
                    label(wicketForAttribute(LFPS::rootSiblingOfParent)) { wicketLabel() }
                }
                text(": ")
                input(LFPS::rootSiblingOfParent, attr("type", "text"))
            }

            h3 { text("Child of Sibling") }

            p {
                span(LFPS::rootChildOfSiblingLabelContainer) {
                    label(wicketForAttribute(LFPS::rootChildOfSibling)) { wicketLabel() }
                }
                span(LFPS::rootChildOfSiblingInputContainer) {
                    text(": ")
                    input(LFPS::rootChildOfSibling, attr("type", "text"))
                }
            }

            h3 { text("No Common Path Parts") }

            p {
                text("This is the same case as ")
                i { text("At Root") }
                text(" » ")
                i { text("Child of Sibling") }
                text(" above ")
            }

            h2 { text("Nested") }

            h3 { text("Sibling") }

            p {
                span(LFPS::nestedSiblingRootContainer) {
                    label(wicketForAttribute(LFPS::nestedSibling)) { wicketLabel() }
                    text(": ")
                    input(LFPS::nestedSibling, attr("type", "text"))
                }
            }

            h3 { text("Child") }

            p {
                span(LFPS::nestedChildRootContainer) {
                    label(wicketForAttribute(LFPS::nestedChild)) {
                        wicketLabel()
                        text(": ")
                        input(LFPS::nestedChild, attr("type", "text"))
                    }
                }
            }

            h3 { text("Sibling of Parent") }

            p {
                span(LFPS::nestedSiblingOfParentRootContainer) {
                    span(LFPS::nestedSiblingOfParentLabelContainer) {
                        label(wicketForAttribute(LFPS::nestedSiblingOfParent)) { wicketLabel() }
                    }
                    text(": ")
                    input(LFPS::nestedSiblingOfParent, attr("type", "text"))
                }
            }

            h3 { text("Child of Sibling") }

            p {
                span(LFPS::nestedChildOfSiblingRootContainer) {
                    span(LFPS::nestedChildOfSiblingLabelContainer) {
                        label(wicketForAttribute(LFPS::nestedChildOfSibling)) { wicketLabel() }
                    }
                    span(LFPS::nestedChildOfSiblingInputContainer) {
                        text(": ")
                        input(LFPS::nestedChildOfSibling, attr("type", "text"))
                    }
                }
            }

            h3 { text("No Common Path Parts") }

            p {
                span(LFPS::nestedNoCommonPathPartsLabelRootContainer) {
                    span(LFPS::nestedNoCommonPathPartsLabelContainer) {
                        label(wicketForAttribute(LFPS::nestedNoCommonPathParts)) { wicketLabel() }
                    }
                }
                span(LFPS::nestedNoCommonPathPartsInputRootContainer) {
                    span(LFPS::nestedNoCommonPathPartsInputContainer) {
                        text(": ")
                        input(LFPS::nestedNoCommonPathParts, attr("type", "text"))
                    }
                }
            }

            h3 { text("No Common Path Parts, With Many Borders") }

            div(LFPS::rootBorder) {
                p {
                    span(LFPS::labelLevel1Border) {
                        span(LFPS::nestedNoCommonPathPartsLabelRootContainerManyBorders) {
                            span(LFPS::labelLevel2Border) {
                                span(LFPS::nestedNoCommonPathPartsLabelContainerManyBorders) {
                                    span(LFPS::labelLevel3Border) {
                                        label(wicketForAttribute(LFPS::nestedNoCommonPathPartsManyBorders)) {
                                            wicketLabel()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    span(LFPS::inputLevel1Border) {
                        span(LFPS::nestedNoCommonPathPartsInputRootContainerManyBorders) {
                            span(LFPS::inputLevel2Border) {
                                span(LFPS::nestedNoCommonPathPartsInputContainerManyBorders) {
                                    text(": ")
                                    span(LFPS::inputLevel3Border) {
                                        input(LFPS::nestedNoCommonPathPartsManyBorders, attr("type", "text"))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

private typealias LFPS = LabelForPage

internal class LabelForDivBorder(id: String) : KotlinWicketMarkupBorder(id) {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addToBorder(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = borderMarkup {
            wicketBorder {
                div {
                    wicketBody()
                }
            }
        }
    }
}

internal class LabelForStrongBorder(id: String) : KotlinWicketMarkupBorder(id) {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addToBorder(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = borderMarkup {
            wicketBorder {
                strong {
                    wicketBody()
                }
            }
        }
    }
}
