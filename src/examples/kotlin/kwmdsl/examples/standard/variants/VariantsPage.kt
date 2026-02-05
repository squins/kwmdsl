package kwmdsl.examples.standard.variants

import kwmdsl.examples.ExamplesStandardBasePage
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
