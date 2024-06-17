package kwmdsl.examples.standard.simple_inheritance

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

open class SimpleInheritanceTemplate : WebPage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("templateLabel", "Simple inheritance, template component"))
    }
}
