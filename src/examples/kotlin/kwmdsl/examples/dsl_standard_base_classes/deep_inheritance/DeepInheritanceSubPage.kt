package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

import com.squins.kwmdsl.Wicket
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

class DeepInheritanceSubPage : DeepInheritanceBasePage() {
    private val subPageLabel by Wicket { Label(it, "Deep inheritance, sub page component") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html("lang" to "en") {
                head {
                    title { text("Deep Inheritance - Sub Page") }
                }
                body {
                    wicketExtend {
                        p {
                            span(DeepInheritanceSubPage::subPageLabel)
                        }
                    }
                }
            }
        }
    }
}
