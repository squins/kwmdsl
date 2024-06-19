package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label

class BorderPage : KotlinWicketMarkupWebPage() {
    private val border by Wicket { SubBorder(it) }
    private val pageLabel by Wicket { Label(it, "page") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html("lang" to "en") {
                head {
                    title { text("Border") }
                }
                body {
                    h1 { text("Border") }
                    div(BorderPage::border) {
                        p {
                            text("Page: ")
                            span(BorderPage::pageLabel)
                        }
                    }
                }
            }
        }
    }
}
