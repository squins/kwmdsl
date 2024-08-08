package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters

abstract class DslPage(pageParameters: PageParameters) : ExamplesConvenienceBasePage(pageParameters) {
    private val dslLabel: Label = newDslLabel(::dslLabel.name)

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
