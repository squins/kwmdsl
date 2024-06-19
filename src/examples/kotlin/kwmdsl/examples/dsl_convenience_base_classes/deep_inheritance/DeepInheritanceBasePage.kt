package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
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

open class DeepInheritanceBasePage : DeepInheritanceSubTemplate() {
    private val basePageLabel by Wicket { Label(it, "Deep inheritance, base page component") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
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
