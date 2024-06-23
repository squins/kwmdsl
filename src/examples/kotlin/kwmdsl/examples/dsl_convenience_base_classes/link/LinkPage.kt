package kwmdsl.examples.dsl_convenience_base_classes.link

import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.img
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.ExamplesListPage

class LinkPage : ExamplesConvenienceBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
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
                                attr("src", LinkPage::class.linkPath<ExamplesListPage>("Apache Wicket.svg")),
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
            }
        }
    }
}
