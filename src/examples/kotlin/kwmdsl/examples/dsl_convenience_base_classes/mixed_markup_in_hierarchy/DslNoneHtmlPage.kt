package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

abstract class DslNoneHtmlPage : DslNonePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(newDslNoneHtmlLabel("dslNoneHtmlLabel"))
    }

    protected open fun newDslNoneHtmlLabel(id: String) = Label(id, "DslNoneHtmlPage")
}
