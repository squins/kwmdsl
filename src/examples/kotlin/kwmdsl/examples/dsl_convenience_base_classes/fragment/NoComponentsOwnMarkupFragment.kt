package kwmdsl.examples.dsl_convenience_base_classes.fragment

import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupFragment
import com.squins.kwmdsl.fragmentBodyMarkup
import com.squins.kwmdsl.standaloneFragmentMarkup
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.IMarkupResourceStreamProvider

class NoComponentsOwnMarkupFragment(id: String, markupId: String) :
    KotlinWicketMarkupFragment(id, markupId, null), IMarkupResourceStreamProvider {
    override fun onInitialize() {
        super.onInitialize()

        add(AttributeAppender.append("class", "specialized"))
    }

    companion object : IKotlinWicketMarkupProvider {
        val noComponentsOwnMarkupBody1 = fragmentBodyMarkup<NCOMFS> {
            text("No components, specialized fragment, fragment markup #1")
        }

        val noComponentsOwnMarkupBody2 = fragmentBodyMarkup<NCOMFS> {
            text("No components, specialized fragment, fragment markup #2")
        }

        override val noVariantMarkup = standaloneFragmentMarkup<NCOMFS> {
            wicketFragment(::noComponentsOwnMarkupBody1)
            wicketFragment(::noComponentsOwnMarkupBody2)
        }
    }
}

private typealias NCOMFS = NoComponentsOwnMarkupFragment
