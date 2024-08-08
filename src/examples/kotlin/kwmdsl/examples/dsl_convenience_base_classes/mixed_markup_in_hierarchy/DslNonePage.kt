package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class DslNonePage(pageParameters: PageParameters) : DslPage(pageParameters) {
    override fun newDslLabel(id: String) = Label(id, "DslNonePage")
}
