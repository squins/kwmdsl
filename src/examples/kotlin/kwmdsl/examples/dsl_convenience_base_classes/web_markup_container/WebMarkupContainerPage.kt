package kwmdsl.examples.dsl_convenience_base_classes.web_markup_container

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.dsl_convenience_base_classes.firstSourceCodeLink
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
    classH1("title") { text("Web Markup Container") }

    classDiv("content") {
        p(S::container) {
            text("First: ")
            span(S::firstName)
            text(", last: ")
            span(S::lastName)
            text(".")
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = WebMarkupContainerPage
