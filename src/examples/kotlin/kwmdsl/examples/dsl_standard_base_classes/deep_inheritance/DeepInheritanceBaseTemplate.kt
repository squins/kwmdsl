package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : WebPage(), IMarkupResourceStreamProvider {
    protected val baseTemplateLabel by Wicket { Label(it, "Deep inheritance, base template component") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Deep Inheritance - Base Template") }
                }
                body {
                    h1 { text("Deep Inheritance") }
                    p {
                        span(DeepInheritanceBaseTemplate::baseTemplateLabel)
                    }
                    wicketChild()
                }
            }
        }
    }
}
