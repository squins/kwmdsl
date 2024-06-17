package kwmdsl.examples.dsl_convenience_base_classes

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.metaTextHtmlUtf8
import org.apache.wicket.markup.html.basic.Label

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_divide_et_impera
@Suppress("ClassName")
class P5_3_2_JugTemplate : KotlinWicketMarkupWebPage() {
    private val headerPanel by Wicket { P5_3_1_HeaderPanel(it) }
//    private val menuPanel by Wicket { P5_3_1_menuPanel(it) }
//    private val footerPanel by Wicket { P5_3_1_footerPanel(it) }

    private val contentComponent by Wicket { Label(it, "Put your content here") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            html {
                head {
                    metaTextHtmlUtf8()
                    // ...
                    // Include CSS
                    // ...
                }
                body {
                    div(P5_3_2_JugTemplate::headerPanel, "id" to "header") { text("header") }
                    div("id" to "body") {
//                        div(P5_3_2_JugTemplate::menuPanel, "id" to "menu") { text("menu") }
                        div(P5_3_2_JugTemplate::contentComponent) { text("content") }
                    }
//                    div(P5_3_2_JugTemplate::footerPanel, "id" to "footer") { text("footer") }
                }
            }
        }
    }
}
