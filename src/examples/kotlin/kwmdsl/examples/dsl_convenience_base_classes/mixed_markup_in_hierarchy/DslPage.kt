package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label

abstract class DslPage : ExamplesConvenienceBasePage() {
    private val dslLabel by Wicket { newDslLabel(it) }

    protected open fun newDslLabel(id: String) = Label(id, "DslPage")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("DSL, None, HTML, None, DSL") }
                classDiv("content") {
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
