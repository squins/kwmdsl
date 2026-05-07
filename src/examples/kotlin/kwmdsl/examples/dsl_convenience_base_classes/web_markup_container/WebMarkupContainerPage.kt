package kwmdsl.examples.dsl_convenience_base_classes.web_markup_container

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label

class WebMarkupContainerPage : ExamplesConvenienceBasePage() {
    private val container: WebMarkupContainer = WebMarkupContainer(::container.name)
    private val firstName: Label = Label(::firstName.name, "John")
    private val lastName: Label = Label(::lastName.name, "Doe")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Web Markup Container (Convenience Base Classes)") }

    div(attrClass("content")) {
        p {
            text("Shows that a basic Wicket component, ")
            code { text("WebMarkupContainer") }
            text(", works.")
        }
        p(S::container) {
            text("First: ")
            span(S::firstName)
            text(", last: ")
            span(S::lastName)
            text(".")
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = WebMarkupContainerPage
