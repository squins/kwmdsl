package kwmdsl.examples.dsl_standard_base_classes

import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.findDslMarkup
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
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.panel.Panel

// https://nightlies.apache.org/wicket/guide/10.x/single.html#_divide_et_impera
@Suppress("ClassName")
class P5_3_1_HeaderPanel(id: String) : Panel(id), IMarkupResourceStreamProvider {
    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>) =
        findDslMarkup(containerClass)

    companion object : IKotlinWicketMarkupProvider {
        override val dslMarkup = markup {
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
