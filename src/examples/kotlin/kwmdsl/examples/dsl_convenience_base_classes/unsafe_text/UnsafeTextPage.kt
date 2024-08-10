package kwmdsl.examples.dsl_convenience_base_classes.unsafe_text

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.firstSourceCodeLink

class UnsafeTextPage : ExamplesConvenienceBasePage() {
    override fun onInitialize() {
        super.onInitialize()

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup<S> {
// @formatter:off
wicketExtend {
    classDiv("content") {
        h2{ text("Unsafe Text") }
        //language=html
        unsafeText("""
        <p>
            If you have large pieces of rich text content (for example the <a href="https://nightlies.apache.org/wicket/guide/10.x/single.html">Wicket Reference Guide</a>), the DSL is not convenient: the code becomes verbose, and hard to read and modify. Compare how <strong>this section</strong> is written, with the <em>other section</em>.
        </p>
        
        <p>
            For writing rich text content you can use the function <code>unsafeText(String)</code>. In IntelliJ IDEA you can use a <a href="https://www.jetbrains.com/help/idea/using-language-injections.html">language injection</a> comment to get HTML editing support: <code>//language=html</code>.
        </p>
        """)

        h2{ text("DSL") }
        p {
            text("If you have large pieces of rich text content (for example the "); a(attr("href", "https://nightlies.apache.org/wicket/guide/10.x/single.html")) { text("Wicket Reference Guide") }; text("), the DSL is not convenient: the code becomes verbose, and hard to read and modify. Compare how "); strong { text("this section") }; text(" is written, with the "); em { text("other section") }; text(".")
        }

        p {
            text("For writing rich text content you can use the function "); code { text("unsafeText(String)") }; text(". In IntelliJ IDEA you can use a "); a(attr("href", "https://www.jetbrains.com/help/idea/using-language-injections.html")) { text("language injection") }; text(" comment to get HTML editing support: "); code { text("//language=html") }; text(".")
        }
    }
    firstSourceCodeLink(this@Companion)
}
// @formatter:on
        }
    }
}

// S stands for 'supplier'
private typealias S = UnsafeTextPage
