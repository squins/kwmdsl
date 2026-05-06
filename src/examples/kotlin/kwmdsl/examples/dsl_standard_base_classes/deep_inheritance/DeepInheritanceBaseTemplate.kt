package kwmdsl.examples.dsl_standard_base_classes.deep_inheritance

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findMarkup
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesStandardBasePage
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceBaseTemplate : ExamplesStandardBasePage(), IMarkupResourceStreamProvider {
    protected val baseTemplateLabel: Label = Label(::baseTemplateLabel.name, "Deep inheritance, base template component")

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findMarkup(container, containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    h1(attrClass("title")) { text("Deep Inheritance") }
    div(attrClass("content")) {
        p {
            span(DeepInheritanceBaseTemplate::baseTemplateLabel)
        }
        wicketChild()
    }
}
// @formatter:on
        }
    }
}
