package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import org.apache.wicket.markup.html.basic.Label

class OwnMarkupDslFragment(id: String, markupId: String) : KotlinWicketMarkupFragment(id, markupId) {
    constructor(id: String) : this(id, ::ownMarkupStandardFragmentVariant1.name)

    private val fragmentLabel: Label = Label(::fragmentLabel.name, "own markup, DSL")

    override fun onInitialize() {
        super.onInitialize()

        // TODO("Only 1 argument should be required. Introduce `fragmentMarkup`? But what should the markup for `noVariantMarkup` be called then?")
        ownMarkupStandardFragmentVariant1.addToFragment(this, this)
    }

    companion object : IKotlinWicketMarkupProvider {
        // TODO("No check to make sure the markup hierarchies of variants are the same. This also applies to fragments that do not have markup but do define the Wicket components")
        val ownMarkupStandardFragmentVariant1 = externalComponentsFragmentMarkup {
            p {
                text("Own markup, DSL markup fragment, variant 1: ")
                span(OwnMarkupDslFragment::fragmentLabel)
            }
        }
        val ownMarkupStandardFragmentVariant2 = externalComponentsFragmentMarkup {
            p {
                text("Own markup, DSL markup fragment, variant 2: ")
                span(OwnMarkupDslFragment::fragmentLabel)
            }
        }

        override val noVariantMarkup = markup<OwnMarkupDslFragment> {
            // TODO("Do not require this for fragment types")
            div()
            embedWicketFragment(::ownMarkupStandardFragmentVariant1)
            embedWicketFragment(::ownMarkupStandardFragmentVariant2)
        }
    }
}
