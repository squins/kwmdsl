package kwmdsl.examples.dsl_convenience_base_classes.fragment

import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.Markup
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Fragment
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class OwnComponentsOwnMarkupHtmlFragment(id: String, markupId: String) : Fragment(id, markupId, null) {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("currentTime") {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now())
        })
    }

    override fun chooseMarkup(provider: MarkupContainer?): Markup = associatedMarkup

    companion object {
        const val BODY_1_ID = "ownComponentsOwnMarkupBody1"
        const val BODY_2_ID = "ownComponentsOwnMarkupBody2"
    }
}
