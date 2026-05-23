package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p

class NoComponentsMarkupSupplyingPanel(id: String) : KotlinWicketMarkupPanel(id) {
    companion object : IKotlinWicketMarkupProvider {
        val noComponentsMarkupSupplyingForUnspecializedBody1 = fragmentBodyMarkup<NCMSPS> {
            text("No components, unspecialized fragment, fragment markup #1")
        }

        val noComponentsMarkupSupplyingForUnspecializedBody2 = fragmentBodyMarkup(noComponentsMarkupSupplyingForUnspecializedBody1) {
            text("No components, unspecialized fragment, fragment markup #2")
        }

        val noComponentsMarkupSupplyingForSpecializedBody1 = fragmentBodyMarkup<NCMSPS> {
            text("No components, specialized fragment, fragment markup #1")
        }

        val noComponentsMarkupSupplyingForSpecializedBody2 = fragmentBodyMarkup(noComponentsMarkupSupplyingForSpecializedBody1) {
            text("No components, specialized fragment, fragment markup #2")
        }

        override val noVariantMarkup = markup<NCMSPS> {
// @formatter:off
wicketPanel {
    p { text("This is a panel, and it supplied the following fragment markup:") }
    wicketFragment(::noComponentsMarkupSupplyingForUnspecializedBody1)
    wicketFragment(::noComponentsMarkupSupplyingForUnspecializedBody2)
    wicketFragment(::noComponentsMarkupSupplyingForSpecializedBody1)
    wicketFragment(::noComponentsMarkupSupplyingForSpecializedBody2)
}
// @formatter:on
        }
    }
}

private typealias NCMSPS = NoComponentsMarkupSupplyingPanel
