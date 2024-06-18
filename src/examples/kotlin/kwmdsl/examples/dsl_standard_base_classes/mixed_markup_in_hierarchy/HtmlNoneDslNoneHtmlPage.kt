package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

class HtmlNoneDslNoneHtmlPage : HtmlNoneDslNonePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("htmlNoneDslNoneHtmlLabel", "HtmlNoneDslNoneHtmlPage"))
    }
}
