package kwmdsl.examples.dsl_convenience_base_classes.variants

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.component.RootMarkupVariants
import com.squins.kwmdsl.markup
import java.util.*

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
            add(Locale.UK, markup {
                wicketPanel {
                    text("English, United Kingdom.")
                }
            })
            add(Locale.FRENCH, markup {
                wicketPanel {
                    text("French.")
                }
            })
            add(Locale("nl"), markup {
                wicketPanel {
                    text("Dutch.")
                }
            })
            add(Locale("nl", "BE"), markup {
                wicketPanel {
                    text("Dutch, Belgium.")
                }
            })
            addStyle("style1", markup {
                wicketPanel {
                    text("Style 1.")
                }
            })
            addStyle("style2", markup {
                wicketPanel {
                    text("Style 2.")
                }
            })
            addVariation("variation1", markup {
                wicketPanel {
                    text("Variation 1.")
                }
            })
            addStyleAndVariation("style2", "variation1", markup {
                wicketPanel {
                    text("Style 2, variation 1.")
                }
            })
            addStyleAndVariation("style3", "variation1", markup {
                wicketPanel {
                    text("Style 3, variation 1.")
                }
            })
            addVariation("variation2", markup {
                wicketPanel {
                    text("Variation 2.")
                }
            })
            addStyleAndVariation("style2", "variation2", markup {
                wicketPanel {
                    text("Style 2, variation 2.")
                }
            })
            addStyleAndVariation("style3", "variation2", markup {
                wicketPanel {
                    text("Style 3, variation 2.")
                }
            })
        }
    }
}
