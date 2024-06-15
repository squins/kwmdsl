package kwmdsl.examples.convenience_base_classses

import com.squins.kwmdsl.body
import com.squins.kwmdsl.br
import com.squins.kwmdsl.component.KotlinWicketMarkupBorder
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_surrounding_existing_markup_with_border
@Suppress("ClassName")
class P6_10_MyBorder(id: String) : KotlinWicketMarkupBorder<P6_10_MyBorder>(id) {
    override fun getKotlinWicketMarkup() = myMarkup

    companion object {
        private val myMarkup = markup<P6_10_MyBorder> {
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
