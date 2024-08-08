package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.span
import com.squins.kwmdsl.standaloneFragmentBodyMarkup
import com.squins.kwmdsl.standaloneFragmentMarkup
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class OwnComponentsOwnMarkupDslFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    private val currentTime: Label = Label(::currentTime.name) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    override fun onInitialize() {
        super.onInitialize()

        ownComponentsOwnMarkupBody1.addToFragment(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        val ownComponentsOwnMarkupBody1 = standaloneFragmentBodyMarkup {
            text("Own components, specialized Fragment, fragment #1. At: ")
            span(OwnComponentsOwnMarkupDslFragment::currentTime)
        }

        val ownComponentsOwnMarkupBody2 = standaloneFragmentBodyMarkup {
            text("Own components, specialized Fragment, fragment #2. At: ")
            span(OwnComponentsOwnMarkupDslFragment::currentTime)
        }

        override val noVariantMarkup = standaloneFragmentMarkup {
            wicketFragment(::ownComponentsOwnMarkupBody1)
            wicketFragment(::ownComponentsOwnMarkupBody2)
        }
    }
}
