package kwmdsl.examples.standard.enclosure

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import kotlin.random.Random

class EnclosuresPage : WebPage() {
    private val autoSpan = Label("autoSpan", "Auto")
    private val directSpan = Label("directSpan", "Direct")
    private val firstSpan = Label("firstSpan", "First")
    private val secondSpan = Label("secondSpan", "Second")
    
    override fun onInitialize() {
        super.onInitialize()

        add(autoSpan)
        add(directSpan)
        add(WebMarkupContainer("twoSpans").apply {
            add(firstSpan)
            add(secondSpan)
        })
    }

    override fun onConfigure() {
        super.onConfigure()

        autoSpan.isVisible = Random.nextBoolean()
        directSpan.isVisible = Random.nextBoolean()
        secondSpan.isVisible = Random.nextBoolean()
    }
}
