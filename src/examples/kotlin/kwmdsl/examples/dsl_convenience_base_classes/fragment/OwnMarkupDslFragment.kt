package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.ownMarkupFragmentMarkup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.span
import org.apache.wicket.markup.html.basic.Label

// TODO("The primary constructor can be private if the `Wicket...` delegates are dropped.")
class OwnMarkupDslFragment(id: String, markupId: String) : KotlinWicketMarkupFragment(id, markupId) {
    constructor(id: String) : this(id, ::ownMarkupDslFragment.name)

    private val fragmentLabel by Wicket { Label(it, "own markup, DSL") }

    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val ownMarkupDslFragment = ownMarkupFragmentMarkup

        override val noVariantMarkup = markup {
            wicketFragment(::ownMarkupDslFragment) {
                p {
                    text("Own markup, DSL fragment: ")
                    span(OwnMarkupDslFragment::fragmentLabel)
                }
            }
        }
    }
}
