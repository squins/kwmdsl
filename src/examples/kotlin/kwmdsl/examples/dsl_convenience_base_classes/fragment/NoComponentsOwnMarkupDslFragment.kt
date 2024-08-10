package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.standaloneFragmentMarkup
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.IMarkupResourceStreamProvider

class NoComponentsOwnMarkupDslFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    override fun onInitialize() {
        super.onInitialize()

        add(AttributeAppender.append("class", "specialized"))
    }

    companion object : IKotlinWicketMarkupProvider {
        val noComponentsOwnMarkupBody1 = fragmentBodyMarkup<NCOMDFS> {
            text("No components, specialized DSL Fragment, fragment #1")
        }

        val noComponentsOwnMarkupBody2 = fragmentBodyMarkup<NCOMDFS> {
            text("No components, specialized DSL Fragment, fragment #2")
        }

        override val noVariantMarkup = standaloneFragmentMarkup<NCOMDFS> {
            wicketFragment(::noComponentsOwnMarkupBody1)
            wicketFragment(::noComponentsOwnMarkupBody2)
        }
    }
}

private typealias NCOMDFS = NoComponentsOwnMarkupDslFragment
