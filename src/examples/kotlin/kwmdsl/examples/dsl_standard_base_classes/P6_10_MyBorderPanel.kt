package kwmdsl.examples.dsl_standard_base_classes

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findDslMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.span
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Panel

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_surrounding_existing_markup_with_border
@Suppress("ClassName")
class P6_10_MyBorderPanel(id: String) : Panel(id), IMarkupResourceStreamProvider {
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

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findDslMarkup(containerClass)

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
