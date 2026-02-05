package kwmdsl.examples.dsl_convenience_base_classes.enclosure

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
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
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Enclosures") }

    div(attrClass("content")) {
        p { text("Refresh to update the visibility of child components.") }

        h2 { text("Element") }

        wicketEnclosure {
            p {
                text("Auto: ")
                span(S::autoSpan)
            }
        }

        wicketEnclosure(S::directSpan) {
            p {
                text("Direct: ")
                span(S::directSpan)
            }
        }

        wicketEnclosure(S::secondSpan) {
            div(S::twoSpans) {
                text("First: ")
                span(S::firstSpan)
                text(", second: ")
                span(S::secondSpan)
            }
        }

        h2 { text("Attribute") }

        p(attrWicketEnclosure()) {
            text("Auto: ")
            span(S::autoSpanAttribute)
        }

        p(attrWicketEnclosure(S::directSpanAttribute)) {
            text("Direct: ")
            span(S::directSpanAttribute)
        }

        div(attrWicketEnclosure(S::secondSpanAttribute)) {
            div(S::twoSpansAttribute) {
                text("First: ")
                span(S::firstSpanAttribute)
                text(", second: ")
                span(S::secondSpanAttribute)
            }
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = EnclosuresPage
