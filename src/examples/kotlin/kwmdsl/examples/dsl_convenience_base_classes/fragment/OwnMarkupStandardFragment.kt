package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class OwnMarkupStandardFragment(id: String, markupId: String) : Fragment(id, markupId, null) {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("fragmentLabel", "own markup, standard"))
    }

    override fun chooseMarkup(provider: MarkupContainer?) = associatedMarkup
}
