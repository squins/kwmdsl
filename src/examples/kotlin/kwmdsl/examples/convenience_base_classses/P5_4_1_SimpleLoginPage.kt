package kwmdsl.examples.convenience_base_classses

import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_markup_inheritance_with_the_wicketextend_tag
@Suppress("ClassName")
class P5_4_1_SimpleLoginPage : KotlinWicketMarkupWebPage<P5_4_1_SimpleLoginPage>() {
    override fun getKotlinWicketMarkup() = myMarkup

    companion object {
        private val myMarkup = markup<P5_4_1_SimpleLoginPage> {
            html {
                head()
                body {
                    wicketExtend {
                        div("style" to "margin: auto; width: 40%;") {
                            // ...
                        }
                    }
                }
            }
        }
    }
}
