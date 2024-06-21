package kwmdsl.examples.dsl_convenience_base_classes.link

import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.img
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.title
import kwmdsl.examples.ExamplesListPage

class LinkPage : KotlinWicketMarkupWebPage() {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Link") }
                }
                body {
                    h1 { text("Link") }

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
