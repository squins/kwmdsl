package kwmdsl.examples.dsl_convenience_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.br
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupBorder
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_surrounding_existing_markup_with_border
@Suppress("ClassName")
class P6_10_MyBorder(id: String) : KotlinWicketMarkupBorder(id) {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup<P6_10_MyBorder> {
            xmlDeclaration()
            html {
                head()
                body {
                    wicketBorder {
                        div {
                            div("childMarkup")
                            wicketBody()
                            br()
                        }
                    }
                }
            }
        }
    }
}
