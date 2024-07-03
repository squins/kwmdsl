package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class OwnMarkupHtmlFragment(id: String, markupId: String) : Fragment(id, markupId, null) {
    constructor(id: String) : this(id, VARIANT_1_ID)

    override fun onInitialize() {
        super.onInitialize()

        add(Label("fragmentLabel", "own markup, standard"))
    }

    override fun chooseMarkup(provider: MarkupContainer?) = associatedMarkup

    companion object {
        const val VARIANT_1_ID = "ownMarkupStandardFragmentVariant1"
        const val VARIANT_2_ID = "ownMarkupStandardFragmentVariant2"
    }
}
