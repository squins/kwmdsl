package kwmdsl.examples.dsl_convenience_base_classes.link

import com.squins.kwmdsl.a
import com.squins.kwmdsl.attrAlt
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrHref
import com.squins.kwmdsl.attrSrc
import com.squins.kwmdsl.attrWidth
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.component.resourcePath
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.img
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.ExamplesListPage
import kwmdsl.examples.firstSourceCodeLink
import kwmdsl.examples.standard.link.LinkPage as StandardLinkPage

class LinkPage : ExamplesConvenienceBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<S> {
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("Link (DSL, Convenience Base Classes)") }

        p {
            text("Shows the use of ")
            code { text("<wicket:link>") }
            text(" for resources and pages. The DSL version has functions to calculate the paths, so renames and moves of types do not break links.")
        }
        p {
            wicketLink {
                text("In this package: ")
                img(
                    attrSrc("Apache Wicket.svg"),
                    attrWidth(50),
                    attrAlt("Wicket logo in blue")
                )
                text(", from an ancestor package: ")
                img(
                    attrSrc(S::class.resourcePath<ExamplesListPage>("Apache Wicket.svg")),
                    attrWidth(50),
                    attrAlt("Wicket logo in the official color (orange)")
                )
                text(", from a subpackage of an ancestor package: ")
                img(
                    attrSrc(S::class.resourcePath<StandardLinkPage>("Apache Wicket.svg")),
                    attrWidth(50),
                    attrAlt("Wicket logo in red")
                )
            }
        }

        p {
            wicketLink {
                a(attrHref(ExamplesListPage::class.linkPath())) {
                    text("Back to the list of examples.")
                }
            }
        }

        p {
            wicketLink {
                a(attrHref(StandardLinkPage::class.linkPath())) {
                    text("View the HTML version.")
                }
            }
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = LinkPage
