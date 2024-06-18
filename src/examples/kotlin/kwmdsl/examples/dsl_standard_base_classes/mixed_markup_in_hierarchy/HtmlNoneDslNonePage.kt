package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

abstract class HtmlNoneDslNonePage : HtmlNoneDslPage() {
    override fun newHtmlNoneDslLabel(id: String) = Label(id, "HtmlNoneDslNonePage")
}
