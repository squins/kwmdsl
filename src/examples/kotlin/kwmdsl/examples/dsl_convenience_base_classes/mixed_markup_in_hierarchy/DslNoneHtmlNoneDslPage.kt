package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label

class DslNoneHtmlNoneDslPage : DslNoneHtmlNonePage() {
    private val dslNoneHtmlNoneDslLabel by Wicket { Label(it, "DslNoneHtmlNoneDslPage") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            wicketExtend {
                p {
                    text("This is from ")
                    code { text("DslNoneHtmlNoneDslPage") }
                    text(".")
                }
                p {
                    text("This is from ")
                    code { span(DslNoneHtmlNoneDslPage::dslNoneHtmlNoneDslLabel) }
                    text(".")
                }
            }
        }
    }
}
