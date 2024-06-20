package kwmdsl.examples

import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.title
import com.squins.kwmdsl.ul
import kwmdsl.examples.dsl_convenience_base_classes.border.BorderPage
import kwmdsl.examples.dsl_convenience_base_classes.fragment.FragmentPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.LabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy.DslNoneHtmlNoneDslPage
import kwmdsl.examples.dsl_convenience_base_classes.repeat.RepeatPage
import kwmdsl.examples.dsl_convenience_base_classes.web_markup_container.WebMarkupContainerPage
import kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy.HtmlNoneDslNoneHtmlPage
import kwmdsl.examples.standard.deep_inheritance.DeepInheritanceSubPage
import kwmdsl.examples.standard.enclosure.EnclosuresPage
import kwmdsl.examples.standard.simple_inheritance.SimpleInheritancePage
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

                    h2 { text("Standard") }

                    wicketLink {
                        ul {
                            li { a(attr("href", SimpleInheritancePage::class.linkPath())) { text("Simple inheritance") } }
                            li { a(attr("href", DeepInheritanceSubPage::class.linkPath())) { text("Deep inheritance") } }
                            li { a(attr("href", EnclosuresPage::class.linkPath())) { text("Enclosures") } }
                        }
                    }

                    h2 { text("DSL, Standard Base Classes") }

                    wicketLink {
                        ul {
                            li { a(attr("href", DslStandardDeepInheritanceSubPage::class.linkPath())) { text("Deep inheritance") } }
                            li { a(attr("href", DslStandardEnclosuresPage::class.linkPath())) { text("Enclosures") } }
                            li { a(attr("href", HtmlNoneDslNoneHtmlPage::class.linkPath())) { text("Mixed markup in hierarchy") } }
                        }
                    }

                    h2 { text("DSL, Convenience Base Classes") }

                    wicketLink {
                        ul {
                            li { a(attr("href", DslConvenienceDeepInheritanceSubPage::class.linkPath())) { text("Deep inheritance") } }
                            li { a(attr("href", DslConvenienceEnclosuresPage::class.linkPath())) { text("Enclosures") } }
                            li { a(attr("href", DslNoneHtmlNoneDslPage::class.linkPath())) { text("Mixed markup in hierarchy") } }
                            li { a(attr("href", BorderPage::class.linkPath())) { text("Border") } }
                            li { a(attr("href", FragmentPage::class.linkPath())) { text("Fragments") } }
                            li { a(attr("href", WebMarkupContainerPage::class.linkPath())) { text("Web markup container") } }
                            li { a(attr("href", RepeatPage::class.linkPath())) { text("Repeat") } }
                            li { a(attr("href", LabelForPage::class.linkPath())) { text("Label for form component") } }
                        }
                    }
                }
            }
        }
    }
}
