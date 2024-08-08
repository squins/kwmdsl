package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters

class DeepInheritanceSubPage(pageParameters: PageParameters) : DeepInheritanceBasePage(pageParameters) {
    private val subPageLabel: Label = Label(::subPageLabel.name, "Deep inheritance, sub page component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Deep Inheritance - Sub Page") }
                }
                body {
                    wicketExtend {
                        p {
                            span(DeepInheritanceSubPage::subPageLabel)
                        }
                    }
                }
            }
        }
    }
}
