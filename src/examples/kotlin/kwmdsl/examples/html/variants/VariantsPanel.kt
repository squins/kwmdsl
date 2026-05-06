package kwmdsl.examples.html.variants

import org.apache.wicket.markup.html.panel.Panel

class VariantsPanel(id: String) : Panel(id) {
    override fun onInitialize() {
        super.onInitialize()

        outputMarkupId = true
    }
}
