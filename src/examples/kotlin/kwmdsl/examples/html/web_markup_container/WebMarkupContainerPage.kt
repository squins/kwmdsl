package kwmdsl.examples.html.web_markup_container

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label

class WebMarkupContainerPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(WebMarkupContainer("container").apply {
            add(
                Label("firstName", "John"),
                Label("lastName", "Doe")
            )
        })

        add(htmlResourceLink("webMarkupContainerPageMarkupLink", WebMarkupContainerPage::class))
    }
}
