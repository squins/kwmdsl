package kwmdsl.examples.standard.deep_inheritance

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : WebPage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("baseTemplateLabel", "Deep inheritance, base template component"))
    }
}
