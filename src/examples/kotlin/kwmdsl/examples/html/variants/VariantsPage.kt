package kwmdsl.examples.html.variants

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import kwmdsl.examples.htmlResourceLinkStyle
import kwmdsl.examples.htmlResourceLinkStyleAndVariation
import kwmdsl.examples.htmlResourceLinkVariation
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.Model
import java.util.Locale

class VariantsPage : ExamplesStandardBasePage() {
    private val styleModel = Model.of("")
    private val variationModel = Model.of("")
    private val localeModel = Model.of("")
    private val variantsForm = Form<Unit>("variantsForm")
    private val style = DropDownChoice("style", styleModel, listOf("", "style1", "style2", "style3"))
    private val overridingVariation = DropDownChoice("variation", variationModel, listOf("", "variation1", "variation2"))
    private val overridingLocale =
        DropDownChoice("locale", localeModel, listOf("", "en", "en_GB", "fr", "fr_FR", "nl", "nl_BE", "nl_NL"))
    private val panel = VariantsPanel("panel")

    override fun onInitialize() {
        super.onInitialize()

        add(variantsForm)
        variantsForm.add(style, overridingVariation, overridingLocale)
        add(panel)

        style.add(object : OnChangeAjaxBehavior() {
            override fun onUpdate(target: AjaxRequestTarget) {
                session.style = styleModel.`object`.takeIf { it.isNotEmpty() }
                target.add(panel)
            }
        })

        overridingVariation.add(object : OnChangeAjaxBehavior() {
            override fun onUpdate(target: AjaxRequestTarget) {
                target.add(panel)
            }
        })

        overridingLocale.add(object : OnChangeAjaxBehavior() {
            override fun onUpdate(target: AjaxRequestTarget) {
                target.add(panel)
            }
        })

        add(
            htmlResourceLink("variantsPanelMarkupLink", VariantsPanel::class),
            htmlResourceLink("variantsPanelEnglishUnitedKingdomMarkupLink", VariantsPanel::class, Locale.UK),
            htmlResourceLink("variantsPanelFrenchMarkupLink", VariantsPanel::class, Locale.FRENCH),
            htmlResourceLink("variantsPanelDutchMarkupLink", VariantsPanel::class, Locale("nl")),
            htmlResourceLink("variantsPanelDutchBelgiumMarkupLink", VariantsPanel::class, Locale("nl", "BE")),
            htmlResourceLinkStyle("variantsPanelStyle1MarkupLink", VariantsPanel::class, "style1"),
            htmlResourceLinkStyle("variantsPanelStyle2MarkupLink", VariantsPanel::class, "style2"),
            htmlResourceLinkVariation("variantsPanelVariation1MarkupLink", VariantsPanel::class, "variation1"),
            htmlResourceLinkStyleAndVariation("variantsPanelStyle2Variation1MarkupLink", VariantsPanel::class, "style2", "variation1"),
            htmlResourceLinkStyleAndVariation("variantsPanelStyle3Variation1MarkupLink", VariantsPanel::class, "style3", "variation1"),
            htmlResourceLinkVariation("variantsPanelVariation2MarkupLink", VariantsPanel::class, "variation2"),
            htmlResourceLinkStyleAndVariation("variantsPanelStyle2Variation2MarkupLink", VariantsPanel::class, "style2", "variation2"),
            htmlResourceLinkStyleAndVariation("variantsPanelStyle2Variation2FrenchFranceMarkupLink", VariantsPanel::class, "style2", "variation2", Locale.FRANCE),
            htmlResourceLink("variantsPageMarkupLink", VariantsPage::class),
        )
    }

    override fun getVariation(): String? {
        val variation = variationModel.`object`
        return variation.ifEmpty { null }
    }

    override fun getLocale(): Locale =
        localesByDisplayValue[localeModel.`object`] ?: super.getLocale()
}

private val localesByDisplayValue = mapOf(
    "" to null,
    "en" to Locale.ENGLISH,
    "en_GB" to Locale.UK,
    "fr" to Locale.FRENCH,
    "fr_FR" to Locale.FRANCE,
    "nl" to Locale("nl"),
    "nl_BE" to Locale("nl", "BE"),
    "nl_NL" to Locale("nl", "NL")
)
