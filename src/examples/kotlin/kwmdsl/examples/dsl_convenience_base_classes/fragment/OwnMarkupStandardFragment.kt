package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

// TODO("Document: the markup ID is passed in, but if there is only 1 fragment in the markup, it can be hardcoded. Just like `OwnMarkupDslFragment` does")
class OwnMarkupStandardFragment(id: String, markupId: String) : Fragment(id, markupId, null) {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("fragmentLabel", "own markup, standard"))
    }

    override fun chooseMarkup(provider: MarkupContainer?) = associatedMarkup

    companion object {
        const val FRAGMENT_ID = "ownMarkupStandardFragment"
    }
}
