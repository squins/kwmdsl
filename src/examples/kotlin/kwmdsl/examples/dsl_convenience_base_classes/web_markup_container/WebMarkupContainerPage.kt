package kwmdsl.examples.dsl_convenience_base_classes.web_markup_container

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label

class WebMarkupContainerPage : KotlinWicketMarkupWebPage() {
    private val container by Wicket { WebMarkupContainer(it) }
    private val firstName by Wicket { Label(it, "John") }
    private val lastName by Wicket { Label(it, "Doe") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Web Markup Container") }
                }
                body {
                    h1 { text("Web Markup Container") }
                    p(WebMarkupContainerPage::container) {
                        text("First: ")
                        span(WebMarkupContainerPage::firstName)
                        text(", last: ")
                        span(WebMarkupContainerPage::lastName)
                        text(".")
                    }
                }
            }
        }
    }
}
