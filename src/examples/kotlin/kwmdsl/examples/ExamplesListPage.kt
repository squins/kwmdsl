package kwmdsl.examples

import com.squins.kwmdsl.MarkupBuilder
import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.component.resourcePath
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.h3
import com.squins.kwmdsl.img
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.ul
import kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy.DslNoneHtmlNoneDslPage
import kwmdsl.examples.dsl_convenience_base_classes.page_parameters.PageParametersWithLateinitVarPropertiesPage
import kwmdsl.examples.dsl_convenience_base_classes.page_parameters.PageParametersWithValPropertiesPage
import kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy.HtmlNoneDslNoneHtmlPage
import kwmdsl.examples.standard.deep_inheritance.DeepInheritanceSubPage
import kwmdsl.examples.standard.enclosure.EnclosuresPage
import kwmdsl.examples.standard.variants.VariantsPage
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.request.mapper.parameter.PageParameters
import java.nio.charset.Charset
import kotlin.reflect.KClass
import kwmdsl.examples.dsl_convenience_base_classes.border.BorderPage as DslConvenienceBorderPage
import kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance.DeepInheritanceSubPage as DslConvenienceDeepInheritanceSubPage
import kwmdsl.examples.dsl_convenience_base_classes.enclosure.EnclosuresPage as DslConvenienceEnclosuresPage
import kwmdsl.examples.dsl_convenience_base_classes.fragment.FragmentPage as DslConvenienceFragmentPage
import kwmdsl.examples.dsl_convenience_base_classes.hello_world.HelloWorldPage as DslConvenienceHelloWorldPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.LabelForPage as DslConvenienceLabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.label_for.WicketLabelForPage as DslConvenienceWicketLabelForPage
import kwmdsl.examples.dsl_convenience_base_classes.link.LinkPage as DslConvenienceLinkPage
import kwmdsl.examples.dsl_convenience_base_classes.repeat.RepeatPage as DslConvenienceRepeatPage
import kwmdsl.examples.dsl_convenience_base_classes.unsafe_text.UnsafeTextPage as DslConvenienceUnsafeTextPage
import kwmdsl.examples.dsl_convenience_base_classes.variants.VariantsPage as DslConvenienceVariantsPage
import kwmdsl.examples.dsl_convenience_base_classes.web_markup_container.WebMarkupContainerPage as DslConvenienceWebMarkupContainerPage
import kwmdsl.examples.dsl_standard_base_classes.deep_inheritance.DeepInheritanceSubPage as DslStandardDeepInheritanceSubPage
import kwmdsl.examples.dsl_standard_base_classes.enclosure.EnclosuresPage as DslStandardEnclosuresPage

class ExamplesListPage : ExamplesConvenienceBasePage() {
    private val pageParametersWithValPropertiesPageLink: Link<String> =
        object : Link<String>(::pageParametersWithValPropertiesPageLink.name) {
            override fun onClick() {
                setResponsePage(PageParametersWithValPropertiesPage::class.java, PageParameters().apply {
                    add("text", "Using val properties!")
                })
            }
        }
    private val pageParametersWithLateinitVarPropertiesPageLink: Link<String> =
        object : Link<String>(::pageParametersWithLateinitVarPropertiesPageLink.name) {
            override fun onClick() {
                setResponsePage(PageParametersWithLateinitVarPropertiesPage::class.java, PageParameters().apply {
                    add("text", "Using lateinit var properties!")
                })
            }
        }

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Kotlin Wicket Markup DSL Examples") }
    div(attrClass("content")) {
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
                        ELPS::class.resourcePath<DslConvenienceLinkPage>("Apache Wicket.svg")
                    ),
                    attr("width", "50")
                )
            }
        }

        h2 { text("Examples") }
        p {
            text("The examples are available in 3 variants, so you can easily compare the implementations:")
        }

        ul {
            li { text("Standard: Wicket without using the DSL at all.") }
            li { text("DSL, standard base classes: markup defined using the DSL, but using the standard Wicket base classes. This shows that you can use the DSL for sub classes of existing Wicket components.") }
            li { text("DSL, convenience base classes: markup defined using the DSL, and using the convenience base classes that provide part of the functionality needed to use the DSL.") }
        }

        p {
            text("The last 2 are almost completely the same, as the only difference is where the code to enable using the DSL is located.")
        }

        p {
            text("Most examples link to (some) source files. But it is best to look at all of the source files using your IDE.")
        }

        h3 { text("Standard") }
        wicketLink {
            ul {
                link(DeepInheritanceSubPage::class, "Deep inheritance")
                link(EnclosuresPage::class, "Enclosures")
                link(VariantsPage::class, "Variants")
            }
        }

        h3 { text("DSL, Standard Base Classes") }
        wicketLink {
            ul {
                link(DslStandardDeepInheritanceSubPage::class, "Deep inheritance")
                link(DslStandardEnclosuresPage::class, "Enclosures")
                link(HtmlNoneDslNoneHtmlPage::class, "Mixed markup in hierarchy")
            }
        }

        h3 { text("DSL, Convenience Base Classes") }
        wicketLink {
            ul {
                link(DslConvenienceHelloWorldPage::class, "Hello World!") {
                    text(" (the DSL version of the ")
                    a(attr("href", "https://wicket.apache.org/learn/examples/helloworld.html")) {
                        text("Wicket Hello World! example")
                    }
                    text(")")
                }
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
                li {
                    a(ELPS::pageParametersWithValPropertiesPageLink) { text("Page parameters, val properties for components") }
                }
                li {
                    a(ELPS::pageParametersWithLateinitVarPropertiesPageLink) { text("Page parameters, lateinit var properties for components") }
                    text(" (also to be used to prevent expensive initialization if a page should not be shown)")
                }
                link(DslConvenienceUnsafeTextPage::class, "Unsafe text") { text(" (useful for large pieces of rich text content)")}
            }
        }
    }
}
// @formatter:on
        }
    }
}

private fun MarkupBuilder<ELPS>.link(
    examplePageClass: KClass<out WebPage>,
    text: String,
    block: MarkupBuilder<ELPS>.() -> Unit = {},
) {
    li {
        a(attr("href", examplePageClass.linkPath())) { text(text) }
        block()
    }
}

private typealias ELPS = ExamplesListPage
