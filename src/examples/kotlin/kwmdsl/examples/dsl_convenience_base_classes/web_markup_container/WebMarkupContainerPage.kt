package kwmdsl.examples.dsl_convenience_base_classes.web_markup_container

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label

class WebMarkupContainerPage : ExamplesConvenienceBasePage() {
    private val container by Wicket { WebMarkupContainer(it) }
    private val firstName by Wicket { Label(it, "John") }
    private val lastName by Wicket { Label(it, "Doe") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketExtend {
                classH1("title") { text("Web Markup Container") }

                classDiv("content") {
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
