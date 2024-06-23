package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.WicketFragment
import com.squins.kwmdsl.WicketOwnMarkupFragment
import com.squins.kwmdsl.WicketSpecializedFragment
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.ownMarkupFragmentMarkup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment


class FragmentPage : ExamplesConvenienceBasePage() {
    private val fragmentLabel by Wicket { Label(it, "unspecialized") }

    private val unspecializedFragmentInstance: Fragment =
        Fragment(::unspecializedFragmentInstance.name, ::unspecializedFragment.name, this).apply {
            unspecializedFragment.addTo(this@FragmentPage, this)
        }
    private val unspecializedFragmentInstance2 by WicketFragment(::unspecializedFragment)

    private val specializedFragmentInstance: SpecializedFragment =
        SpecializedFragment(::specializedFragmentInstance.name, ::specializedFragment.name, this)
    private val specializedFragmentInstance2 by WicketSpecializedFragment(::specializedFragment, ::SpecializedFragment)

    private val ownMarkupStandardFragmentInstance: OwnMarkupStandardFragment =
        OwnMarkupStandardFragment(::ownMarkupStandardFragmentInstance.name, OwnMarkupStandardFragment.FRAGMENT_ID)
    private val ownMarkupStandardFragmentInstance2 by WicketOwnMarkupFragment(
        FragmentPage::ownMarkupStandardFragment, ::OwnMarkupStandardFragment
    )

    private val ownMarkupDslFragmentInstance: OwnMarkupDslFragment =
        OwnMarkupDslFragment(::ownMarkupDslFragmentInstance.name)
    private val ownMarkupDslFragmentInstance2 by WicketOwnMarkupFragment(
        OwnMarkupDslFragment::ownMarkupDslFragment, ::OwnMarkupDslFragment
    )

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

        // TODO("Document: placed here (instead of in `OwnMarkupStandardFragment`) to show third-party, unmodifiable fragments with their own markup can be integrated without wrappers")
        // TODO("Only needed when working with the delegates. This can be removed if the delegates are removed")
        val ownMarkupStandardFragment = ownMarkupFragmentMarkup

        override val dslMarkup = markup {
            wicketExtend {
                classH1("title") { text("Fragment") }
                classDiv("content") {
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
