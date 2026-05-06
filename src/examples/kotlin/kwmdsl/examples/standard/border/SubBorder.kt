package kwmdsl.examples.standard.border

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.border.Border

class SubBorder(id: String) : BaseBorder(id) {
    override fun onInitialize() {
        super.onInitialize()

        addToBorder(Label("subLabel", "sub"))
    }
}
