package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label

abstract class DslPage : ExamplesConvenienceBasePage() {
    private val dslLabel: Label = newDslLabel(::dslLabel.name)

    protected open fun newDslLabel(id: String) = Label(id, "DslPage")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("DSL, None, HTML, None, DSL (Convenience Base Classes)") }
        p {
            text("This is from ")
            code { text("DslPage") }
            text(".")
        }
        p {
            text("This is from ")
            code { span(DPS::dslLabel) }
            text(".")
        }
        wicketChild()
    }
}
// @formatter:on
        }
    }
}

private typealias DPS = DslPage
