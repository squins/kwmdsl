package kwmdsl.examples

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class ExamplesConvenienceBasePage(pageParameters: PageParameters?) : KotlinWicketMarkupWebPage(pageParameters) {
    constructor() : this(null)

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<ExamplesConvenienceBasePage> {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Kotlin Wicket Markup DSL Examples") }
                }
                body {
                    classSection("section") {
                        classDiv("container") {
                            wicketChild()
                        }
                    }
                }
            }
        }
    }
}
