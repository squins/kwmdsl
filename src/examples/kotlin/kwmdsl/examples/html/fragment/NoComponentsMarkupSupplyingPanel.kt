package kwmdsl.examples.html.fragment

import org.apache.wicket.markup.html.panel.Panel

class NoComponentsMarkupSupplyingPanel(id: String) : Panel(id) {
    companion object {
        const val NO_COMPONENTS_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_1 = "noComponentsMarkupSupplyingForUnspecializedBody1"
        const val NO_COMPONENTS_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_2 = "noComponentsMarkupSupplyingForUnspecializedBody2"
        const val NO_COMPONENTS_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_1 = "noComponentsMarkupSupplyingForSpecializedBody1"
        const val NO_COMPONENTS_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_2 = "noComponentsMarkupSupplyingForSpecializedBody2"
    }
}
