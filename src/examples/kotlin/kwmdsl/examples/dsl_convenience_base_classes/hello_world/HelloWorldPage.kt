package kwmdsl.examples.dsl_convenience_base_classes.hello_world

import com.squins.kwmdsl.body
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.KotlinWicketMarkupWebPage
import com.squins.kwmdsl.html
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.span
import kwmdsl.examples.firstSourceCodeLink
import org.apache.wicket.markup.html.basic.Label

class HelloWorldPage : KotlinWicketMarkupWebPage<Unit>() {
    private val message: Label = Label(::message.name, "Hello World!")

    init {
        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
// @formatter:off
html {
    body {
        span(S::message) { text("Message goes here")}
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = HelloWorldPage
