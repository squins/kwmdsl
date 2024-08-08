package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class FragmentPage : ExamplesConvenienceBasePage() {
    // `fragmentSnippet` and `standaloneFragmentSnippet` must not be root markups. `embedFragmentSnippet` must only
    // accept snippet markups.

    // Need a name for fragment variants:
    // - Fragment variant (similar to the bundle variation parameter). Should `fragmentSnippet`,
    //   `standaloneFragmentSnippet` and `embedFragmentSnippet(...)` be renamed?
    // - Fragment snippet

    // TODO("Document this")
    // There is not check to see if fragment variants have the same component hierarchy.

    // `standAloneFragmentMarkup` must not have an `addTo...(...)` function



    // - 1 or more `standaloneFragmentSnippet`s, with fragment as supplier, `addToFragment(fragment)` not invoked
    // - Embedded in `standAloneFragmentMarkup { ... }`, using `embedFragmentSnippet(...)`
    /*

    val noComponentsOwnMarkupVariant1 = standAloneFragmentVariant<NoComponentsFragment> {
        ...
    }

    val noComponentsOwnMarkupVariant2 = standAloneFragmentVariant<NoComponentsFragment> {
        ...
    }

    override val noVariantMarkup = standAloneFragmentMarkup<NoComponentsFragment> {
        embedFragmentVariant(::noComponentsOwnMarkupVariant1)
        embedFragmentVariant(::noComponentsOwnMarkupVariant2)
    }

    */
    private val xyz_noComponentsOwnMarkupFragmentInstance = 0

    // - 1 or more `fragmentSnippet`s, with parent as supplier, `addToFragment(fragment, supplier)` not invoked
    // - Embedded in any (root) markup, using `embedFragmentSnippet(...)`
    private val xyz_noComponentsMarkupInParentUnspecializedFragmentInstance = 0
    // - Same as with unspecialized fragment
    private val xyz_noComponentsMarkupInParentSpecializedFragmentInstance = 0

    // - 1 or more `fragmentSnippet`s, with other component as supplier, `addToFragment(fragment, supplier)` not invoked
    // - Embedded in any (root) markup, using `embedFragmentSnippet(...)`
    private val xyz_noComponentsMarkupInOtherComponentUnspecializedFragmentInstance = 0
    // - Same as with unspecialized fragment
    private val xyz_noComponentsMarkupInOtherComponentSpecializedFragmentInstance = 0

    // - 1 or more `standaloneFragmentSnippet`s, with fragment as supplier, `addToFragment(fragment)` invoked
    // - Components from 1 of the `standaloneFragmentSnippet`s
    // - Embedded in `standAloneFragmentMarkup { ... }`
    /*

    val withComponentsOwnMarkupVariant1 = standAloneFragmentVariant {
        ...
    }

    // TODO: If another variant is supplied, the component hierarchies can be compared
    val withComponentsOwnMarkupVariant2 = standAloneFragmentVariant {
        ...
    }

    // TODO: Is it possible to remove the generic argument?
    override val noVariantMarkup = standAloneFragmentMarkup<WithComponentsFragment> {
        embedFragmentVariant(::withComponentsOwnMarkupVariant1)
        embedFragmentVariant(::withComponentsOwnMarkupVariant2)
    }

    override val onInitialize() {
        super.onInitialize()

        withComponentsOwnMarkupVariant1.addToFragment(this)
    }
    */
    private val xyz_ownComponentsOwnMarkupFragmentInstance = 0

    // - 1 or more `fragmentSnippet`s, with parent as supplier, `addToFragment(fragment, supplier)` invoked
    // - Components from 1 of the `fragmentSnippet`s
    // - Embedded in any (root) markup, using `embedFragmentSnippet(...)`
    private val xyz_componentsInParentMarkupInParentUnspecializedFragmentInstance = 0
    // - Same as with unspecialized fragment
    private val xyz_componentsInParentMarkupInParentSpecializedFragmentInstance = 0

    // - 1 or more `fragmentSnippet`s, with other component as supplier, `addToFragment(fragment, supplier)` invoked
    // - Components from 1 of the `fragmentSnippet`s
    // - Embedded in any (root) markup, using `embedFragmentSnippet(...)`
    private val xyz_componentsInOtherComponentMarkupInOtherComponentUnspecializedFragmentInstance = 0
    // - Same as with unspecialized fragment
    private val xyz_componentsInOtherComponentMarkupInOtherComponentSpecializedFragmentInstance = 0


    private val fragmentLabel: Label = Label(::fragmentLabel.name, "component in parent")

    private val externalComponentsFragmentInstance: Fragment =
        Fragment(::externalComponentsFragmentInstance.name, ::externalComponentsFragment.name, this).also {
            externalComponentsFragment.addToFragment(it, this)
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
        Fragment(::panelSuppliedFragmentInstance.name, FragmentSupplyingPanel.Companion::fragment.name, fragmentSupplyingPanel)

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
                    p { text("Wicket is very flexible when it comes to fragments, and supports all possible combinations of the table below (and more). But the DSL only supports the combinations specifying what type of fragment can be used:") }
                    table {
                        thead {
                          tr {
                              td(attr("rowspan", "2")) { text("") }
                              th(attr("rowspan", "2")) { text("Own markup") }
                              th(attr("colspan", "3"), attr("style", "text-align: center;")) { text("External markup") }
                          }
                          tr {
                              th { text("Markup in parent") }
                              th { text("Markup in other component, also supplying components") }
                              th { text("Markup in other component, not supplying components") }
                          }
                        }
                        tbody {
                            tr {
                                th { text("No components") }
                                td { text("Specialized only") }
                                td { text("(Un)specialized") }
                                td { text("(Un)specialized") }
                                td { text("(Un)specialized") }
                            }
                            tr {
                                th { text("Own components") }
                                td { text("Specialized only") }
                                td { text("-") }
                                td { text("-") }
                                td { text("-") }
                            }
                            tr {
                                th { text("Components in parent") }
                                td { text("-") }
                                td { text("(Un)specialized") }
                                td { text("-") }
                                td { text("-") }
                            }
                            tr {
                                th { text("Components in other component, also supplying markup") }
                                td { text("-") }
                                td { text("-") }
                                td { text("(Un)specialized") }
                                td { text("-") }
                            }
                            tr {
                                th { text("Components in other component, not supplying markup") }
                                td { text("-") }
                                td { text("-") }
                                td { text("-") }
                                td { text("-") }
                            }
                        }
                    }

                    // TODO("Rewrite cases and DSL to support above combinations as good as possible, update documentation below")
                    p { text("In all cases multiple fragment markup variants are supported. A random one is chosen in each example when the page is loaded and refreshed.") }

                    h2 { text("Examples") }

                    h3 { text("Own Markup") }

                    h3 { text("Markup in Parent") }

                    h3 { text("Markup in Other Component") }

                    h3 { text("Old") }

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
