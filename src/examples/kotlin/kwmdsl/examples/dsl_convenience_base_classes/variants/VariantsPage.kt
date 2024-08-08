package kwmdsl.examples.dsl_convenience_base_classes.variants

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.Model
import java.util.*

class VariantsPage : ExamplesConvenienceBasePage() {
    private val styleModel = Model.of("")
    private val variationModel = Model.of("")
    private val localeModel = Model.of("")
    private val variantsForm: Form<Unit> = Form<Unit>(::variantsForm.name)
    private val style: DropDownChoice<String> = DropDownChoice(::style.name, styleModel, listOf("", "style1", "style2", "style3"))
    private val overridingVariation: DropDownChoice<String> = DropDownChoice(::overridingVariation.name, variationModel, listOf("", "variation1", "variation2"))
    private val overridingLocale: DropDownChoice<String> = DropDownChoice(::overridingLocale.name, localeModel, listOf("", "en", "en_GB", "fr", "fr_FR", "nl", "nl_BE", "nl_NL"))
    private val panel: VariantsPanel = VariantsPanel(::panel.name)

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)

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

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Variants") }
                form(VariantsPage::variantsForm) {
                    p {
                        text("Style: ")
                        select(VariantsPage::style)
                    }
                    p {
                        text("Variation: ")
                        select(VariantsPage::overridingVariation)
                    }
                    p {
                        text("Locale: ")
                        select(VariantsPage::overridingLocale)
                    }
                }
                hr()
                div(VariantsPage::panel)
            }
        }
    }
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
