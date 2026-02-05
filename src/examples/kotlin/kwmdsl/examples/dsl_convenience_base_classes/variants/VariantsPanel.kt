package kwmdsl.examples.dsl_convenience_base_classes.variants

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.component.RootMarkupVariants
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.markupStyle
import com.squins.kwmdsl.markupStyleAndVariation
import com.squins.kwmdsl.markupVariation
import java.util.Locale

class VariantsPanel(id: String) : KotlinWicketMarkupPanel(id) {
    override fun onInitialize() {
        super.onInitialize()

        outputMarkupId = true

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<VariantsPanel> {
            wicketPanel {
                text("No variants.")
            }
        }

        override fun getVariantMarkup(style: String?, variation: String?, locale: Locale) =
            variants.get(style, variation, locale)

        private val variants = RootMarkupVariants<VariantsPanel>(this).apply {
            add(markup(Locale.UK) {
                wicketPanel {
                    text("English, United Kingdom.")
                }
            })
            add(markup(Locale.FRENCH) {
                wicketPanel {
                    text("French.")
                }
            })
            add(markup(Locale("nl")) {
                wicketPanel {
                    text("Dutch.")
                }
            })
            add(markup(Locale("nl", "BE")) {
                wicketPanel {
                    text("Dutch, Belgium.")
                }
            })
            add(markupStyle("style1") {
                wicketPanel {
                    text("Style 1.")
                }
            })
            add(markupStyle("style2") {
                wicketPanel {
                    text("Style 2.")
                }
            })
            add(markupVariation("variation1") {
                wicketPanel {
                    text("Variation 1.")
                }
            })
            add(markupStyleAndVariation("style", "variation1") {
                wicketPanel {
                    text("Style 2, variation 1.")
                }
            })
            add(markupStyleAndVariation("style3", "variation1") {
                wicketPanel {
                    text("Style 3, variation 1.")
                }
            })
            add(markupVariation("variation2") {
                wicketPanel {
                    text("Variation 2.")
                }
            })
            add(markupStyleAndVariation("style2", "variation2") {
                wicketPanel {
                    text("Style 2, variation 2.")
                }
            })
            add(markupStyleAndVariation("style3", "variation2") {
                wicketPanel {
                    text("Style 3, variation 2.")
                }
            })
        }
    }
}
