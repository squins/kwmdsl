package kwmdsl.examples.dsl_convenience_base_classes.repeat

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.title
import com.squins.kwmdsl.ul
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.repeater.RepeatingView

class RepeatPage : KotlinWicketMarkupWebPage() {
    private val firstThreeNumbers by Wicket { RepeatingView(it) }

    override fun onInitialize() {
        super.onInitialize()

        listOf("One", "Two", "Three").forEach { number ->
            firstThreeNumbers.add(Label(firstThreeNumbers.newChildId(), number))
        }
        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html("lang" to "en") {
                head {
                    title { text("Repeat") }
                }
                body {
                    h1 { text("Repeat") }
                    ul {
                        li(RepeatPage::firstThreeNumbers)
                    }
                }
            }
        }
    }
}
