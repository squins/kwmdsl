package kwmdsl.examples.standard.simple_inheritance

import org.apache.wicket.markup.html.basic.Label

class SimpleInheritancePage : SimpleInheritanceTemplate() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("pageLabel", "Simple inheritance, page component"))
    }
}
