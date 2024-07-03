package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.WicketComponentsInFragment
import com.squins.kwmdsl.WicketFragment
import com.squins.kwmdsl.WicketOwnMarkupFragment
import com.squins.kwmdsl.classDiv
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.externalComponentsFragmentMarkup
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.hr
import com.squins.kwmdsl.li
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.ul
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class FragmentPage : ExamplesConvenienceBasePage() {
    private val fragmentLabel by Wicket { Label(it, "component in parent") }

    private val externalComponentsFragmentInstance: Fragment =
        Fragment(::externalComponentsFragmentInstance.name, ::externalComponentsFragment.name, this).apply {
            externalComponentsFragment.addToFragment(this, this@FragmentPage)
        }
    private val externalComponentsFragmentInstance2 by WicketFragment(::externalComponentsFragmentWithDelegate)

    private val componentsInFragmentInstance: ComponentsInFragment =
        ComponentsInFragment(::componentsInFragmentInstance.name, ::componentsInFragmentVariant2.name, this)
    private val componentsInFragmentInstance2 by WicketComponentsInFragment(::componentsInFragmentVariant2, ::ComponentsInFragment)

    private val ownMarkupHtmlFragmentInstance: OwnMarkupHtmlFragment =
        OwnMarkupHtmlFragment(::ownMarkupHtmlFragmentInstance.name, OwnMarkupHtmlFragment.VARIANT_2_ID)
    private val ownMarkupHtmlFragmentInstance2 by WicketOwnMarkupFragment(
        FragmentPage::ownMarkupStandardFragmentVariant2, ::OwnMarkupHtmlFragment
    )

    private val ownMarkupDslFragmentInstance: OwnMarkupDslFragment =
        OwnMarkupDslFragment(::ownMarkupDslFragmentInstance.name)
//    private val ownMarkupDslFragmentInstance2 by WicketOwnMarkupFragment(
//        OwnMarkupDslFragment::ownMarkupStandardFragmentVariant1, ::OwnMarkupDslFragment
//    )

    private val fragmentSupplyingPanel: FragmentSupplyingPanel =
        FragmentSupplyingPanel(::fragmentSupplyingPanel.name)
    private val panelSuppliedFragmentInstance: Fragment =
        Fragment(::panelSuppliedFragmentInstance.name, FragmentSupplyingPanel::fragment.name, fragmentSupplyingPanel)

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val externalComponentsFragmentWithDelegate = markup {
            p {
                text("External-components fragment: ")
                span(FragmentPage::fragmentLabel)
            }
        }

        val externalComponentsFragment = externalComponentsFragmentMarkup {
            p {
                text("External-components fragment: ")
                span(FragmentPage::fragmentLabel)
            }
        }

        // TODO("No check to make sure the markup hierarchies of variants are the same. This also applies to fragments with their own DSL markup")
        val componentsInFragmentVariant1 = markup {
            p {
                text("Components in fragment, variant 1: ")
                span(ComponentsInFragment::fragmentLabel)
            }
        }

        val componentsInFragmentVariant2 = markup {
            p {
                text("Components in fragment, variant 2: ")
                span(ComponentsInFragment::fragmentLabel)
            }
        }

        // TODO("Document: placed here (instead of in `OwnMarkupStandardFragment`) to show third-party, unmodifiable fragments with their own markup can be integrated without wrappers")
        // TODO("Only needed when working with the delegates. This can be removed if the delegates are removed")
        val ownMarkupStandardFragmentVariant2 = Unit

        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Fragment") }
                classDiv("content") {
                    h2 { text("Introduction") }
                    // TODO("also put this in the documentation")
                    p { text("Supported cases:") }
                    ul {
                        li {
                            div { text("Markup provided by another component than the fragment.") }
                            ul {
                                li { text("External-components fragment: Wicket components provided by another component than the fragment. This can be an unspecialized or specialized fragment.") }
                                li { text("Components in fragment: Wicket components provided by the fragment. This is always a specialized fragment.") }
                            }
                        }
                        li {
                            div { text("Markup provided by a specialized fragment.") }
                            ul {
                                li { text("HTML markup: Wicket components provided by fragment.") }
                                li { text("DSL markup: Wicket components provided by fragment.") }
                            }
                        }
                    }
                    p { text("In all cases multiple fragment markup variants are supported. This is shown in 3 examples below:") }
                    ul {
                        li { text("Markup provided by by another component than the fragment, components-in-fragment instance") }
                        li { text("Own markup, HTML markup fragment instance") }
                        li { text("Own markup, DSL markup fragment instance") }
                    }

                    h2 { text("Examples") }

                    p {
                        text("Markup provided by another component than the fragment, external-components fragment instance:")
                    }
                    div(FragmentPage::externalComponentsFragmentInstance)

                    hr()

                    p {
                        text("Markup provided by another component than the fragment, components-in-fragment instance:")
                    }
                    div(FragmentPage::componentsInFragmentInstance)

                    hr()

                    p {
                        text("Own markup, HTML markup fragment instance:")
                    }
                    div(FragmentPage::ownMarkupHtmlFragmentInstance)

                    hr()

                    p {
                        text("Own markup, DSL markup fragment instance:")
                    }
                    div(FragmentPage::ownMarkupDslFragmentInstance)

                    hr()

                    div(FragmentPage::fragmentSupplyingPanel)
                    div(FragmentPage::panelSuppliedFragmentInstance)

                    embedWicketFragment(::externalComponentsFragmentWithDelegate)
                    embedWicketFragment(::externalComponentsFragment)
                    embedWicketFragment(::componentsInFragmentVariant1)
                    embedWicketFragment(::componentsInFragmentVariant2)
                }
            }
        }
    }
}
