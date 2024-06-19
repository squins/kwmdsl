package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.borderMarkup
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupBorder
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label

open class BaseBorder(id: String) : KotlinWicketMarkupBorder(id) {
    private val baseLabel by Wicket { Label(it, "base") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addToBorder(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = borderMarkup {
            wicketBorder {
                p {
                    text("Base border: ")
                    span(BaseBorder::baseLabel)
                }
                wicketChild()
                wicketBody()
            }
        }
    }
}
