package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.basic.Label
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ComponentsAndMarkupSupplyingDslPanel(id: String) : KotlinWicketMarkupPanel(id) {
    val currentTime: Label = Label(::currentTime.name) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        val componentsAndMarkupSupplyingForUnspecializedBody1 = fragmentBodyMarkup {
            text("Components in external component, unspecialized Fragment, fragment #1. At: ")
            span(ComponentsAndMarkupSupplyingDslPanel::currentTime)
        }

        val componentsAndMarkupSupplyingForUnspecializedBody2 = fragmentBodyMarkup {
            text("Components in external component, unspecialized Fragment, fragment #2. At: ")
            span(ComponentsAndMarkupSupplyingDslPanel::currentTime)
        }

        val componentsAndMarkupSupplyingForSpecializedBody1 = fragmentBodyMarkup {
            text("Components in external component, specialized Fragment, fragment #1. At: ")
            span(ComponentsAndMarkupSupplyingDslPanel::currentTime)
        }

        val componentsAndMarkupSupplyingForSpecializedBody2 = fragmentBodyMarkup {
            text("Components in external component, specialized Fragment, fragment #2. At: ")
            span(ComponentsAndMarkupSupplyingDslPanel::currentTime)
        }

        override val noVariantMarkup = markup<ComponentsAndMarkupSupplyingDslPanel> {
            wicketPanel {
                p { text("This is a panel, and it supplied the following fragment, including the components:") }
                wicketFragment(::componentsAndMarkupSupplyingForUnspecializedBody1)
                wicketFragment(::componentsAndMarkupSupplyingForUnspecializedBody2)
                wicketFragment(::componentsAndMarkupSupplyingForSpecializedBody1)
                wicketFragment(::componentsAndMarkupSupplyingForSpecializedBody2)
            }
        }
    }
}
