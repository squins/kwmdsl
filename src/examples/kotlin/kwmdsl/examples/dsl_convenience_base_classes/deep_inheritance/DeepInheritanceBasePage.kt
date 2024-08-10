package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBasePage : DeepInheritanceSubTemplate() {
    private val basePageLabel: Label = Label(::basePageLabel.name, "Deep inheritance, base page component")

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
        title { text("Deep Inheritance - Base Page") }
    }
    body {
        wicketExtend {
            p {
                span(DIBPS::basePageLabel)
            }
            wicketChild()
        }
    }
}
// @formatter:ofn
        }
    }
}

private typealias DIBPS = DeepInheritanceBasePage
