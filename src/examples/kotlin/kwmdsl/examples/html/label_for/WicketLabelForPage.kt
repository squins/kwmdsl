package kwmdsl.examples.html.label_for

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model

class WicketLabelForPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(
            TextField<String>("rootSibling").apply {
                label = Model.of("Sibling")
            },

            WebMarkupContainer("rootSiblingOfParentLabelContainer"),
            TextField<String>("rootSiblingOfParent").apply {
                label = Model.of("Sibling of parent")
            },

            WebMarkupContainer("rootChildOfSiblingLabelContainer"),
            WebMarkupContainer("rootChildOfSiblingInputContainer").add(
                TextField<String>("rootChildOfSibling").apply {
                    label = Model.of("Child of sibling")
                }
            ),

            WebMarkupContainer("nestedSiblingRootContainer").add(
                TextField<String>("nestedSibling").apply {
                    label = Model.of("Sibling")
                }
            ),

            WebMarkupContainer("nestedSiblingOfParentRootContainer").add(
                WebMarkupContainer("nestedSiblingOfParentLabelContainer"),
                TextField<String>("nestedSiblingOfParent").apply {
                    label = Model.of("Sibling of parent")
                }
            ),

            WebMarkupContainer("nestedChildOfSiblingRootContainer").add(
                WebMarkupContainer("nestedChildOfSiblingLabelContainer"),
                WebMarkupContainer("nestedChildOfSiblingInputContainer").add(
                    TextField<String>("nestedChildOfSibling").apply {
                        label = Model.of("Child of sibling")
                    }
                )
            ),

            WebMarkupContainer("nestedNoCommonPathPartsLabelRootContainer").add(
                WebMarkupContainer("nestedNoCommonPathPartsLabelContainer")
            ),
            WebMarkupContainer("nestedNoCommonPathPartsInputRootContainer").add(
                WebMarkupContainer("nestedNoCommonPathPartsInputContainer").add(
                    TextField<String>("nestedNoCommonPathParts").apply {
                        label = Model.of("No common path parts")
                    }
                )
            ),

            LabelForDivBorder("rootBorder").add(
                LabelForStrongBorder("labelLevel1Border").add(
                    WebMarkupContainer("nestedNoCommonPathPartsLabelRootContainerManyBorders").add(
                        LabelForStrongBorder("labelLevel2Border").add(
                            WebMarkupContainer("nestedNoCommonPathPartsLabelContainerManyBorders").add(
                                LabelForStrongBorder("labelLevel3Border")
                            )
                        )
                    )
                ),
                LabelForStrongBorder("inputLevel1Border").add(
                    WebMarkupContainer("nestedNoCommonPathPartsInputRootContainerManyBorders").add(
                        LabelForStrongBorder("inputLevel2Border").add(
                            WebMarkupContainer("nestedNoCommonPathPartsInputContainerManyBorders").add(
                                LabelForStrongBorder("inputLevel3Border").add(
                                    TextField<String>("nestedNoCommonPathPartsManyBorders").apply {
                                        label = Model.of("No common path parts, with many borders")
                                    }
                                )
                            )
                        )
                    )
                )
            ),

            htmlResourceLink("wicketLabelForPageMarkupLink", WicketLabelForPage::class)
        )
    }
}
