package kwmdsl.examples.dsl_standard_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import org.apache.wicket.markup.IMarkupResourceStreamProvider

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_markup_inheritance_with_the_wicketextend_tag
@Suppress("ClassName")
class P5_4_1_SimpleLoginPage : P5_4_1_JugTemplate(), IMarkupResourceStreamProvider {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup<P5_4_1_SimpleLoginPage> {
            html {
                head()
                body {
                    wicketExtend {
                        div("style" to "margin: auto; width: 40%;") {
                            text("Simple Login")
                            // ...
                        }
                    }
                }
            }
        }
    }
}
