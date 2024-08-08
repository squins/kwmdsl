package kwmdsl.examples.dsl_convenience_base_classes.enclosure

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : ExamplesConvenienceBasePage() {
    private val autoSpan: Label = Label(::autoSpan.name, "Auto")
    private val directSpan: Label = Label(::directSpan.name, "Direct")
    private val twoSpans: WebMarkupContainer = WebMarkupContainer(::twoSpans.name)
    private val firstSpan: Label = Label(::firstSpan.name, "First")
    private val secondSpan: Label = Label(::secondSpan.name, "Second")
    private val autoSpanAttribute: Label = Label(::autoSpanAttribute.name, "Auto")
    private val directSpanAttribute: Label = Label(::directSpanAttribute.name, "Direct")
    private val twoSpansAttribute: WebMarkupContainer = WebMarkupContainer(::twoSpansAttribute.name)
    // TODO("Unique names are now required as the whole hierarchy is flattened. Is that a problem?")
    private val firstSpanAttribute: Label = Label(::firstSpanAttribute.name, "First")
    private val secondSpanAttribute: Label = Label(::secondSpanAttribute.name, "Second")

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

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Enclosures") }

                classDiv("content") {
                    p { text("Refresh to update the visibility of child components.") }

                    h2 { text("Element") }

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
