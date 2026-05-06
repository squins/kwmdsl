package kwmdsl.examples.html.border

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.basic.Label

class BorderPage : ExamplesStandardBasePage() {
    override fun onInitialize() {
        super.onInitialize()
        
        add(SubBorder("border").apply {
            add(Label("pageLabel", "page"))
        })

        add(htmlResourceLink("baseBorderMarkupLink", BaseBorder::class))
        add(htmlResourceLink("borderPageMarkupLink", BorderPage::class))
        add(htmlResourceLink("subBorderMarkupLink", SubBorder::class))
    }
}
