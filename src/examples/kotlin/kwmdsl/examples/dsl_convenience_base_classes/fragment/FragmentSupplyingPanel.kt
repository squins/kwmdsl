package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import org.apache.wicket.MarkupContainer

class FragmentSupplyingPanel(id: String) : KotlinWicketMarkupPanel(id) {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        val fragment = markup {
            p { text("This is the fragment.") }
        }

        override val noVariantMarkup = markup<FragmentSupplyingPanel> {
            wicketPanel {
                p { text("This is a panel supplying a fragment.") }

            }
            embedWicketFragment(::fragment)
        }
    }
}
