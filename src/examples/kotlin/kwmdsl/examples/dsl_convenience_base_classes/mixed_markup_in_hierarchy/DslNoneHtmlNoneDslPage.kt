package kwmdsl.examples.dsl_convenience_base_classes.mixed_markup_in_hierarchy

import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.firstSourceCodeLink
import kwmdsl.examples.sourceCodeLink
import kwmdsl.examples.sourceMarkupLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.ResourceLink
import org.apache.wicket.request.resource.PackageResourceReference
import java.nio.charset.StandardCharsets.UTF_8

class DslNoneHtmlNoneDslPage : DslNoneHtmlNonePage() {
    private val dslNoneHtmlNoneDslLabel: Label = Label(::dslNoneHtmlNoneDslLabel.name, "DslNoneHtmlNoneDslPage")
    private val dslNoneHtmlPageMarkupLink: ResourceLink<Unit> =
        ResourceLink<Unit>(
            ::dslNoneHtmlPageMarkupLink.name,
            object : PackageResourceReference(DslNoneHtmlPage::class.java, "DslNoneHtmlPage.html") {
                override fun getResource() =
                    super.getResource()
                        .apply {
                            textEncoding = UTF_8.name()
                        }
            }
        )

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    p {
        text("This is from ")
        code { span(DNHNDPS::dslNoneHtmlNoneDslLabel) }
        text(".")
    }
    firstSourceCodeLink(DslPage::class)
    sourceCodeLink(DslNonePage::class)
    sourceCodeLink(DslNoneHtmlPage::class)
    sourceMarkupLink(DslNoneHtmlPage::class, DNHNDPS::dslNoneHtmlPageMarkupLink)
    sourceCodeLink(DslNoneHtmlNonePage::class)
    sourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

private typealias DNHNDPS = DslNoneHtmlNoneDslPage
