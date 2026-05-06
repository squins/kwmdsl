package kwmdsl.examples.html.deep_inheritance

import org.apache.wicket.markup.html.basic.Label

class DeepInheritanceSubPage : DeepInheritanceBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("subPageLabel", "Deep inheritance, sub page component"))
    }
}
