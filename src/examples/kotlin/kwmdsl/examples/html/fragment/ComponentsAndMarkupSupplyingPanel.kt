package kwmdsl.examples.html.fragment

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Panel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ComponentsAndMarkupSupplyingPanel(id: String) : Panel(id) {
    val currentTime = Label("currentTime") {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    companion object {
        const val COMPONENTS_AND_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_1 = "componentsAndMarkupSupplyingForUnspecializedBody1"
        const val COMPONENTS_AND_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_2 = "componentsAndMarkupSupplyingForUnspecializedBody2"
        const val COMPONENTS_AND_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_1 = "componentsAndMarkupSupplyingForSpecializedBody1"
        const val COMPONENTS_AND_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_2 = "componentsAndMarkupSupplyingForSpecializedBody2"
    }
}
