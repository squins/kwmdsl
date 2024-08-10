package kwmdsl.examples.dsl_convenience_base_classes.page_parameters

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.model.LoadableDetachableModel
import org.apache.wicket.request.mapper.parameter.PageParameters

class PageParametersWithValPropertiesPage(pageParameters: PageParameters) : ExamplesConvenienceBasePage(pageParameters) {
    private val modelUsingAPageParameter = object : LoadableDetachableModel<String>() {
        override fun load() = pageParameters.get("text").toString("Hello, world!").reversed()
    }
    private val text: Label = Label(::text.name, modelUsingAPageParameter)

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    classH1("title") { text("Components Defined by Val Properties Accessing Page Parameters") }

    classDiv("content") {
        p {
            text("This page must have a constructor accepting page parameters. If it does not have one, Wicket will set the page parameters after construction, and the property initializers for the component have already executed by then.")
        }

        p {
            text("This is the reverse of the text you sent me: ")
            span(PPWVPPS::text)
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

private typealias PPWVPPS = PageParametersWithValPropertiesPage
