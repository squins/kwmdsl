package kwmdsl.examples.standard.fragment

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class FragmentPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(
            NoComponentsOwnMarkupFragment(
                "noComponentsOwnMarkupFragment",
                if (Random.nextBoolean()) {
                    NoComponentsOwnMarkupFragment.BODY_1_ID
                } else {
                    NoComponentsOwnMarkupFragment.BODY_2_ID
                }
            )
        )

        add(
            OwnComponentsOwnMarkupFragment(
                "ownComponentsOwnMarkupFragment",
                if (Random.nextBoolean()) {
                    OwnComponentsOwnMarkupFragment.BODY_1_ID
                } else {
                    OwnComponentsOwnMarkupFragment.BODY_2_ID
                }
            )
        )

        add(
            Fragment(
                "noComponentsMarkupInParentUnspecializedFragment",
                if (Random.nextBoolean()) {
                    NO_COMPONENTS_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_1
                } else {
                    NO_COMPONENTS_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_2
                },
                this
            )
        )

        add(
            NoComponentsNoMarkupFragment(
                "noComponentsMarkupInParentSpecializedFragment",
                if (Random.nextBoolean()) {
                    NO_COMPONENTS_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_1
                } else {
                    NO_COMPONENTS_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_2
                },
                this
            )
        )

        add(
            OwnComponentsMarkupInParentFragment(
                "ownComponentsMarkupInParentFragment",
                if (Random.nextBoolean()) {
                    OWN_COMPONENTS_MARKUP_IN_PARENT_BODY_1
                } else {
                    OWN_COMPONENTS_MARKUP_IN_PARENT_BODY_2
                },
                this
            )
        )

        add (
            Fragment(
                "componentsInParentMarkupInParentUnspecializedFragment",
                if (Random.nextBoolean()) {
                    COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_1
                } else {
                    COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_2
                },
                this
            ).apply {
                add(
                    Label("componentsInParentMarkupInParentUnspecializedFragmentCurrentTime") {
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
                    }
                )
            }
        )

        add(
            NoComponentsNoMarkupFragment(
                "componentsInParentMarkupInParentSpecializedFragment",
                if (Random.nextBoolean()) {
                    COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_1
                } else {
                    COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_2
                },
                this
            ).apply {
                add(
                    Label("componentsInParentMarkupInParentSpecializedFragmentCurrentTime") {
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
                    }
                )
            }
        )

        val componentsAndMarkupSupplyingPanelForUnspecialized = ComponentsAndMarkupSupplyingPanel("componentsAndMarkupSupplyingPanelForUnspecialized")
        add(
            componentsAndMarkupSupplyingPanelForUnspecialized,
            Fragment(
                "componentsAndMarkupInOtherComponentUnspecializedFragment",
                if (Random.nextBoolean()) {
                    ComponentsAndMarkupSupplyingPanel.COMPONENTS_AND_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_1
                } else {
                    ComponentsAndMarkupSupplyingPanel.COMPONENTS_AND_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_2
                },
                componentsAndMarkupSupplyingPanelForUnspecialized
            ).apply {
                add(
                    componentsAndMarkupSupplyingPanelForUnspecialized.currentTime
                )
            }
        )

        val componentsAndMarkupSupplyingPanelForSpecialized = ComponentsAndMarkupSupplyingPanel("componentsAndMarkupSupplyingPanelForSpecialized")
        add(
            componentsAndMarkupSupplyingPanelForSpecialized,
            NoComponentsNoMarkupFragment(
                "componentsAndMarkupInOtherComponentSpecializedFragment",
                if (Random.nextBoolean()) {
                    ComponentsAndMarkupSupplyingPanel.COMPONENTS_AND_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_1
                } else {
                    ComponentsAndMarkupSupplyingPanel.COMPONENTS_AND_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_2
                },
                componentsAndMarkupSupplyingPanelForSpecialized
            ).apply {
                add(
                    componentsAndMarkupSupplyingPanelForSpecialized.currentTime
                )
            }
        )

        val noComponentsMarkupSupplyingPanelForUnspecialized = NoComponentsMarkupSupplyingPanel("noComponentsMarkupSupplyingPanelForUnspecialized")
        add(
            noComponentsMarkupSupplyingPanelForUnspecialized,
            Fragment(
                "noComponentsMarkupInOtherComponentUnspecializedFragment",
                if (Random.nextBoolean()) {
                    NoComponentsMarkupSupplyingPanel.NO_COMPONENTS_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_1
                } else {
                    NoComponentsMarkupSupplyingPanel.NO_COMPONENTS_MARKUP_SUPPLYING_FOR_UNSPECIALIZED_BODY_2
                },
                noComponentsMarkupSupplyingPanelForUnspecialized
            )
        )

        val noComponentsMarkupSupplyingPanelForSpecialized = NoComponentsMarkupSupplyingPanel("noComponentsMarkupSupplyingPanelForSpecialized")
        add(
            noComponentsMarkupSupplyingPanelForSpecialized,
            NoComponentsNoMarkupFragment(
                "noComponentsMarkupInOtherComponentSpecializedFragment",
                if (Random.nextBoolean()) {
                    NoComponentsMarkupSupplyingPanel.NO_COMPONENTS_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_1
                } else {
                    NoComponentsMarkupSupplyingPanel.NO_COMPONENTS_MARKUP_SUPPLYING_FOR_SPECIALIZED_BODY_2
                },
                noComponentsMarkupSupplyingPanelForSpecialized
            )
        )

        add(htmlResourceLink("componentsAndMarkupSupplyingPanelMarkupLink", ComponentsAndMarkupSupplyingPanel::class))
        add(htmlResourceLink("fragmentPageMarkupLink", FragmentPage::class))
        add(htmlResourceLink("noComponentsMarkupSupplyingPanelMarkupLink", NoComponentsMarkupSupplyingPanel::class))
        add(htmlResourceLink("noComponentsOwnMarkupFragmentMarkupLink", NoComponentsOwnMarkupFragment::class))
        add(htmlResourceLink("ownComponentsOwnMarkupFragmentMarkupLink", OwnComponentsOwnMarkupFragment::class))
    }

    companion object {
        const val NO_COMPONENTS_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_1 = "noComponentsMarkupInParentUnspecializedFragmentBody1"
        const val NO_COMPONENTS_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_2 = "noComponentsMarkupInParentUnspecializedFragmentBody2"
        const val NO_COMPONENTS_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_1 = "noComponentsMarkupInParentSpecializedFragmentBody1"
        const val NO_COMPONENTS_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_2 = "noComponentsMarkupInParentSpecializedFragmentBody2"
        const val OWN_COMPONENTS_MARKUP_IN_PARENT_BODY_1 = "ownComponentsMarkupInParentBody1"
        const val OWN_COMPONENTS_MARKUP_IN_PARENT_BODY_2 = "ownComponentsMarkupInParentBody2"
        const val COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_1 = "componentsInParentMarkupInParentUnspecializedFragmentBody1"
        const val COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_UNSPECIALIZED_FRAGMENT_BODY_2 = "componentsInParentMarkupInParentUnspecializedFragmentBody2"
        const val COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_1 = "componentsInParentMarkupInParentSpecializedFragmentBody1"
        const val COMPONENTS_IN_PARENT_MARKUP_IN_PARENT_SPECIALIZED_FRAGMENT_BODY_2 = "componentsInParentMarkupInParentSpecializedFragmentBody2"
    }
}
