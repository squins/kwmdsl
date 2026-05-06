package kwmdsl.examples.dsl_standard_base_classes.enclosure

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.ul
import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : ExamplesStandardBasePage(), IMarkupResourceStreamProvider {
    private val autoSpan: Label = Label(::autoSpan.name, "Auto")
    private val directSpan: Label = Label(::directSpan.name, "Direct")
    private val twoSpans: WebMarkupContainer = WebMarkupContainer(::twoSpans.name)
    private val firstSpan: Label = Label(::firstSpan.name, "First")
    private val secondSpan: Label = Label(::secondSpan.name, "Second")
    private val autoSpanAttribute: Label = Label(::autoSpanAttribute.name, "Auto")
    private val directSpanAttribute: Label = Label(::directSpanAttribute.name, "Direct")
    private val twoSpansAttribute: WebMarkupContainer = WebMarkupContainer(::twoSpansAttribute.name)
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

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Enclosures (DSL, Standard Base Classes)") }

    div(attrClass("content")) {
        p { text("Shows enclosures that:") }
        ul {
            li { text("Detect the single component within them.") }
            li { text("Have the single component within them specified.") }
            li { text("Have the (nested) component of multiple components within them specified.") }
        }
        p { text("For both enclosure elements and attributes.") }
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

        p(attrWicketEnclosure()) {
            text("Auto: ")
            span(EnclosuresPage::autoSpanAttribute)
        }

        p(attrWicketEnclosure(EnclosuresPage::directSpanAttribute)) {
            text("Direct: ")
            span(EnclosuresPage::directSpanAttribute)
        }

        div(attrWicketEnclosure(EnclosuresPage::secondSpanAttribute)) {
            div(EnclosuresPage::twoSpansAttribute) {
                text("First: ")
                span(EnclosuresPage::firstSpanAttribute)
                text(", second: ")
                span(EnclosuresPage::secondSpanAttribute)
            }
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}
