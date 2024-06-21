package kwmdsl.examples.standard.deep_inheritance

import kwmdsl.examples.ExamplesStandardBasePage
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("baseTemplateLabel", "Deep inheritance, base template component"))
    }
}
