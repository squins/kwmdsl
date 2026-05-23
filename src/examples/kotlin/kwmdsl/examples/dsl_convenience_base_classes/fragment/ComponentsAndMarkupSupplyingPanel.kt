package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ComponentsAndMarkupSupplyingPanel(id: String) : KotlinWicketMarkupPanel(id) {
    val currentTime: Label = Label(::currentTime.name) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    companion object : IKotlinWicketMarkupProvider {
        val componentsAndMarkupSupplyingForUnspecializedBody1 = fragmentBodyMarkup {
            text("Components in external component, unspecialized fragment, fragment markup #1. At: ")
            span(CAMSPS::currentTime)
        }

        val componentsAndMarkupSupplyingForUnspecializedBody2 = fragmentBodyMarkup(componentsAndMarkupSupplyingForUnspecializedBody1) {
            text("Components in external component, unspecialized fragment, fragment markup #2. At: ")
            span(CAMSPS::currentTime)
        }

        val componentsAndMarkupSupplyingForSpecializedBody1 = fragmentBodyMarkup {
            text("Components in external component, specialized fragment, fragment markup #1. At: ")
            span(CAMSPS::currentTime)
        }

        val componentsAndMarkupSupplyingForSpecializedBody2 = fragmentBodyMarkup(componentsAndMarkupSupplyingForSpecializedBody1) {
            text("Components in external component, specialized fragment, fragment markup #2. At: ")
            span(CAMSPS::currentTime)
        }

        override val noVariantMarkup = markup<CAMSPS> {
// @formatter:off
wicketPanel {
    p { text("This is a panel, and it supplied the following fragment markup, including the components:") }
    wicketFragment(::componentsAndMarkupSupplyingForUnspecializedBody1)
    wicketFragment(::componentsAndMarkupSupplyingForUnspecializedBody2)
    wicketFragment(::componentsAndMarkupSupplyingForSpecializedBody1)
    wicketFragment(::componentsAndMarkupSupplyingForSpecializedBody2)
}
// @formatter:on
        }
    }
}

private typealias CAMSPS = ComponentsAndMarkupSupplyingPanel
