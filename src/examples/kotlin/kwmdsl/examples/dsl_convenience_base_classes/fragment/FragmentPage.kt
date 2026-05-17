package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.attr
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrColspan
import com.squins.kwmdsl.attrRowspan
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.dd
import com.squins.kwmdsl.div
import com.squins.kwmdsl.dl
import com.squins.kwmdsl.dt
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.h3
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import com.squins.kwmdsl.table
import com.squins.kwmdsl.tbody
import com.squins.kwmdsl.td
import com.squins.kwmdsl.th
import com.squins.kwmdsl.thead
import com.squins.kwmdsl.tr
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import kwmdsl.examples.sourceCodeLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class FragmentPage : ExamplesConvenienceBasePage() {
    private val noComponentsOwnMarkupFragment: NoComponentsOwnMarkupFragment =
        NoComponentsOwnMarkupFragment(
            ::noComponentsOwnMarkupFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsOwnMarkupFragment.Companion::noComponentsOwnMarkupBody1.name
            } else {
                NoComponentsOwnMarkupFragment.Companion::noComponentsOwnMarkupBody2.name
            }
        )

    private val ownComponentsOwnMarkupFragment: OwnComponentsOwnMarkupFragment =
        OwnComponentsOwnMarkupFragment(
            ::ownComponentsOwnMarkupFragment.name,
            if (Random.nextBoolean()) {
                OwnComponentsOwnMarkupFragment.Companion::ownComponentsOwnMarkupBody1.name
            } else {
                OwnComponentsOwnMarkupFragment.Companion::ownComponentsOwnMarkupBody2.name
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

    private val componentsInParentMarkupInParentUnspecializedFragmentCurrentTime: Label =
        Label(::componentsInParentMarkupInParentUnspecializedFragmentCurrentTime.name) {
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

    private val componentsInParentMarkupInParentSpecializedFragmentCurrentTime: Label =
        Label(::componentsInParentMarkupInParentSpecializedFragmentCurrentTime.name) {
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

    private val componentsAndMarkupSupplyingPanelForUnspecialized: ComponentsAndMarkupSupplyingPanel =
        ComponentsAndMarkupSupplyingPanel(::componentsAndMarkupSupplyingPanelForUnspecialized.name)
    private val componentsAndMarkupInOtherComponentUnspecializedFragment: Fragment =
        Fragment(
            ::componentsAndMarkupInOtherComponentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                ComponentsAndMarkupSupplyingPanel.Companion::componentsAndMarkupSupplyingForUnspecializedBody1.name
            } else {
                ComponentsAndMarkupSupplyingPanel.Companion::componentsAndMarkupSupplyingForUnspecializedBody2.name
            },
            componentsAndMarkupSupplyingPanelForUnspecialized
        ).apply {
            ComponentsAndMarkupSupplyingPanel.componentsAndMarkupSupplyingForUnspecializedBody1.addToFragment(
                this, componentsAndMarkupSupplyingPanelForUnspecialized
            )
        }

    private val componentsAndMarkupSupplyingPanelForSpecialized: ComponentsAndMarkupSupplyingPanel =
        ComponentsAndMarkupSupplyingPanel(::componentsAndMarkupSupplyingPanelForSpecialized.name)
    private val componentsAndMarkupInOtherComponentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::componentsAndMarkupInOtherComponentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                ComponentsAndMarkupSupplyingPanel.Companion::componentsAndMarkupSupplyingForSpecializedBody1.name
            } else {
                ComponentsAndMarkupSupplyingPanel.Companion::componentsAndMarkupSupplyingForSpecializedBody2.name
            },
            componentsAndMarkupSupplyingPanelForSpecialized
        ).apply {
            ComponentsAndMarkupSupplyingPanel.componentsAndMarkupSupplyingForSpecializedBody1.addToFragment(
                this, componentsAndMarkupSupplyingPanelForSpecialized
            )
        }

    private val noComponentsMarkupSupplyingPanelForUnspecialized: NoComponentsMarkupSupplyingPanel =
        NoComponentsMarkupSupplyingPanel(::noComponentsMarkupSupplyingPanelForUnspecialized.name)
    private val noComponentsMarkupInOtherComponentUnspecializedFragment: Fragment =
        Fragment(
            ::noComponentsMarkupInOtherComponentUnspecializedFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsMarkupSupplyingPanel.Companion::noComponentsMarkupSupplyingForUnspecializedBody1.name
            } else {
                NoComponentsMarkupSupplyingPanel.Companion::noComponentsMarkupSupplyingForUnspecializedBody2.name
            },
            noComponentsMarkupSupplyingPanelForUnspecialized
        )

    private val noComponentsMarkupSupplyingPanelForSpecialized: NoComponentsMarkupSupplyingPanel =
        NoComponentsMarkupSupplyingPanel(::noComponentsMarkupSupplyingPanelForSpecialized.name)
    private val noComponentsMarkupInOtherComponentSpecializedFragment: NoComponentsNoMarkupFragment =
        NoComponentsNoMarkupFragment(
            ::noComponentsMarkupInOtherComponentSpecializedFragment.name,
            if (Random.nextBoolean()) {
                NoComponentsMarkupSupplyingPanel.Companion::noComponentsMarkupSupplyingForSpecializedBody1.name
            } else {
                NoComponentsMarkupSupplyingPanel.Companion::noComponentsMarkupSupplyingForSpecializedBody2.name
            },
            noComponentsMarkupSupplyingPanelForSpecialized
        )

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val noComponentsMarkupInParentUnspecializedFragmentBody1 = fragmentBodyMarkup<FPS> {
            text("No components, unspecialized fragment, fragment markup #1")
        }

        val noComponentsMarkupInParentUnspecializedFragmentBody2 = fragmentBodyMarkup<FPS> {
            text("No components, unspecialized fragment, fragment markup #2")
        }

        val noComponentsMarkupInParentSpecializedFragmentBody1 = fragmentBodyMarkup<FPS> {
            text("No components, specialized fragment, fragment markup #1")
        }

        val noComponentsMarkupInParentSpecializedFragmentBody2 = fragmentBodyMarkup<FPS> {
            text("No components, specialized fragment, fragment markup #2")
        }

        val componentsInParentMarkupInParentUnspecializedFragmentBody1 = fragmentBodyMarkup {
            text("Components in parent, unspecialized fragment, fragment markup #1. At: ")
            span(FPS::componentsInParentMarkupInParentUnspecializedFragmentCurrentTime)
        }

        val componentsInParentMarkupInParentUnspecializedFragmentBody2 = fragmentBodyMarkup {
            text("Components in parent, unspecialized fragment, fragment markup #2. At: ")
            span(FPS::componentsInParentMarkupInParentUnspecializedFragmentCurrentTime)
        }

        val componentsInParentMarkupInParentSpecializedFragmentBody1 = fragmentBodyMarkup {
            text("Components in parent, specialized fragment, fragment markup #1. At: ")
            span(FPS::componentsInParentMarkupInParentSpecializedFragmentCurrentTime)
        }

        val componentsInParentMarkupInParentSpecializedFragmentBody2 = fragmentBodyMarkup {
            text("Components in parent, specialized fragment, fragment markup #2. At: ")
            span(FPS::componentsInParentMarkupInParentSpecializedFragmentCurrentTime)
        }

        val ownComponentsMarkupInParentBody1 = fragmentBodyMarkup {
            text("Own components, specialized fragment, fragment markup #1. At: ")
            span(OwnComponentsMarkupInParentFragment::currentTime)
        }

        val ownComponentsMarkupInParentBody2 = fragmentBodyMarkup {
            text("Own components, specialized fragment, fragment markup #2. At: ")
            span(OwnComponentsMarkupInParentFragment::currentTime)
        }

        override val noVariantMarkup = markup {
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("Fragment (DSL, Convenience Base Classes)") }

        h2 { text("Introduction") }

        p {
            text("Wicket is very flexible when it comes to fragments, and supports most combinations of the table below (and more). But the DSL only supports the combinations specifying which ")
            code { text("Fragment") }
            text(" classes can be used. Explanation of the table headers and cell values:")
        }

        h3 { text("Markup Location") }

        dl {
            dt { text("Own markup") }
            dd { text("The fragment has its own markup file.") }

            dt { text("Markup in parent") }
            dd { text("The fragment markup is embedded in the parent markup to which the fragment is added.") }

            dt { text("Markup in other component, also supplying components") }
            dd { text("The fragment markup is embedded in the markup of a descendent component. The descendent component also creates the descendent components of the fragment. However, the adding of the components to the fragment is still done by the parent.") }

            dt { text("Markup in other component, not supplying components") }
            dd { text("The fragment markup is embedded in the markup of a descendent component. The fragment does not contain descendent components.") }
        }

        h3 { text("Components Location") }

        dl {
            dt { text("No components") }
            dd { text("The fragment does not contain components.") }

            dt { text("Own components") }
            dd { text("The fragment creates its descendent components itself.") }

            dt { text("Components in parent") }
            dd { text("The parent of the fragment creates the descendent components of the fragment. The adding of the components to the fragment is done by the parent.") }

            dt { text("Components in other component, also supplying markup.") }
            dd { text("A descendent component creates the descendent components of the fragment. However, the adding of the components to the fragment is still done by the parent. The descendent component also supplies the fragment markup.") }

            dt { text("Components in other component, not supplying markup") }
            dd { text("A descendent component creates the descendent components of the fragment. The fragment markup is located somewhere else than embedded in the descendent component markup.") }
        }

        h3 { text("Allowed Fragment Classes") }

        dl {
            dt { text("-") }
            dd { text("This combination is not supported by the DSL.") }

            dt { text("(Un)specialized") }
            dd {
                code { text("Fragment") }
                text(" can be used as is. A subclass is also allowed.")
            }

            dt { text("Specialized only") }
            dd {
                text("A subclass of ")
                code { text("Fragment") }
                text(" must be used.")
            }
        }

        table {
            thead {
              tr {
                  td(attrRowspan(2)) { text("") }
                  th(attrRowspan(2)) { text("Own markup") }
                  th(attrColspan(3), attr("align", "center")) { text("External markup") }
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

        p {
            text("Note that the (subclass (shown in a different color) of) ")
            code { text("Fragment") }
            text(" is always added directly to the page in the examples below.")
        }

        p { text("In all cases multiple fragment markup variants are supported. A random one is chosen in each example when the page is loaded and refreshed.") }

        h2 { text("Examples") }

        h3 { text("Own Markup") }

        p {
            span(FPS::noComponentsOwnMarkupFragment)
        }

        p {
            span(FPS::ownComponentsOwnMarkupFragment)
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
        firstSourceCodeLink(ComponentsAndMarkupSupplyingPanel.Companion)
        sourceCodeLink(this@Companion)
        sourceCodeLink(NoComponentsMarkupSupplyingPanel.Companion)
        sourceCodeLink(NoComponentsNoMarkupFragment::class)
        sourceCodeLink(NoComponentsOwnMarkupFragment.Companion)
        sourceCodeLink(OwnComponentsMarkupInParentFragment::class)
        sourceCodeLink(OwnComponentsOwnMarkupFragment.Companion)
    }
}
// @formatter:on
        }
    }
}

private typealias FPS = FragmentPage
