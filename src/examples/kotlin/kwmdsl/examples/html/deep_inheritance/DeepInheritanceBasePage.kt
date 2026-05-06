package kwmdsl.examples.html.deep_inheritance

import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBasePage : DeepInheritanceSubTemplate() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("basePageLabel", "Deep inheritance, base page component"))
    }
}
