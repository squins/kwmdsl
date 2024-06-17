package kwmdsl.examples.dsl_convenience_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupPanel
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.head
import com.squins.kwmdsl.html
import com.squins.kwmdsl.img
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.metaTextHtmlUtf8
import com.squins.kwmdsl.table
import com.squins.kwmdsl.tbody
import com.squins.kwmdsl.td
import com.squins.kwmdsl.tr

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_divide_et_impera
@Suppress("ClassName")
class P5_3_1_HeaderPanel(id: String) : KotlinWicketMarkupPanel(id) {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup<P5_3_1_HeaderPanel> {
            html {
                head {
                    metaTextHtmlUtf8()
                }
                body {
                    wicketPanel {
                        table(
                            "width" to "100%",
                            "style" to "border: 0px none;"
                        ) {
                            tbody {
                                tr {
                                    td {
                                        img(
                                            "alt" to "Jug4Tenda",
                                            "src" to "wicketLayout_files/logo_jug4tenda.gif"
                                        )
                                    }
                                    td {
                                        h1 {
                                            text("Gestione Anagrafica")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
