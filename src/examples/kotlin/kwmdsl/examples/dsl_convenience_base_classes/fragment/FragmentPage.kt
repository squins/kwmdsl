package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.dsl_convenience_base_classes.OwnComponentsMarkupInParentFragment
import kwmdsl.examples.dsl_convenience_base_classes.firstSourceCodeLink
import kwmdsl.examples.dsl_convenience_base_classes.sourceCodeLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class FragmentPage : ExamplesConvenienceBasePage() {
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

    private val noComponentsMarkupInParentUnspecializedFragment: Fragment =
        Fragment(
            ::noComponentsMarkupInParentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                Companion::noComponentsMarkupInParentUnspecializedFragmentBody1.name
            } else {
                Companion::noComponentsMarkupInParentUnspecializedFragmentBody2.name
            },
            this
        )
    private val noComponentsMarkupInParentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::noComponentsMarkupInParentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                Companion::noComponentsMarkupInParentSpecializedFragmentBody1.name
            } else {
                Companion::noComponentsMarkupInParentSpecializedFragmentBody2.name
            },
            this
        )

    private val componentsInParentMarkupInParentCurrentTimeUnspecializedFragment: Label =
        Label(::componentsInParentMarkupInParentCurrentTimeUnspecializedFragment.name) {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
        }
    private val componentsInParentMarkupInParentUnspecializedFragment: Fragment =
        Fragment(
            ::componentsInParentMarkupInParentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                Companion::componentsInParentMarkupInParentUnspecializedFragmentBody1.name
            } else {
                Companion::componentsInParentMarkupInParentUnspecializedFragmentBody2.name
            },
            this
        ).apply {
            componentsInParentMarkupInParentUnspecializedFragmentBody1.addToFragment(this, this@FragmentPage)
        }
    private val componentsInParentMarkupInParentCurrentTimeSpecializedFragment: Label =
        Label(::componentsInParentMarkupInParentCurrentTimeSpecializedFragment.name) {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
        }
    private val componentsInParentMarkupInParentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::componentsInParentMarkupInParentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                Companion::componentsInParentMarkupInParentSpecializedFragmentBody1.name
            } else {
                Companion::componentsInParentMarkupInParentSpecializedFragmentBody2.name
            },
            this
        ).apply {
            componentsInParentMarkupInParentSpecializedFragmentBody1.addToFragment(this, this@FragmentPage)
        }

    private val noComponentsMarkupSupplyingPanelForUnspecialized: NoComponentsMarkupSupplyingDslPanel =
        NoComponentsMarkupSupplyingDslPanel(::noComponentsMarkupSupplyingPanelForUnspecialized.name)
    private val noComponentsMarkupInOtherComponentUnspecializedFragment: Fragment =
        Fragment(
            ::noComponentsMarkupInOtherComponentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsMarkupSupplyingDslPanel.Companion::noComponentsMarkupSupplyingForUnspecializedBody1.name
            } else {
                NoComponentsMarkupSupplyingDslPanel.Companion::noComponentsMarkupSupplyingForUnspecializedBody2.name
            },
            noComponentsMarkupSupplyingPanelForUnspecialized
        )
    private val noComponentsMarkupSupplyingPanelForSpecialized: NoComponentsMarkupSupplyingDslPanel =
        NoComponentsMarkupSupplyingDslPanel(::noComponentsMarkupSupplyingPanelForSpecialized.name)
    private val noComponentsMarkupInOtherComponentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::noComponentsMarkupInOtherComponentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsMarkupSupplyingDslPanel.Companion::noComponentsMarkupSupplyingForSpecializedBody1.name
            } else {
                NoComponentsMarkupSupplyingDslPanel.Companion::noComponentsMarkupSupplyingForSpecializedBody2.name
            },
            noComponentsMarkupSupplyingPanelForSpecialized
        )

    private val componentsAndMarkupSupplyingPanelForUnspecialized: ComponentsAndMarkupSupplyingDslPanel =
        ComponentsAndMarkupSupplyingDslPanel(::componentsAndMarkupSupplyingPanelForUnspecialized.name)
    private val componentsAndMarkupInOtherComponentUnspecializedFragment: Fragment =
        Fragment(
            ::componentsAndMarkupInOtherComponentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                ComponentsAndMarkupSupplyingDslPanel.Companion::componentsAndMarkupSupplyingForUnspecializedBody1.name
            } else {
                ComponentsAndMarkupSupplyingDslPanel.Companion::componentsAndMarkupSupplyingForUnspecializedBody2.name
            },
            componentsAndMarkupSupplyingPanelForUnspecialized
        ).apply {
            ComponentsAndMarkupSupplyingDslPanel.componentsAndMarkupSupplyingForUnspecializedBody1.addToFragment(
                this, componentsAndMarkupSupplyingPanelForUnspecialized
            )
        }
    private val componentsAndMarkupSupplyingPanelForSpecialized: ComponentsAndMarkupSupplyingDslPanel =
        ComponentsAndMarkupSupplyingDslPanel(::componentsAndMarkupSupplyingPanelForSpecialized.name)
    private val componentsAndMarkupInOtherComponentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::componentsAndMarkupInOtherComponentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                ComponentsAndMarkupSupplyingDslPanel.Companion::componentsAndMarkupSupplyingForSpecializedBody1.name
            } else {
                ComponentsAndMarkupSupplyingDslPanel.Companion::componentsAndMarkupSupplyingForSpecializedBody2.name
            },
            componentsAndMarkupSupplyingPanelForSpecialized
        ).apply {
            ComponentsAndMarkupSupplyingDslPanel.componentsAndMarkupSupplyingForSpecializedBody1.addToFragment(
                this, componentsAndMarkupSupplyingPanelForSpecialized
            )
        }

    private val ownComponentsMarkupInParentFragment: OwnComponentsMarkupInParentFragment =
        OwnComponentsMarkupInParentFragment(
            ::ownComponentsMarkupInParentFragment.name,
            if (Random.nextBoolean()) {
                Companion::ownComponentsMarkupInParentBody1.name
            } else {
                Companion::ownComponentsMarkupInParentBody2.name
            },
            this
        )

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val noComponentsMarkupInParentUnspecializedFragmentBody1 = fragmentBodyMarkup<FPS> {
            text("No components, unspecialized fragment, fragment #1")
        }

        val noComponentsMarkupInParentUnspecializedFragmentBody2 = fragmentBodyMarkup<FPS> {
            text("No components, unspecialized fragment, fragment #2")
        }

        val noComponentsMarkupInParentSpecializedFragmentBody1 = fragmentBodyMarkup<FPS> {
            text("No components, specialized fragment, fragment #1")
        }

        val noComponentsMarkupInParentSpecializedFragmentBody2 = fragmentBodyMarkup<FPS> {
            text("No components, specialized fragment, fragment #2")
        }

        val componentsInParentMarkupInParentUnspecializedFragmentBody1 = fragmentBodyMarkup {
            text("Components in parent, unspecialized fragment, fragment #1. At: ")
            span(FPS::componentsInParentMarkupInParentCurrentTimeUnspecializedFragment)
        }

        val componentsInParentMarkupInParentUnspecializedFragmentBody2 = fragmentBodyMarkup {
            text("Components in parent, unspecialized fragment, fragment #2. At: ")
            span(FPS::componentsInParentMarkupInParentCurrentTimeUnspecializedFragment)
        }

        val componentsInParentMarkupInParentSpecializedFragmentBody1 = fragmentBodyMarkup {
            text("Components in parent, specialized fragment, fragment #1. At: ")
            span(FPS::componentsInParentMarkupInParentCurrentTimeSpecializedFragment)
        }

        val componentsInParentMarkupInParentSpecializedFragmentBody2 = fragmentBodyMarkup {
            text("Components in parent, specialized fragment, fragment #2. At: ")
            span(FPS::componentsInParentMarkupInParentCurrentTimeSpecializedFragment)
        }

        val ownComponentsMarkupInParentBody1 = fragmentBodyMarkup {
            text("Own components, specialized fragment, fragment #1. At: ")
            span(OwnComponentsMarkupInParentFragment::currentTime)
        }

        val ownComponentsMarkupInParentBody2 = fragmentBodyMarkup {
            text("Own components, specialized fragment, fragment #2. At: ")
            span(OwnComponentsMarkupInParentFragment::currentTime)
        }

        override val noVariantMarkup = markup {
// @formatter:off
wicketHead {
    style {
        // language=css
        text(".specialized { color: coral; }")
    }
}
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
                    td { text("-") }
                    td { text("(Un)specialized") }
                }
                tr {
                    th { text("Own components") }
                    td { text("Specialized only") }
                    td { text("Specialized only") }
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

        p { text("In all cases multiple fragment markup variants are supported. A random one is chosen in each example when the page is loaded and refreshed.") }

        h2 { text("Examples") }

        h3 { text("Own Markup") }

        p {
            span(FPS::noComponentsOwnMarkupDslFragment)
        }

        p {
            span(FPS::noComponentsOwnMarkupHtmlFragment)
        }

        p {
            span(FPS::ownComponentsOwnMarkupDslFragment)
        }

        p {
            span(FPS::ownComponentsOwnMarkupHtmlFragment)
        }

        h3 { text("Markup in Parent") }

        p {
            span(FPS::noComponentsMarkupInParentUnspecializedFragment)
        }

        p {
            span(FPS::noComponentsMarkupInParentSpecializedFragment)
        }

        p {
            span(FPS::ownComponentsMarkupInParentFragment)
        }

        p {
            span(FPS::componentsInParentMarkupInParentUnspecializedFragment)
        }

        p {
            span(FPS::componentsInParentMarkupInParentSpecializedFragment)
        }

        h3 { text("Markup in Other Component, Also Supplying Components") }

        div(FPS::componentsAndMarkupSupplyingPanelForUnspecialized)
        p {
            span(FPS::componentsAndMarkupInOtherComponentUnspecializedFragment)
        }

        div(FPS::componentsAndMarkupSupplyingPanelForSpecialized)
        p {
            span(FPS::componentsAndMarkupInOtherComponentSpecializedFragment)
        }

        h3 { text("Markup in Other Component, Not Supplying Components") }

        div(FPS::noComponentsMarkupSupplyingPanelForUnspecialized)
        p {
            span(FPS::noComponentsMarkupInOtherComponentUnspecializedFragment)
        }

        div(FPS::noComponentsMarkupSupplyingPanelForSpecialized)
        p {
            span(FPS::noComponentsMarkupInOtherComponentSpecializedFragment)
        }

        wicketFragment(::noComponentsMarkupInParentUnspecializedFragmentBody1)
        wicketFragment(::noComponentsMarkupInParentUnspecializedFragmentBody2)
        wicketFragment(::noComponentsMarkupInParentSpecializedFragmentBody1)
        wicketFragment(::noComponentsMarkupInParentSpecializedFragmentBody2)
        wicketFragment(::componentsInParentMarkupInParentUnspecializedFragmentBody1)
        wicketFragment(::componentsInParentMarkupInParentUnspecializedFragmentBody2)
        wicketFragment(::componentsInParentMarkupInParentSpecializedFragmentBody1)
        wicketFragment(::componentsInParentMarkupInParentSpecializedFragmentBody2)
        wicketFragment(::ownComponentsMarkupInParentBody1)
        wicketFragment(::ownComponentsMarkupInParentBody2)
    }
    firstSourceCodeLink(this@Companion)
    sourceCodeLink(ComponentsAndMarkupSupplyingDslPanel.Companion)
    sourceCodeLink(NoComponentsMarkupSupplyingDslPanel.Companion)
    sourceCodeLink(NoComponentsNoMarkupFragment::class)
    sourceCodeLink(NoComponentsOwnMarkupDslFragment.Companion)
    sourceCodeLink(NoComponentsOwnMarkupHtmlFragment.Companion)
    sourceCodeLink(OwnComponentsOwnMarkupDslFragment.Companion)
    sourceCodeLink(OwnComponentsOwnMarkupHtmlFragment.Companion)
}
// @formatter:on
        }
    }
}

private typealias FPS = FragmentPage
