package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class DslNoneHtmlNonePage(pageParameters: PageParameters) : DslNoneHtmlPage(pageParameters) {
    override fun newDslNoneHtmlLabel(id: String) = Label(id, "DslNoneHtmlNonePage")
}
