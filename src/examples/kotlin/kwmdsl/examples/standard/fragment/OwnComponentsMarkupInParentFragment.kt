package kwmdsl.examples.standard.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime.now
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

class OwnComponentsMarkupInParentFragment(id: String, markupId: String, markupProvider: MarkupContainer) :
    Fragment(id, markupId, markupProvider) {

    override fun onInitialize() {
        super.onInitialize()

        add(AttributeAppender.append("class", "specialized"))

        add(Label("currentTime") {
            ISO_LOCAL_DATE_TIME.format(now())
        })
    }
}
