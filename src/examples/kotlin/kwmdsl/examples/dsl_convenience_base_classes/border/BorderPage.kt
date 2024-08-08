package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
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
