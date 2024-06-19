package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.body
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label

abstract class DslPage : KotlinWicketMarkupWebPage() {
    private val dslLabel by Wicket { newDslLabel(it) }

    protected open fun newDslLabel(id: String) = Label(id, "DslPage")

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html("lang" to "en") {
                head {
                    title { text("Mixed Markup in Hierarchy - DSL, None, HTML, None, DSL") }
                }
                body {
                    h1 { text("DSL, None, HTML, None, DSL") }
                    p {
                        text("This is from ")
                        code { text("DslPage") }
                        text(".")
                    }
                    p {
                        text("This is from ")
                        code { span(DslPage::dslLabel) }
                        text(".")
                    }
                    wicketChild()
                }
            }
        }
    }
}
