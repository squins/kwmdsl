package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.borderMarkup
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label

class SubBorder(id: String) : BaseBorder(id) {
    private val subLabel by Wicket { Label(it, "sub") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addToBorder(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = borderMarkup {
            wicketExtend {
                p {
                    text("Sub border: ")
                    span(SubBorder::subLabel)
                }
            }
        }
    }
}
