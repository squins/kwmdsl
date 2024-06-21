package kwmdsl.examples

import com.squins.kwmdsl.MarkupBuilder
import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.img
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.title
import com.squins.kwmdsl.ul
import kwmdsl.examples.dsl_convenience_base_classes.border.BorderPage
import kwmdsl.examples.dsl_convenience_base_classes.fragment.FragmentPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.LabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.WicketLabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.link.LinkPage
import kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy.DslNoneHtmlNoneDslPage
import kwmdsl.examples.dsl_convenience_base_classes.repeat.RepeatPage
import kwmdsl.examples.dsl_convenience_base_classes.web_markup_container.WebMarkupContainerPage
import kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy.HtmlNoneDslNoneHtmlPage
import kwmdsl.examples.standard.deep_inheritance.DeepInheritanceSubPage
import kwmdsl.examples.standard.enclosure.EnclosuresPage
import kwmdsl.examples.standard.simple_inheritance.SimpleInheritancePage
import org.apache.wicket.markup.html.WebPage
import kotlin.reflect.KClass
import kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance.DeepInheritanceSubPage as DslConvenienceDeepInheritanceSubPage
import kwmdsl.examples.dsl_convenience_base_classes.enclosure.EnclosuresPage as DslConvenienceEnclosuresPage
import kwmdsl.examples.dsl_standard_base_classes.deep_inheritance.DeepInheritanceSubPage as DslStandardDeepInheritanceSubPage
import kwmdsl.examples.dsl_standard_base_classes.enclosure.EnclosuresPage as DslStandardEnclosuresPage

class ExamplesListPage : KotlinWicketMarkupWebPage() {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup<ExamplesListPage> {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Kotlin Wicket Markup DSL Examples") }
                }
                body {
                    h1 { text("Kotlin Wicket Markup DSL Examples") }

                    p {
                        text("This page also shows the use of ")
                        code { text("<wicket:link>") }
                        text(" for resources (images) and links.")
                    }

                    p {
                        wicketLink {
                            text("In this package: ")
                            img(
                                attr("src", "Apache Wicket.svg"),
                                attr("width", "50")
                            )
                            text(", from a sub package: ")
                            img(
                                attr("src", ExamplesListPage::class.linkPath<LinkPage>("Apache Wicket.svg")),
                                attr("width", "50")
                            )
                        }
                    }

                    h2 { text("Standard") }

                    wicketLink {
                        ul {
                            link(SimpleInheritancePage::class, "Simple inheritance")
                            link(DeepInheritanceSubPage::class, "Deep inheritance")
                            link(EnclosuresPage::class, "Enclosures")
                        }
                    }

                    h2 { text("DSL, Standard Base Classes") }

                    wicketLink {
                        ul {
                            link(DslStandardDeepInheritanceSubPage::class, "Deep inheritance")
                            link(DslStandardEnclosuresPage::class, "Enclosures")
                            link(HtmlNoneDslNoneHtmlPage::class, "Mixed markup in hierarchy")
                        }
                    }

                    h2 { text("DSL, Convenience Base Classes") }

                    wicketLink {
                        ul {
                            link(DslConvenienceDeepInheritanceSubPage::class, "Deep inheritance")
                            link(DslConvenienceEnclosuresPage::class, "Enclosures")
                            link(DslNoneHtmlNoneDslPage::class, "Mixed markup in hierarchy")
                            link(BorderPage::class, "Border")
                            link(FragmentPage::class, "Fragments")
                            link(LinkPage::class, "Link")
                            link(WebMarkupContainerPage::class, "Web markup container")
                            link(RepeatPage::class, "Repeat")
                            link(LabelForPage::class, "Label for form component")
                            link(WicketLabelForPage::class, "Wicket label for form component")
                        }
                    }
                }
            }
        }
    }
}

private fun MarkupBuilder<ExamplesListPage>.link(examplePageClass: KClass<out WebPage>, text: String) {
    li { a(attr("href", examplePageClass.linkPath())) { text(text) } }
}
