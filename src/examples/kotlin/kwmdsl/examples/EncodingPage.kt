package kwmdsl.examples

import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.div
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p

class EncodingPage : ExamplesConvenienceBasePage() {
    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<S> {
// @formatter:off
wicketExtend {
    div(attrClass("content")) {
        h1(attrClass("title")) { text("Wicket and the Default JVM Encoding") }

        p {
            text("If you did not see a happy face, system property ")
            code { text("file.encoding") }
            text(" is not set to ")
            code { text("UTF-8") }
            text(". Unicode characters in the markup will not be rendered properly.")
        }

        p {
            text("Wicket converts the markup strings to bytes and back before sending them to the browser. If the encoding is not set to UTF-8, this will result in data loss: question marks will appear instead of the intended characters. This applies to all Wicket applications, not just this example one showing how to use the DSL.")
        }

        p {
            text("You either have to add ")
            code { text("-Dfile.encoding=UTF-8") }
            text(" or remove ")
            code { text("-Dfile.encoding=COMPAT") }
            text(" from the JVM parameters.")
        }
        firstSourceCodeLink(this@Companion)
    }
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = EncodingPage
