package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label

class BorderPage : ExamplesConvenienceBasePage() {
    private val border by Wicket { SubBorder(it) }
    private val pageLabel by Wicket { Label(it, "page") }

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Border") }
                classDiv("content") {
                    div(BorderPage::border) {
                        p {
                            text("Page: ")
                            span(BorderPage::pageLabel)
                        }
                    }
                }
            }
        }
    }
}
