package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import kotlin.random.Random

// TODO("See if this is possible without changes to current design: external markup, own components")
class FragmentPage : ExamplesConvenienceBasePage() {
    // `fragmentBody` and `standaloneFragmentBody` must not be root markups. `wicketFragment` must only
    // accept fragment body markups.
    //
    // - `fragmentBody`
    //   - Supplier must be sub type of `MarkupContainer`
    //   - `addToFragment(fragment, supplier)`
    // - `wicketFragment`
    //   - Provides `<wicket:fragment>...</wicket:fragment>`
    //
    // - `standAloneFragmentBody`
    //   - Supplier must be sub type of `Fragment`
    //   - `addToFragment(fragment)`
    // - `standaloneFragmentMarkup`
    //   - Provides `<!-- -->...`
    //   - no 'addTo...(...)`
    // - `wicketFragment`
    //   - Provides `<wicket:fragment>...</wicket:fragment>`

    // TODO: also show for Java Fragment with constants for the body variant IDs
    private val noComponentsOwnMarkupDslFragment: NoComponentsOwnMarkupDslFragment =
        NoComponentsOwnMarkupDslFragment(
            ::noComponentsOwnMarkupDslFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsOwnMarkupDslFragment.Companion::noComponentsOwnMarkupBody1.name
            } else {
                NoComponentsOwnMarkupDslFragment.Companion::noComponentsOwnMarkupBody2.name
            }
        )
    private val noComponentsOwnMarkupHtmlFragment: NoComponentsOwnMarkupHtmlFragment =
        NoComponentsOwnMarkupHtmlFragment(
            ::noComponentsOwnMarkupHtmlFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsOwnMarkupHtmlFragment.BODY_1_ID
            } else {
                NoComponentsOwnMarkupHtmlFragment.BODY_2_ID
            }
        )


    // - 1 or more `fragmentBody`s, with parent as supplier, `addToFragment(fragment, supplier)` not invoked
    // - Embedded in any (root) markup, using `wicketFragment(...)`
    private val xyz_noComponentsMarkupInParentUnspecializedFragment = 0
    // - Same as with unspecialized fragment
    private val xyz_noComponentsMarkupInParentSpecializedFragment = 0

    // - 1 or more `fragmentBody`s, with other component as supplier, `addToFragment(fragment, supplier)` not invoked
    // - Embedded in any (root) markup, using `wicketFragment(...)`
    private val xyz_noComponentsMarkupInOtherComponentUnspecializedFragment = 0
    // - Same as with unspecialized fragment
    private val xyz_noComponentsMarkupInOtherComponentSpecializedFragment = 0

    private val ownComponentsOwnMarkupDslFragment: OwnComponentsOwnMarkupDslFragment =
        OwnComponentsOwnMarkupDslFragment(
            ::ownComponentsOwnMarkupDslFragment.name,
            if (Random.nextBoolean()) {
                OwnComponentsOwnMarkupDslFragment.Companion::ownComponentsOwnMarkupBody1.name
            } else {
                OwnComponentsOwnMarkupDslFragment.Companion::ownComponentsOwnMarkupBody2.name
            }
        )
    private val ownComponentsOwnMarkupHtmlFragment: OwnComponentsOwnMarkupHtmlFragment =
        OwnComponentsOwnMarkupHtmlFragment(
            ::ownComponentsOwnMarkupHtmlFragment.name,
            if (Random.nextBoolean()) {
                OwnComponentsOwnMarkupHtmlFragment.BODY_1_ID
            } else {
                OwnComponentsOwnMarkupHtmlFragment.BODY_2_ID
            }
        )

    // - 1 or more `fragmentBody`s, with parent as supplier, `addToFragment(fragment, supplier)` invoked
    // - Components from 1 of the `fragmentBody`s
    // - Embedded in any (root) markup, using `wicketFragment(...)`
    private val xyz_componentsInParentMarkupInParentUnspecializedFragment = 0
    // - Same as with unspecialized fragment
    private val xyz_componentsInParentMarkupInParentSpecializedFragment = 0

    // - 1 or more `fragmentBody`s, with other component as supplier, `addToFragment(fragment, supplier)` invoked
    // - Components from 1 of the `fragmentBody`s
    // - Embedded in any (root) markup, using `wicketFragment(...)`
    private val xyz_componentsInOtherComponentMarkupInOtherComponentUnspecializedFragment = 0
    // - Same as with unspecialized fragment
    private val xyz_componentsInOtherComponentMarkupInOtherComponentSpecializedFragment = 0


    private val fragmentLabel: Label = Label(::fragmentLabel.name, "component in parent")

    private val externalComponentsFragmentInstance: Fragment =
        Fragment(::externalComponentsFragmentInstance.name, ::externalComponentsFragment.name, this).also {
            externalComponentsFragment.addToFragment(it, this)
        }

    private val componentsInFragmentInstance: ComponentsInFragment =
        ComponentsInFragment(::componentsInFragmentInstance.name, ::componentsInFragmentVariant2.name, this)

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

        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Fragment") }
                classDiv("content") {
                    h2 { text("Introduction") }
                    // TODO("also put this in the documentation")
                    p { text("Wicket is very flexible when it comes to fragments, and supports most combinations of the table below (and more). But the DSL only supports the combinations specifying what type of fragment can be used:") }
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

                    p {
                        span(FragmentPage::noComponentsOwnMarkupDslFragment)
                    }

                    p {
                        span(FragmentPage::noComponentsOwnMarkupHtmlFragment)
                    }

                    p {
                        span(FragmentPage::ownComponentsOwnMarkupDslFragment)
                    }

                    p {
                        span(FragmentPage::ownComponentsOwnMarkupHtmlFragment)
                    }

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
