package kwmdsl.examples.dsl_convenience_base_classes.link

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.component.resourcePath
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.ExamplesListPage
import kwmdsl.examples.firstSourceCodeLink

class LinkPage : ExamplesConvenienceBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<S> {
// @formatter:off
wicketExtend {
    classH1("title") { text("Link") }

    classDiv("content") {
        p {
            wicketLink {
                text("In this package: ")
                img(
                    attr("src", "Apache Wicket.svg"),
                    attr("width", "50")
                )
                text(", from (a sub package of) an ancestor package: ")
                img(
                    attr("src", S::class.resourcePath<ExamplesListPage>("Apache Wicket.svg")),
                    attr("width", "50")
                )
            }
        }

        p {
            wicketLink {
                a(attr("href", ExamplesListPage::class.linkPath())) {
                    text("Back to the list of examples.")
                }
            }
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = LinkPage
