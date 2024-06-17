package kwmdsl.examples.dsl_convenience_base_classes

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.div
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.span
import kwmdsl.examples.dsl_standard_base_classes.P6_10_MyBorder
import org.apache.wicket.markup.html.basic.Label

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_surrounding_existing_markup_with_border
@Suppress("ClassName")
class P6_10_MyBorderPanel(id: String) : KotlinWicketMarkupPanel(id) {
    private val myBorder by Wicket {
        P6_10_MyBorder(it).apply {
            addToBorder(Label("childMarkup", "Child inside markup."))
        }
    }

    private val childTag by Wicket { Label(it, "Child inside tag.") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketPanel {
                div(P6_10_MyBorderPanel::myBorder) {
                    span(P6_10_MyBorderPanel::childTag)
                }
            }
        }
    }
}
