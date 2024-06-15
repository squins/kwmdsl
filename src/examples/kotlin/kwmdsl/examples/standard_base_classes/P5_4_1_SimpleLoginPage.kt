package kwmdsl.examples.standard_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_markup_inheritance_with_the_wicketextend_tag
@Suppress("ClassName")
class P5_4_1_SimpleLoginPage : WebPage(), IMarkupResourceStreamProvider {
    override fun onInitialize() {
        super.onInitialize()

        myMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) = myMarkup.stream

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
