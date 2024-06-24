package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.classSection
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceSubTemplate : DeepInheritanceBaseTemplate() {
    private val subTemplateLabel by Wicket { Label(it, "Deep inheritance, sub template component") }

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
