package kwmdsl.examples.dsl_convenience_base_classes.deep_inheritance

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : ExamplesConvenienceBasePage() {
    protected val baseTemplateLabel by Wicket { Label(it, "Deep inheritance, base template component") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketExtend {
                classH1("title") { text("Deep Inheritance") }
                classDiv("content") {
                    p {
                        span(DeepInheritanceBaseTemplate::baseTemplateLabel)
                    }
                    wicketChild()
                }
            }
        }
    }
}
