package kwmdsl.examples.dsl_standard_base_classes.enclosure

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findDslMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : WebPage(), IMarkupResourceStreamProvider {
    private val autoSpan by Wicket { Label(it, "Auto") }
    private val directSpan by Wicket { Label(it, "Direct") }
    private val twoSpans by Wicket { WebMarkupContainer(it) }
    private val firstSpan by Wicket { Label(it, "First") }
    private val secondSpan by Wicket { Label(it, "Second") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    override fun onConfigure() {
        super.onConfigure()

        autoSpan.isVisible = Random.nextBoolean()
        directSpan.isVisible = Random.nextBoolean()
        secondSpan.isVisible = Random.nextBoolean()
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findDslMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup<EnclosuresPage> {
            docTypeHtml()
            html("lang" to "en") {
                head {
                    title { text("Enclosures") }
                }
                body {
                    h1 { text("Enclosures") }

                    p { text("Refresh to update the visibility of child components.") }

                    wicketEnclosure {
                        p {
                            text("Auto: ")
                            span(EnclosuresPage::autoSpan)
                        }
                    }

                    wicketEnclosure(EnclosuresPage::directSpan) {
                        p {
                            text("Direct: ")
                            span(EnclosuresPage::directSpan)
                        }
                    }

                    wicketEnclosure(EnclosuresPage::secondSpan) {
                        div(EnclosuresPage::twoSpans) {
                            text("First: ")
                            span(EnclosuresPage::firstSpan)
                            text(", second: ")
                            span(EnclosuresPage::secondSpan)
                        }
                    }
                }
            }
        }
    }
}
