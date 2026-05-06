package kwmdsl.examples.html.border

import org.apache.wicket.markup.html.basic.Label

class SubBorder(id: String) : BaseBorder(id) {
    override fun onInitialize() {
        super.onInitialize()

        addToBorder(Label("subLabel", "sub"))
    }
}
