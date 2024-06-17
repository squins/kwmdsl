package kwmdsl.examples.standard.deep_inheritance

import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceSubTemplate : DeepInheritanceBaseTemplate() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("subTemplateLabel", "Deep inheritance, sub template component"))
    }
}
