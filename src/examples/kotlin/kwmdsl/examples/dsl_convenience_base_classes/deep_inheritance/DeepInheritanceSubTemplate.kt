package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrLang
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.section
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
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
                h1(attrClass("title")) { text("Deep Inheritance") }
                div(attrClass("content")) {
                    p {
                        span(DISTS::subTemplateLabel)
                    }
                    wicketChild()
                    p {
                        span(DeepInheritanceSubTemplate::baseTemplateLabel.name)
                    }
                }
            }
        }
        firstSourceCodeLink(DeepInheritanceBaseTemplate::class)
        sourceCodeLink(this@Companion)
        sourceCodeLink(DeepInheritanceBasePage::class)
        sourceCodeLink(DeepInheritanceSubPage::class)
    }
}
// @formatter:on
        }
    }
}

private typealias DISTS = DeepInheritanceSubTemplate
