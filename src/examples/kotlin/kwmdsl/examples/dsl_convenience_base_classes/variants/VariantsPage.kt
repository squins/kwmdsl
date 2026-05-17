package kwmdsl.examples.dsl_convenience_base_classes.variants

import com.squins.kwmdsl.TableHeaderScope.COL
import com.squins.kwmdsl.a
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrHref
import com.squins.kwmdsl.attrScope
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.form
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.hr
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.select
import com.squins.kwmdsl.table
import com.squins.kwmdsl.tbody
import com.squins.kwmdsl.td
import com.squins.kwmdsl.th
import com.squins.kwmdsl.thead
import com.squins.kwmdsl.tr
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink
import kwmdsl.examples.sourceCodeLink
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
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("Variants (Convenience Base Classes)") }

        p {
            text("Shows variants of markup based on the ")
            a(attrHref("https://nightlies.apache.org/wicket/guide/9.x/single.html#_style_and_variation_parameters_for_bundles")) {
                text("style, variation and locale")
            }
            text(". Markup is available for the following combinations:")
        }

        p {
            text("Note that Wickes uses the following order for style, variation and locale: ")
            code { text("<base name>[_variation][_style][_<language code>[_<COUNTRY_CODE>[_<variant code>]]]") }
            text("in the filename, but that the priority is: style, variation, locale. That is why the DSL uses the latter order in its API.")
        }

        table(attrClass("table")) {
            thead {
                tr {
                    th(attrScope(COL)) {
                        text("Style")
                    }
                    th(attrScope(COL)) {
                        text("Variation")
                    }
                    th(attrScope(COL)) {
                        text("Locale")
                    }
                }
            }
            tbody {
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("en-GB")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("fr")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("nl")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("nl-BE")
                    }
                }
                tr {
                    td {
                        text("style1")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("style2")
                    }
                    td {
                        text("-")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("style2")
                    }
                    td {
                        text("variation1")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("style2")
                    }
                    td {
                        text("variation2")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("style2")
                    }
                    td {
                        text("variation2")
                    }
                    td {
                        text("fr-FR")
                    }
                }
                tr {
                    td {
                        text("style3")
                    }
                    td {
                        text("variation1")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("variation1")
                    }
                    td {
                        text("-")
                    }
                }
                tr {
                    td {
                        text("-")
                    }
                    td {
                        text("variation2")
                    }
                    td {
                        text("-")
                    }
                }
            }
        }

        form(S::variantsForm) {
            p {
                text("Style: ")
                select(S::style)
            }
            p {
                text("Variation: ")
                select(S::overridingVariation)
            }
            p {
                text("Locale: ")
                select(S::overridingLocale)
            }
        }
        hr()
        div(S::panel)
        firstSourceCodeLink(VariantsPanel.Companion)
        sourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = VariantsPage

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
