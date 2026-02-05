package kwmdsl.examples

import com.squins.kwmdsl.attr
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.section
import com.squins.kwmdsl.title
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class ExamplesConvenienceBasePage(pageParameters: PageParameters?) : KotlinWicketMarkupWebPage<Unit>(pageParameters) {
    constructor() : this(null)

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<ECBPS> {
// @formatter:off
docTypeHtml()
html(attr("lang", "en")) {
    head {
        title { text("Kotlin Wicket Markup DSL Examples") }
    }
    body {
        section(attrClass("section")) {
            div(attrClass("container")) {
                wicketChild()
            }
        }
    }
}
// @formatter:on
        }
    }
}

private typealias ECBPS = ExamplesConvenienceBasePage
