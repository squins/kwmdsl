package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

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

open class DeepInheritanceBasePage : DeepInheritanceSubTemplate() {
    private val basePageLabel: Label = Label(::basePageLabel.name, "Deep inheritance, base page component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            docTypeHtml()
            html(attrLang(ENGLISH)) {
                head {
                    title { text("Deep Inheritance - Base Page") }
                }
                body {
                    wicketExtend {
                        p {
                            span(DeepInheritanceBasePage::basePageLabel)
                        }
                        wicketChild()
                    }
                }
            }
        }
    }
}
