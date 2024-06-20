package kwmdsl.examples.dsl_convenience_base_classes.label_for

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.form
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.h3
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.i
import com.squins.kwmdsl.input
import com.squins.kwmdsl.label
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model

class LabelForPage : KotlinWicketMarkupWebPage() {
    private val rootSibling by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Sibling")
        }
    }

    private val rootChild by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Child")
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

    private val nestedChildRootContainer by Wicket { WebMarkupContainer(it) }
    private val nestedChild by Wicket {
        TextField<String>(it).apply {
            label = Model.of("Child")
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
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Label for Form Component") }
                }
                body {
                    h1 { text("Label for Form Component") }

                    form {
                        h2 { text("At Root") }

                        h3 { text("Sibling") }

                        p {
                            label(wicketForAttribute(LabelForPage::rootSibling)) { wicketLabel() }
                            text(": ")
                            input(LabelForPage::rootSibling, attr("type", "text"))
                        }

                        h3 { text("Child") }

                        p {
                            label(wicketForAttribute(LabelForPage::rootChild)) {
                                wicketLabel()
                                text(": ")
                                input(LabelForPage::rootChild, attr("type", "text"))
                            }
                        }

                        h3 { text("Sibling of Parent") }

                        p {
                            span(LabelForPage::rootSiblingOfParentLabelContainer) {
                                label(wicketForAttribute(LabelForPage::rootSiblingOfParent)) { wicketLabel() }
                            }
                            text(": ")
                            input(LabelForPage::rootSiblingOfParent, attr("type", "text"))
                        }

                        h3 { text("Child of Sibling") }

                        p {
                            span(LabelForPage::rootChildOfSiblingLabelContainer) {
                                label(wicketForAttribute(LabelForPage::rootChildOfSibling)) { wicketLabel() }
                            }
                            span(LabelForPage::rootChildOfSiblingInputContainer) {
                                text(": ")
                                input(LabelForPage::rootChildOfSibling, attr("type", "text"))
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
                            text("This is the same case as ")
                            i { text("At Root") }
                            text(" » ")
                            i { text("Child of Sibling") }
                            text(" above ")
                        }

                        h3 { text("Child") }

                        p {
                            span(LabelForPage::nestedChildRootContainer) {
                                label(wicketForAttribute(LabelForPage::nestedChild)) {
                                    wicketLabel()
                                    text(": ")
                                    input(LabelForPage::nestedChild, attr("type", "text"))
                                }
                            }
                        }

                        h3 { text("Sibling of Parent") }

                        p {
                            span(LabelForPage::nestedSiblingOfParentRootContainer) {
                                span(LabelForPage::nestedSiblingOfParentLabelContainer) {
                                    label(wicketForAttribute(LabelForPage::nestedSiblingOfParent)) { wicketLabel() }
                                }
                                text(": ")
                                input(LabelForPage::nestedSiblingOfParent, attr("type", "text"))
                            }
                        }

                        h3 { text("Child of Sibling") }

                        p {
                            span(LabelForPage::nestedChildOfSiblingRootContainer) {
                                span(LabelForPage::nestedChildOfSiblingLabelContainer) {
                                    label(wicketForAttribute(LabelForPage::nestedChildOfSibling)) { wicketLabel() }
                                }
                                span(LabelForPage::nestedChildOfSiblingInputContainer) {
                                    text(": ")
                                    input(LabelForPage::nestedChildOfSibling, attr("type", "text"))
                                }
                            }
                        }

                        h3 { text("No Common Path Parts") }

                        p {
                            span(LabelForPage::nestedNoCommonPathPartsLabelRootContainer) {
                                span(LabelForPage::nestedNoCommonPathPartsLabelContainer) {
                                    label(wicketForAttribute(LabelForPage::nestedNoCommonPathParts)) { wicketLabel() }
                                }
                            }
                            span(LabelForPage::nestedNoCommonPathPartsInputRootContainer) {
                                span(LabelForPage::nestedNoCommonPathPartsInputContainer) {
                                    text(": ")
                                    input(LabelForPage::nestedNoCommonPathParts, attr("type", "text"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
