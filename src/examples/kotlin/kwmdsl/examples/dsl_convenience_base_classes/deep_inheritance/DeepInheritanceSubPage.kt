package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.attrLang
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label
import java.util.Locale.ENGLISH

class DeepInheritanceSubPage : DeepInheritanceBasePage() {
    private val subPageLabel: Label = Label(::subPageLabel.name, "Deep inheritance, sub page component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:on
docTypeHtml()
html(attrLang(ENGLISH)) {
    head {
        title { text("Deep Inheritance - Sub Page") }
    }
    body {
        wicketExtend {
            p {
                span(DISPS::subPageLabel)
            }
        }
    }
}
// @formatter:off
        }
    }
}

private typealias DISPS = DeepInheritanceSubPage
