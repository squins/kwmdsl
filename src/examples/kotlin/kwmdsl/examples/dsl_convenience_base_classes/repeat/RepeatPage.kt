package kwmdsl.examples.dsl_convenience_base_classes.repeat

import com.squins.kwmdsl.Repeated
import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.form
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.input
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.span
import com.squins.kwmdsl.ul
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
    private val firstThreeNumbers2 by Wicket { RepeatingView(it) }

    private val group: CheckGroup<Person> = CheckGroup(::group.name, mutableListOf<Person>())
    private val group2 by Wicket { CheckGroup(::group.name, mutableListOf<Person>()) }

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
                item.add(checkbox(item.getModel()))
                item.add(name(PropertyModel<String>(item.getDefaultModel(), Person::name.name)))
                item.add(lastName(PropertyModel<String>(item.getDefaultModel(), Person::lastName.name)))
            }
        }
    private val persons2 by Wicket {
        object : ListView<Person>(
            it, listOf(
                Person("Fritz", "Fritzel"),
                Person("Ghan", "Phariounimn"),
                Person("Jan", "Klaasen"),
                Person("Hank", "Plaindweller"),
            )
        ) {
            override fun populateItem(item: ListItem<Person>) {
                item.add(checkbox(item.getModel()))
                item.add(name(PropertyModel<String>(item.getDefaultModel(), Person::name.name)))
                item.add(lastName(PropertyModel<String>(item.getDefaultModel(), Person::lastName.name)))
            }
        }
    }


    override fun onInitialize() {
        super.onInitialize()

        listOf("One", "Two", "Three").forEach { number ->
            firstThreeNumbers.add(Label(firstThreeNumbers.newChildId(), number))
        }
        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketExtend {
                h1(attr("class", "title")) { text("Repeat") }
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
