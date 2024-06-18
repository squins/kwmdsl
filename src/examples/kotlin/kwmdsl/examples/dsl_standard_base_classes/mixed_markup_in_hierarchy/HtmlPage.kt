package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

abstract class HtmlPage : WebPage() {
    override fun onInitialize() {
        super.onInitialize()

        add(newHtmlLabel("htmlLabel"))
    }

    protected open fun newHtmlLabel(id: String) = Label(id, "HtmlPage")
}
