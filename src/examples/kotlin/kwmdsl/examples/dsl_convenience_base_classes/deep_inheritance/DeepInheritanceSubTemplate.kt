package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrLang
import com.squins.kwmdsl.body
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.section
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import com.squins.kwmdsl.ul
import kwmdsl.examples.firstSourceCodeLink
import kwmdsl.examples.sourceCodeLink
import org.apache.wicket.markup.html.basic.Label
import java.util.Locale.ENGLISH

open class DeepInheritanceSubTemplate : DeepInheritanceBaseTemplate() {
    private val subTemplateLabel: Label = Label(::subTemplateLabel.name, "Deep inheritance, sub template component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
docTypeHtml()
html(attrLang(ENGLISH)) {
    head {
        title { text("Deep Inheritance - Sub Template") }
    }
    body {
        section(attrClass("section")) {
            div(attrClass("container")) {
                h1(attrClass("title")) { text("Deep Inheritance (DSL, Convenience Base Classes)") }
                div(attrClass("content")) {
                    p {
                        text("Shows a deep hierarchy of pages. The 2 template base classes provide common markup. And the pages provide specific content:")
                    }
                    ul {
                        li {
                            div {
                                code { text("DeepInheritanceBaseTemplate") }
                                text(": provides very simple common markup, which is not used as ")
                                code { text("DeepInheritanceSubTemplate") }
                                text(" overrides it with its own markup.")
                            }
                            ul {
                                li {
                                    div {
                                        code { text("DeepInheritanceSubTemplate") }
                                        text(": provides a proper HTML page as the common markup for subpages.")
                                    }
                                    ul {
                                        li {
                                            div {
                                                code { text("DeepInheritanceBasePage") }
                                                text(": adds a label, and allows for child pages to provide markup.")
                                                ul {
                                                    li {
                                                        div {
                                                            code { text("DeepInheritanceSubPage") }
                                                            text(": adds a label.")
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
                    p {
                        text("Here are the components of each of the classes above:")
                    }
                    p {
                        span(DISTS::subTemplateLabel)
                    }
                    wicketChild()
                    p {
                        span(DeepInheritanceSubTemplate::baseTemplateLabel.name)
                    }
                }
                firstSourceCodeLink(DeepInheritanceBasePage::class)
                sourceCodeLink(DeepInheritanceBaseTemplate::class)
                sourceCodeLink(DeepInheritanceSubPage::class)
                sourceCodeLink(this@Companion)
            }
        }
    }
}
// @formatter:on
        }
    }
}

private typealias DISTS = DeepInheritanceSubTemplate
