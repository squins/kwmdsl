package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

abstract class DslNoneHtmlNonePage : DslNoneHtmlPage() {
    override fun newDslNoneHtmlLabel(id: String) = Label(id, "DslNoneHtmlNonePage")
}
