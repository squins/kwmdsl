package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceSubTemplate : DeepInheritanceBaseTemplate() {
    private val subTemplateLabel: Label = Label(::subTemplateLabel.name, "Deep inheritance, sub template component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Deep Inheritance - Sub Template") }
                }
                body {
                    classSection("section") {
                        classDiv("container") {
                            classH1("title") { text("Deep Inheritance") }
                            classDiv("content") {
                                p {
                                    span(DeepInheritanceSubTemplate::subTemplateLabel)
                                }
                                wicketChild()
                                p {
                                    span(DeepInheritanceSubTemplate::baseTemplateLabel.name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
