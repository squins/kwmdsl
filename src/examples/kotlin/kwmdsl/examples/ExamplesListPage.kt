package kwmdsl.examples

import com.squins.kwmdsl.a
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
import kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy.DslNoneHtmlNoneDslPage
import kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy.HtmlNoneDslNoneHtmlPage
import kwmdsl.examples.standard.deep_inheritance.DeepInheritanceSubPage
import kwmdsl.examples.standard.enclosure.EnclosuresPage
import kwmdsl.examples.standard.simple_inheritance.SimpleInheritancePage
import kwmdsl.examples.dsl_convenience_base_classes.P4_3_HomePage as DslConvenienceP4_3_HomePage
import kwmdsl.examples.dsl_convenience_base_classes.P5_4_1_SimpleLoginPage as DslConvenienceP5_4_1_SimpleLoginPage
import kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance.DeepInheritanceSubPage as DslConvenienceDeepInheritanceSubPage
import kwmdsl.examples.dsl_convenience_base_classes.enclosure.EnclosuresPage as DslConvenienceEnclosuresPage
import kwmdsl.examples.dsl_standard_base_classes.P4_3_HomePage as DslStandardP4_3_HomePage
import kwmdsl.examples.dsl_standard_base_classes.P5_4_1_SimpleLoginPage as DslStandardP5_4_1_SimpleLoginPage
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
            html {
                head {
                    title { text("Kotlin Wicket Markup DSL Examples") }
                }
                body {
                    h1 { text("Kotlin Wicket Markup DSL Examples") }

                    h2 { text("Standard") }

                    wicketLink {
                        ul {
                            li { a("href" to SimpleInheritancePage::class.linkPath()) { text("Simple inheritance") } }
                            li { a("href" to DeepInheritanceSubPage::class.linkPath()) { text("Deep inheritance") } }
                            li { a("href" to EnclosuresPage::class.linkPath()) { text("Enclosures") } }
                        }
                    }

                    h2 { text("DSL, Standard Base Classes") }

                    wicketLink {
                        ul {
                            li { a("href" to DslStandardDeepInheritanceSubPage::class.linkPath()) { text("Deep inheritance") } }
                            li { a("href" to DslStandardEnclosuresPage::class.linkPath()) { text("Enclosures") } }
                            li { a("href" to HtmlNoneDslNoneHtmlPage::class.linkPath()) { text("Mixed markup in hierarchy") } }
                            li { a("href" to DslStandardP4_3_HomePage::class.linkPath()) { text("4.3 HomePage") } }
                            li { a("href" to DslStandardP5_4_1_SimpleLoginPage::class.linkPath()) { text("5.4.1 SimpleLoginPage") } }
                        }
                    }

                    h2 { text("DSL, Convenience Base Classes") }

                    wicketLink {
                        ul {
                            li { a("href" to DslConvenienceDeepInheritanceSubPage::class.linkPath()) { text("Deep inheritance") } }
                            li { a("href" to DslConvenienceEnclosuresPage::class.linkPath()) { text("Enclosures") } }
                            li { a("href" to DslNoneHtmlNoneDslPage::class.linkPath()) { text("Mixed markup in hierarchy") } }
                            li { a("href" to BorderPage::class.linkPath()) { text("Border") } }
                            li { a("href" to FragmentPage::class.linkPath()) { text("Fragments") } }
                            li { a("href" to DslConvenienceP4_3_HomePage::class.linkPath()) { text("4.3 HomePage") } }
                            li { a("href" to DslConvenienceP5_4_1_SimpleLoginPage::class.linkPath()) { text("5.4.1 SimpleLoginPage") } }
                        }
                    }
                }
            }
        }
    }
}
