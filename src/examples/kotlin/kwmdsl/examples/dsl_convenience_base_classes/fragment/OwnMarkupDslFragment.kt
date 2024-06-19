package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment

class OwnMarkupDslFragment(id: String, markupId: String) : KotlinWicketMarkupFragment(id, markupId) {
    private val fragmentLabel by Wicket { Label(it, "own markup, DSL") }

    override fun onInitialize() {
        super.onInitialize()

        dslMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        // TODO: this can be a singleton, as only the property name is important
        val ownMarkupDslFragment = markup<Fragment> {}

        override val dslMarkup = markup {
            wicketFragment(::ownMarkupDslFragment) {
                p {
                    text("Own markup, DSL fragment: ")
                    span(OwnMarkupDslFragment::fragmentLabel)
                }
            }
        }
    }
}
