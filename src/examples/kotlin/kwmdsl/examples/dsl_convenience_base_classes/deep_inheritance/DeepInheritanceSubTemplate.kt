package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.dsl_convenience_base_classes.firstSourceCodeLink
import kwmdsl.examples.dsl_convenience_base_classes.sourceCodeLink
import org.apache.wicket.markup.html.basic.Label

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
html(attr("lang", "en")) {
    head {
        title { text("Deep Inheritance - Sub Template") }
    }
    body {
        classSection("section") {
            classDiv("container") {
                classH1("title") { text("Deep Inheritance") }
                classDiv("content") {
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
