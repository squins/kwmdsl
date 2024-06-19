package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.WicketFragment
import com.squins.kwmdsl.WicketOwnMarkupFragment
import com.squins.kwmdsl.WicketSpecializedFragment
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.div
import com.squins.kwmdsl.docTypeHtml
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.ownMarkupFragmentMarkup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.title
import org.apache.wicket.markup.html.basic.Label


class FragmentPage : KotlinWicketMarkupWebPage() {
    private val fragmentLabel by Wicket { Label(it, "unspecialized") }
    private val unspecializedFragmentInstance by WicketFragment(::unspecializedFragment)
    private val specializedFragmentInstance by WicketSpecializedFragment(::specializedFragment, ::SpecializedFragment)
    private val ownMarkupStandardFragmentInstance by WicketOwnMarkupFragment(FragmentPage::ownMarkupStandardFragment, ::OwnMarkupStandardFragment)
    private val ownMarkupDslFragmentInstance by WicketOwnMarkupFragment(OwnMarkupDslFragment::ownMarkupDslFragment, ::OwnMarkupDslFragment)

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val unspecializedFragment = markup {
            p {
                text("Unspecialized fragment: ")
                span(FragmentPage::fragmentLabel)
            }
        }

        val specializedFragment = markup {
            p {
                text("Specialized fragment: ")
                span(SpecializedFragment::fragmentLabel)
            }
        }

        // TODO("Document: placed (instead of in `OwnMarkupStandardFragment`) here to show third-party, unmodifiable fragments with their own markup can be integrated without wrappers")
        val ownMarkupStandardFragment = ownMarkupFragmentMarkup

        override val dslMarkup = markup {
            docTypeHtml()
            html(attr("lang", "en")) {
                head {
                    title { text("Fragment") }
                }
                body {
                    h1 { text("Fragment") }
                    p {
                        text("Unspecialized fragment instance:")
                    }
                    div(FragmentPage::unspecializedFragmentInstance)

                    p {
                        text("Specialized fragment instance:")
                    }
                    div(FragmentPage::specializedFragmentInstance)

                    p {
                        text("Own markup, standard fragment instance:")
                    }
                    div(FragmentPage::ownMarkupStandardFragmentInstance)

                    p {
                        text("Own markup, DSL fragment instance:")
                    }
                    div(FragmentPage::ownMarkupDslFragmentInstance)

                    wicketFragment(::unspecializedFragment)
                    wicketFragment(::specializedFragment)
                }
            }
        }
    }
}
