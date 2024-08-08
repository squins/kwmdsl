package kwmdsl.examples

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.component.resourcePath
import kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy.DslNoneHtmlNoneDslPage
import kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy.HtmlNoneDslNoneHtmlPage
import kwmdsl.examples.standard.deep_inheritance.DeepInheritanceSubPage
import kwmdsl.examples.standard.enclosure.EnclosuresPage
import kwmdsl.examples.standard.variants.VariantsPage
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.mapper.parameter.PageParameters
import java.nio.charset.Charset
import kotlin.reflect.KClass
import kwmdsl.examples.dsl_convenience_base_classes.border.BorderPage as DslConvenienceBorderPage
import kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance.DeepInheritanceSubPage as DslConvenienceDeepInheritanceSubPage
import kwmdsl.examples.dsl_convenience_base_classes.enclosure.EnclosuresPage as DslConvenienceEnclosuresPage
import kwmdsl.examples.dsl_convenience_base_classes.fragment.FragmentPage as DslConvenienceFragmentPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.LabelForPage as DslConvenienceLabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.WicketLabelForPage as DslConvenienceWicketLabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.link.LinkPage as DslConvenienceLinkPage
import kwmdsl.examples.dsl_convenience_base_classes.repeat.RepeatPage as DslConvenienceRepeatPage
import kwmdsl.examples.dsl_convenience_base_classes.variants.VariantsPage as DslConvenienceVariantsPage
import kwmdsl.examples.dsl_convenience_base_classes.web_markup_container.WebMarkupContainerPage as DslConvenienceWebMarkupContainerPage
import kwmdsl.examples.dsl_standard_base_classes.deep_inheritance.DeepInheritanceSubPage as DslStandardDeepInheritanceSubPage
import kwmdsl.examples.dsl_standard_base_classes.enclosure.EnclosuresPage as DslStandardEnclosuresPage

class ExamplesListPage(pageParameters: PageParameters) : ExamplesConvenienceBasePage(pageParameters) {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Kotlin Wicket Markup DSL Examples") }
                classDiv("content") {
                    h2 { text("Encoding") }
                    p {
                        text("Are we happy about the encoding (")
                        text(Charset.defaultCharset().displayName())
                        text(")? \uD83D\uDE04")
                        text(" If you do not see a happy face to the left, read about ")
                        wicketLink {
                            a(attr("href", EncodingPage::class.linkPath())) {
                                text("Wicket and the default JVM encoding")
                            }
                        }
                        text(".")
                    }

                    h2 { text("Wicket Links") }
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
                                attr(
                                    "src",
                                    ExamplesListPage::class.resourcePath<DslConvenienceLinkPage>("Apache Wicket.svg")
                                ),
                                attr("width", "50")
                            )
                        }
                    }

                    h2 { text("Standard") }

                    wicketLink {
                        ul {
                            link(DeepInheritanceSubPage::class, "Deep inheritance")
                            link(EnclosuresPage::class, "Enclosures")
                            link(VariantsPage::class, "Variants")
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
                            link(DslConvenienceBorderPage::class, "Border")
                            link(DslConvenienceFragmentPage::class, "Fragments")
                            link(DslConvenienceLinkPage::class, "Link")
                            link(DslConvenienceWebMarkupContainerPage::class, "Web markup container")
                            link(DslConvenienceRepeatPage::class, "Repeat")
                            link(DslConvenienceLabelForPage::class, "Label for form component")
                            link(DslConvenienceWicketLabelForPage::class, "Wicket label for form component")
                            link(DslConvenienceVariantsPage::class, "Variants")
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
