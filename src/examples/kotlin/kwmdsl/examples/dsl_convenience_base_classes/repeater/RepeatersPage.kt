package kwmdsl.examples.dsl_convenience_base_classes.repeater

import com.squins.kwmdsl.InputType.CHECKBOX
import com.squins.kwmdsl.Repeated
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrType
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.form
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.input
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.ul
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.repeater.RepeatingView
import org.apache.wicket.model.IModel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.util.io.IClusterable

class RepeatersPage : ExamplesConvenienceBasePage() {
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
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("Repeaters (Convenience Base Classes)") }
        p {
            text("Shows repeaters without and with markup for the items.")
        }
        h2 { code { text("RepeatingView") } }
        ul {
            li(S::firstThreeNumbers)
        }
        h2 { code { text("ListView") } }
        form {
            div(S::group) {
                ul {
                    li(S::persons) {
                        input(Repeated(S::checkbox), attrType(CHECKBOX))
                        text(" ")
                        span(Repeated(S::name))
                        text(" ")
                        span(Repeated(S::lastName))
                    }
                }
            }
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = RepeatersPage

data class Person(
    val name: String,
    val lastName: String,
) : IClusterable
