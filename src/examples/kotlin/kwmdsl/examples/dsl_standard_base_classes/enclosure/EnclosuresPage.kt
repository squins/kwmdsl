package kwmdsl.examples.dsl_standard_base_classes.enclosure

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesStandardBasePage
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : ExamplesStandardBasePage(), IMarkupResourceStreamProvider {
    private val autoSpan by Wicket { Label(it, "Auto") }
    private val directSpan by Wicket { Label(it, "Direct") }
    private val twoSpans by Wicket { WebMarkupContainer(it) }
    private val firstSpan by Wicket { Label(it, "First") }
    private val secondSpan by Wicket { Label(it, "Second") }
    private val autoSpanAttribute by Wicket { Label(it, "Auto") }
    private val directSpanAttribute by Wicket { Label(it, "Direct") }
    private val twoSpansAttribute by Wicket { WebMarkupContainer(it) }
    // TODO("Unique names are now required as the whole hierarchy is flattened. Is that a problem?")
    private val firstSpanAttribute by Wicket { Label(it, "First") }
    private val secondSpanAttribute by Wicket { Label(it, "Second") }

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    override fun onConfigure() {
        super.onConfigure()

        autoSpan.isVisible = Random.nextBoolean()
        directSpan.isVisible = Random.nextBoolean()
        secondSpan.isVisible = Random.nextBoolean()
        autoSpanAttribute.isVisible = Random.nextBoolean()
        directSpanAttribute.isVisible = Random.nextBoolean()
        secondSpanAttribute.isVisible = Random.nextBoolean()
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<EnclosuresPage> {
            wicketExtend {
                classH1("title") { text("Enclosures") }

                classDiv("content") {
                    p { text("Refresh to update the visibility of child components.") }

                    h2 { text("Tag") }

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

                    h2 { text("Attribute") }

                    p(wicketEnclosureAttribute()) {
                        text("Auto: ")
                        span(EnclosuresPage::autoSpanAttribute)
                    }

                    p(wicketEnclosureAttribute(EnclosuresPage::directSpanAttribute)) {
                        text("Direct: ")
                        span(EnclosuresPage::directSpanAttribute)
                    }

                    div(wicketEnclosureAttribute(EnclosuresPage::secondSpanAttribute)) {
                        div(EnclosuresPage::twoSpansAttribute) {
                            text("First: ")
                            span(EnclosuresPage::firstSpanAttribute)
                            text(", second: ")
                            span(EnclosuresPage::secondSpanAttribute)
                        }
                    }
                }
            }
        }
    }
}
