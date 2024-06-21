package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import kwmdsl.examples.ExamplesStandardBasePage
import org.apache.wicket.markup.html.basic.Label

abstract class HtmlPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(newHtmlLabel("htmlLabel"))
    }

    protected open fun newHtmlLabel(id: String) = Label(id, "HtmlPage")
}
