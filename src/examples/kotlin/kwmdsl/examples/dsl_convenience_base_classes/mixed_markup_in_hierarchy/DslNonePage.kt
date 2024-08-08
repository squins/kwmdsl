package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import org.apache.wicket.markup.html.basic.Label

abstract class DslNonePage : DslPage() {
    override fun newDslLabel(id: String) = Label(id, "DslNonePage")
}
