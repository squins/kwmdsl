package kwmdsl.examples

import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classSection
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.title

abstract class ExamplesConvenienceBasePage : KotlinWicketMarkupWebPage() {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
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
