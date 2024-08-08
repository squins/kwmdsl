package kwmdsl.examples.dsl_convenience_base_classes.label_for

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model
import org.apache.wicket.request.mapper.parameter.PageParameters

class WicketLabelForPage(pageParameters: PageParameters) : ExamplesConvenienceBasePage(pageParameters) {
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
            wicketExtend {
                classH1("title") { text("Wicket Label for Form Component") }

                classDiv("content") {
                    form {
                        h2 { text("At Root") }

                        h3 { text("Sibling") }

                        p {
                            wicketLabel(WicketLabelForPage::rootSibling)
                            text(": ")
                            input(WicketLabelForPage::rootSibling, attr("type", "text"))
                        }

                        h3 { text("Sibling of Parent") }

                        p {
                            span(WicketLabelForPage::rootSiblingOfParentLabelContainer) {
                                wicketLabel(WicketLabelForPage::rootSiblingOfParent)
                            }
                            text(": ")
                            input(WicketLabelForPage::rootSiblingOfParent, attr("type", "text"))
                        }

                        h3 { text("Child of Sibling") }

                        p {
                            span(WicketLabelForPage::rootChildOfSiblingLabelContainer) {
                                wicketLabel(WicketLabelForPage::rootChildOfSibling)
                            }
                            span(WicketLabelForPage::rootChildOfSiblingInputContainer) {
                                text(": ")
                                input(WicketLabelForPage::rootChildOfSibling, attr("type", "text"))
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
                            span(WicketLabelForPage::nestedSiblingRootContainer) {
                                wicketLabel(WicketLabelForPage::nestedSibling)
                                text(": ")
                                input(WicketLabelForPage::nestedSibling, attr("type", "text"))
                            }
                        }

                        h3 { text("Sibling of Parent") }

                        p {
                            span(WicketLabelForPage::nestedSiblingOfParentRootContainer) {
                                span(WicketLabelForPage::nestedSiblingOfParentLabelContainer) {
                                    wicketLabel(WicketLabelForPage::nestedSiblingOfParent)
                                }
                                text(": ")
                                input(WicketLabelForPage::nestedSiblingOfParent, attr("type", "text"))
                            }
                        }

                        h3 { text("Child of Sibling") }

                        p {
                            span(WicketLabelForPage::nestedChildOfSiblingRootContainer) {
                                span(WicketLabelForPage::nestedChildOfSiblingLabelContainer) {
                                    wicketLabel(WicketLabelForPage::nestedChildOfSibling)
                                }
                                span(WicketLabelForPage::nestedChildOfSiblingInputContainer) {
                                    text(": ")
                                    input(WicketLabelForPage::nestedChildOfSibling, attr("type", "text"))
                                }
                            }
                        }

                        h3 { text("No Common Path Parts") }

                        p {
                            span(WicketLabelForPage::nestedNoCommonPathPartsLabelRootContainer) {
                                span(WicketLabelForPage::nestedNoCommonPathPartsLabelContainer) {
                                    wicketLabel(WicketLabelForPage::nestedNoCommonPathParts)
                                }
                            }
                            span(WicketLabelForPage::nestedNoCommonPathPartsInputRootContainer) {
                                span(WicketLabelForPage::nestedNoCommonPathPartsInputContainer) {
                                    text(": ")
                                    input(WicketLabelForPage::nestedNoCommonPathParts, attr("type", "text"))
                                }
                            }
                        }
                    }

                    h3 { text("No Common Path Parts, With Many Borders") }

                    div(WicketLabelForPage::rootBorder) {
                        p {
                            span(WicketLabelForPage::labelLevel1Border) {
                                span(WicketLabelForPage::nestedNoCommonPathPartsLabelRootContainerManyBorders) {
                                    span(WicketLabelForPage::labelLevel2Border) {
                                        span(WicketLabelForPage::nestedNoCommonPathPartsLabelContainerManyBorders) {
                                            span(WicketLabelForPage::labelLevel3Border) {
                                                wicketLabel(WicketLabelForPage::nestedNoCommonPathPartsManyBorders)
                                            }
                                        }
                                    }
                                }
                            }
                            span(WicketLabelForPage::inputLevel1Border) {
                                span(WicketLabelForPage::nestedNoCommonPathPartsInputRootContainerManyBorders) {
                                    span(WicketLabelForPage::inputLevel2Border) {
                                        span(WicketLabelForPage::nestedNoCommonPathPartsInputContainerManyBorders) {
                                            text(": ")
                                            span(WicketLabelForPage::inputLevel3Border) {
                                                input(WicketLabelForPage::nestedNoCommonPathPartsManyBorders, attr("type", "text"))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
