package kwmdsl.examples.dsl_convenience_base_classes.variants

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.classH1
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.form
import com.squins.kwmdsl.hr
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.select
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.Model
import java.util.Locale

class VariantsPage : ExamplesConvenienceBasePage() {
    private val styleModel = Model.of("")
    private val variationModel = Model.of("")
    private val localeModel = Model.of("")
    private val variantsForm by Wicket { Form<Unit>(it)}
    private val style by Wicket { DropDownChoice<String>(it, styleModel, listOf("", "style1", "style2", "style3"))}
    private val overridingVariation by Wicket { DropDownChoice<String>(it, variationModel, listOf("", "variation1", "variation2")) }
    private val overridingLocale by Wicket { DropDownChoice<String>(it, localeModel, listOf("", "en", "en_GB", "fr", "fr_FR", "nl", "nl_BE", "nl_NL"))}
    private val panel by Wicket { VariantsPanel(it) }

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
        return if (variation.isEmpty()) null else variation
    }

    override fun getLocale(): Locale? {
        val locale = localesByDisplayValue[localeModel.`object`]
        return if (locale == null) super.locale else locale
    }

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
