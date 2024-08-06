package kwmdsl.examples.dsl_standard_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label

abstract class HtmlNoneDslPage : HtmlNonePage(), IMarkupResourceStreamProvider {
    private val htmlNoneDslLabel: Label = newHtmlNoneDslLabel(::htmlNoneDslLabel.name)

    protected open fun newHtmlNoneDslLabel(id: String) = Label(id, "HtmlNoneDslPage")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                p {
                    text("This is from ")
                    code { text("HtmlNoneDslPage") }
                    text(".")
                }
                p {
                    text("This is from ")
                    code { span(HtmlNoneDslPage::htmlNoneDslLabel) }
                    text(".")
                }
                wicketChild()
            }
        }
    }
}
