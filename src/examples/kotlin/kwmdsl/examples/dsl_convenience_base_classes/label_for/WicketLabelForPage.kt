package kwmdsl.examples.dsl_convenience_base_classes.label_for

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
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
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model

class WicketLabelForPage : ExamplesConvenienceBasePage() {
    private val rootSibling by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Sibling")
        }
    }

    private val rootSiblingOfParentLabelContainer by Wicket { WebMarkupContainer(it) }
    private val rootSiblingOfParent by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Sibling of parent")
        }
    }

    private val rootChildOfSiblingLabelContainer by Wicket { WebMarkupContainer(it) }
    private val rootChildOfSiblingInputContainer by Wicket { WebMarkupContainer(it) }
    private val rootChildOfSibling by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Child of sibling")
        }
    }

    private val nestedSiblingRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedSibling by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Sibling")
        }
    }

    private val nestedSiblingOfParentRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedSiblingOfParentLabelContainer by Wicket { WebMarkupContainer(it) }
    private val nestedSiblingOfParent by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Sibling of parent")
        }
    }

    private val nestedChildOfSiblingRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedChildOfSiblingLabelContainer by Wicket { WebMarkupContainer(it) }
    private val nestedChildOfSiblingInputContainer by Wicket { WebMarkupContainer(it) }
    private val nestedChildOfSibling by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Child of sibling")
        }
    }

    private val nestedNoCommonPathPartsLabelRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedNoCommonPathPartsLabelContainer by Wicket { WebMarkupContainer(it) }
    private val nestedNoCommonPathPartsInputRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedNoCommonPathPartsInputContainer by Wicket { WebMarkupContainer(it) }
    private val nestedNoCommonPathParts by Wicket {
        TextField<String>(it).apply {
            label = Model.of("No common path parts")
        }
    }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketExtend {
                h1(attr("class", "title")) { text("Wicket Label for Form Component") }

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
                }
            }
        }
    }
}
