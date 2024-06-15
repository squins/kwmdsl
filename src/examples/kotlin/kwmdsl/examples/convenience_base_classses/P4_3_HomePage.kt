package kwmdsl.examples.convenience_base_classses

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.meta
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_the_homepage_class
@Suppress("ClassName")
class P4_3_HomePage : KotlinWicketMarkupWebPage<P4_3_HomePage>() {
    private val helloMessage by Wicket { Label(it, "Hello WicketWorld!") }

    override fun getKotlinWicketMarkup() = myMarkup

    companion object {
        private val myMarkup = markup {
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
