package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.standaloneFragmentBodyMarkup
import com.squins.kwmdsl.standaloneFragmentMarkup
import org.apache.wicket.markup.IMarkupResourceStreamProvider

class NoComponentsOwnMarkupDslFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    companion object : IKotlinWicketMarkupProvider {
        val noComponentsOwnMarkupBody1 = standaloneFragmentBodyMarkup<NoComponentsOwnMarkupDslFragment> {
            text("No components, specialized DSL Fragment, fragment #1")
        }

        val noComponentsOwnMarkupBody2 = standaloneFragmentBodyMarkup<NoComponentsOwnMarkupDslFragment> {
            text("No components, specialized DSL Fragment, fragment #2")
        }

        override val noVariantMarkup = standaloneFragmentMarkup {
            wicketFragment(::noComponentsOwnMarkupBody1)
            wicketFragment(::noComponentsOwnMarkupBody2)
        }
    }
}
