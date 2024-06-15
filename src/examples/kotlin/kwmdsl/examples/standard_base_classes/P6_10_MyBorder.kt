package kwmdsl.examples.standard_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.br
import com.squins.kwmdsl.div
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.border.Border

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_surrounding_existing_markup_with_border
@Suppress("ClassName")
class P6_10_MyBorder(id: String) : Border(id), IMarkupResourceStreamProvider {
    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) = myMarkup.stream

    companion object {
        private val myMarkup = markup {
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
