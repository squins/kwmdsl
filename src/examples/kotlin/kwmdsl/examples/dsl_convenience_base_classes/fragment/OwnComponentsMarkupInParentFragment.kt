package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class OwnComponentsMarkupInParentFragment(id: String, markupId: String, markupProvider: MarkupContainer) :
    Fragment(id, markupId, markupProvider) {
    val currentTime: Label = Label(::currentTime.name) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
    }

    override fun onInitialize() {
        super.onInitialize()

        add(AttributeAppender.append("class", "specialized"))

        FragmentPage.ownComponentsMarkupInParentBody1.addToFragment(this)
    }
}
