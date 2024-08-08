package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import org.apache.wicket.MarkupContainer

class NoComponentsMarkupSupplyingDslPanel(id: String) : KotlinWicketMarkupPanel(id) {
    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        val noComponentsMarkupSupplyingForUnspecializedBody1 = fragmentBodyMarkup<NoComponentsMarkupSupplyingDslPanel> {
            text("No components, unspecialized Fragment, fragment #1")
        }

        val noComponentsMarkupSupplyingForUnspecializedBody2 = fragmentBodyMarkup<NoComponentsMarkupSupplyingDslPanel> {
            text("No components, unspecialized Fragment, fragment #2")
        }

        val noComponentsMarkupSupplyingForSpecializedBody1 = fragmentBodyMarkup<NoComponentsMarkupSupplyingDslPanel> {
            text("No components, specialized Fragment, fragment #1")
        }

        val noComponentsMarkupSupplyingForSpecializedBody2 = fragmentBodyMarkup<NoComponentsMarkupSupplyingDslPanel> {
            text("No components, specialized Fragment, fragment #2")
        }

        override val noVariantMarkup = markup {
            wicketPanel {
                p { text("This is a panel, and it supplied the following fragment:") }
                wicketFragment(::noComponentsMarkupSupplyingForUnspecializedBody1)
                wicketFragment(::noComponentsMarkupSupplyingForUnspecializedBody2)
                wicketFragment(::noComponentsMarkupSupplyingForSpecializedBody1)
                wicketFragment(::noComponentsMarkupSupplyingForSpecializedBody2)
            }
        }
    }
}
