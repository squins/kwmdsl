package kwmdsl.examples.dsl_standard_base_classes

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findDslMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.meta
import com.squins.kwmdsl.title
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_the_homepage_class
@Suppress("ClassName")
class P4_3_HomePage : WebPage(), IMarkupResourceStreamProvider {
    private val helloMessage by Wicket { Label(it, "Hello WicketWorld!") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findDslMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html {
                head {
                    meta("charset" to "utf-8")
                    title { text("Apache Wicket HelloWorld") }
                }
                body {
                    div(P4_3_HomePage::helloMessage) {
                        text("[Label's message goes here]")
                    }
                }
            }
        }
    }
}
