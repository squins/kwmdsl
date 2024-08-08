package kwmdsl.examples.dsl_convenience_base_classes.repeat

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.repeater.RepeatingView
import org.apache.wicket.model.IModel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.util.io.IClusterable

class RepeatPage : ExamplesConvenienceBasePage() {
    private val firstThreeNumbers: RepeatingView = RepeatingView(::firstThreeNumbers.name)

    private val group: CheckGroup<Person> = CheckGroup(::group.name, mutableListOf<Person>())

    private fun checkbox(model: IModel<Person>): Check<Person> = Check(::checkbox.name, model)
    private fun name(model: IModel<String>): Label = Label(::name.name, model)
    private fun lastName(model: IModel<String>): Label = Label(::lastName.name, model)

    private val persons: ListView<Person> =
        object : ListView<Person>(
            ::persons.name, listOf(
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

    override fun onInitialize() {
        super.onInitialize()

        listOf("One", "Two", "Three").forEach { number ->
            firstThreeNumbers.add(Label(firstThreeNumbers.newChildId(), number))
        }
        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Repeat") }
                classDiv("content") {
                    ul {
                        li(RepeatPage::firstThreeNumbers)
                    }
                    form {
                        span(RepeatPage::group) {
                            ul {
                                li(RepeatPage::persons) {
                                    input(Repeated(RepeatPage::checkbox), attr("type", "checkbox"))
                                    text(" ")
                                    span(Repeated(RepeatPage::name))
                                    text(" ")
                                    span(Repeated(RepeatPage::lastName))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Person(
    val name: String,
    val lastName: String,
) : IClusterable
