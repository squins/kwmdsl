package kwmdsl.examples.html.link

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink

class LinkPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        add(htmlResourceLink("linkPageMarkupLink", LinkPage::class))
    }
}
