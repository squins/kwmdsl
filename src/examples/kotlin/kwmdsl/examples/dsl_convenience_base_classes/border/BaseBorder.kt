package kwmdsl.examples.dsl_convenience_base_classes.border

import com.squins.kwmdsl.borderMarkup
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupBorder
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label

open class BaseBorder(id: String) : KotlinWicketMarkupBorder(id) {
    private val baseLabel: Label = Label(::baseLabel.name, "base")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addToBorder(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = borderMarkup {
// @formatter:off
wicketBorder {
    p {
        text("Base border: ")
        span(BBS::baseLabel)
    }
    wicketChild()
    wicketBody()
}
// @formatter:on
        }
    }
}

private typealias BBS = BaseBorder
