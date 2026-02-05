package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.basic.Label

class BorderPage : ExamplesConvenienceBasePage() {
    private val border: SubBorder = SubBorder(::border.name)
    private val pageLabel: Label = Label(::pageLabel.name, "page")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Border") }
    div(attrClass("content")) {
        div(BPS::border) {
            p {
                text("Page: ")
                span(BPS::pageLabel)
            }
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

private typealias BPS = BorderPage
