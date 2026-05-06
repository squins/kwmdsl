package kwmdsl.examples.standard.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.panel.Fragment

class NoComponentsNoMarkupFragment(id: String, markupId: String, markupProvider: MarkupContainer) :
    Fragment(id, markupId, markupProvider) {
    override fun onInitialize() {
        super.onInitialize()

        add(AttributeAppender.append("class", "specialized"))
    }
}
