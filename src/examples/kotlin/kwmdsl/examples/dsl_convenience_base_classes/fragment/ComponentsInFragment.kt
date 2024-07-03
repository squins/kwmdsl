package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class ComponentsInFragment(id: String, markupId: String, markupProvider: MarkupContainer) :
    Fragment(id, markupId, markupProvider) {
    val fragmentLabel by Wicket { Label(it, "component in fragment") }

    override fun onInitialize() {
        super.onInitialize()

        FragmentPage.componentsInFragmentVariant1.addTo(this)
    }
}
