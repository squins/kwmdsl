package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

abstract class HtmlNonePage : HtmlPage() {
    override fun newHtmlLabel(id: String) = Label(id, "HtmlNonePage")
}
