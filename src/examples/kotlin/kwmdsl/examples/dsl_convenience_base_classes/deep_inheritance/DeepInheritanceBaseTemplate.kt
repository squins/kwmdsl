package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : ExamplesConvenienceBasePage() {
    protected val baseTemplateLabel: Label = Label(::baseTemplateLabel.name, "Deep inheritance, base template component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    classH1("title") { text("Deep Inheritance") }
    classDiv("content") {
        p {
            span(DIBTS::baseTemplateLabel)
        }
        wicketChild()
    }
}
// @formatter:on
        }
    }
}

private typealias DIBTS = DeepInheritanceBaseTemplate
