package kwmdsl.examples.standard.simple_inheritance

import kwmdsl.examples.ExamplesStandardBasePage
import org.apache.wicket.markup.html.basic.Label

open class SimpleInheritanceTemplate : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("templateLabel", "Simple inheritance, template component"))
    }
}
