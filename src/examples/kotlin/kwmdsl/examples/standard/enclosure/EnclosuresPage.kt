package kwmdsl.examples.standard.enclosure

import kwmdsl.examples.ExamplesStandardBasePage
import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : ExamplesStandardBasePage() {
    private val autoSpan = Label("autoSpan", "Auto")
    private val directSpan = Label("directSpan", "Direct")
    private val firstSpan = Label("firstSpan", "First")
    private val secondSpan = Label("secondSpan", "Second")
    private val autoSpanAttribute = Label("autoSpanAttribute", "Auto")
    private val directSpanAttribute = Label("directSpanAttribute", "Direct")
    private val firstSpanAttribute = Label("firstSpanAttribute", "First")
    private val secondSpanAttribute = Label("secondSpanAttribute", "Second")

    override fun onInitialize() {
        super.onInitialize()

        add(autoSpan)
        add(directSpan)
        add(WebMarkupContainer("twoSpans").apply {
            add(firstSpan)
            add(secondSpan)
        })
        add(autoSpanAttribute)
        add(directSpanAttribute)
        add(WebMarkupContainer("twoSpansAttribute").apply {
            add(firstSpanAttribute)
            add(secondSpanAttribute)
        })

        add(htmlResourceLink("enclosuresPageMarkupLink", EnclosuresPage::class))
    }

    override fun onConfigure() {
        super.onConfigure()

        autoSpan.isVisible = Random.nextBoolean()
        directSpan.isVisible = Random.nextBoolean()
        secondSpan.isVisible = Random.nextBoolean()
        autoSpanAttribute.isVisible = Random.nextBoolean()
        directSpanAttribute.isVisible = Random.nextBoolean()
        secondSpanAttribute.isVisible = Random.nextBoolean()
    }
}
