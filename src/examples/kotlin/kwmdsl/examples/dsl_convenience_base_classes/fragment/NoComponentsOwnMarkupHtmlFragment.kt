package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.Markup
import org.apache.wicket.markup.html.panel.Fragment

class NoComponentsOwnMarkupHtmlFragment(id: String, markupId: String) : Fragment(id, markupId, null) {
    override fun chooseMarkup(provider: MarkupContainer?): Markup = associatedMarkup

    companion object {
        const val BODY_1_ID = "noComponentsOwnMarkupBody1"
        const val BODY_2_ID = "noComponentsOwnMarkupBody2"
    }
}
