package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

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

    // TODO("Document this")
    // There is no check to see if fragment markup bodies have the same component hierarchy.

    // TODO: also show for Java Fragment with constants for the body variant IDs
    private val xyz_noComponentsOwnMarkupFragment: NoComponentsFragment =
        NoComponentsFragment(
            ::xyz_noComponentsOwnMarkupFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsFragment.Companion::noComponentsOwnMarkupBody1.name
            } else {
                NoComponentsFragment.Companion::noComponentsOwnMarkupBody2.name
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

    // TODO: also show for Java Fragment with constants for the body variant IDs
    private val xyz_ownComponentsOwnMarkupFragment: OwnComponentsFragment =
        OwnComponentsFragment(
            ::xyz_ownComponentsOwnMarkupFragment.name,
            if (Random.nextBoolean()) {
                OwnComponentsFragment.Companion::ownComponentsOwnMarkupBody1.name
            } else {
                OwnComponentsFragment.Companion::ownComponentsOwnMarkupBody2.name
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
                        span(FragmentPage::xyz_noComponentsOwnMarkupFragment)
                    }

                    p {
                        span(FragmentPage::xyz_ownComponentsOwnMarkupFragment)
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

class NoComponentsFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    companion object : IKotlinWicketMarkupProvider {
        val noComponentsOwnMarkupBody1 = standaloneFragmentBodyMarkup<NoComponentsFragment> {
            text("No components, specialized Fragment, fragment #1")
        }

        val noComponentsOwnMarkupBody2 = standaloneFragmentBodyMarkup<NoComponentsFragment> {
            text("No components, specialized Fragment, fragment #2")
        }

        override val noVariantMarkup = standaloneFragmentMarkup {
            wicketFragment(::noComponentsOwnMarkupBody1)
            wicketFragment(::noComponentsOwnMarkupBody2)
        }
    }
}

class OwnComponentsFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    private val currentTime: Label = Label(::currentTime.name) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    override fun onInitialize() {
        super.onInitialize()

        ownComponentsOwnMarkupBody1.addToFragment(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val ownComponentsOwnMarkupBody1 = standaloneFragmentBodyMarkup {
            text("Own components, specialized Fragment, fragment #1. At: ")
            span(OwnComponentsFragment::currentTime)
        }

        val ownComponentsOwnMarkupBody2 = standaloneFragmentBodyMarkup {
            text("Own components, specialized Fragment, fragment #2. At: ")
            span(OwnComponentsFragment::currentTime)
        }

        override val noVariantMarkup = standaloneFragmentMarkup {
            wicketFragment(::ownComponentsOwnMarkupBody1)
            wicketFragment(::ownComponentsOwnMarkupBody2)
        }
    }
}
