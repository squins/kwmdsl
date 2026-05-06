package kwmdsl.examples.html.border

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.border.Border

open class BaseBorder(id: String) : Border(id) {
    override fun onInitialize() {
        super.onInitialize()

        addToBorder(Label("baseLabel", "base"))
    }
}
