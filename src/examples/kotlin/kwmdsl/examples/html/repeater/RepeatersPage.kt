package kwmdsl.examples.html.repeater

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.dsl_convenience_base_classes.repeater.Person
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.repeater.RepeatingView
import org.apache.wicket.model.IModel
import org.apache.wicket.model.PropertyModel

class RepeatersPage : ExamplesStandardBasePage() {
    private fun checkbox(model: IModel<Person>): Check<Person> = Check(::checkbox.name, model)
    private fun name(model: IModel<String>): Label = Label(::name.name, model)
    private fun lastName(model: IModel<String>): Label = Label(::lastName.name, model)

    override fun onInitialize() {
        super.onInitialize()

        add(
            RepeatingView("firstThreeNumbers").apply {
                listOf("One", "Two", "Three").forEach { number ->
                    add(Label(newChildId(), number))
                }
            },
            CheckGroup("group", mutableListOf<Person>()).apply {
                add(
                    object : ListView<Person>(
                        "persons", listOf(
                            Person("Fritz", "Fritzel"),
                            Person("Ghan", "Phariounimn"),
                            Person("Jan", "Klaasen"),
                            Person("Hank", "Plaindweller"),
                        )
                    ) {
                        override fun populateItem(item: ListItem<Person>) {
                            item.add(checkbox(item.model))
                            item.add(name(PropertyModel(item.getDefaultModel(), Person::name.name)))
                            item.add(lastName(PropertyModel(item.getDefaultModel(), Person::lastName.name)))
                        }
                    }

                )
            }
        )

        add(htmlResourceLink("repeatersPageMarkupLink", RepeatersPage::class))
    }

    override fun onConfigure() {
        super.onConfigure()
    }
}
