package kwmdsl.examples.dsl_convenience_base_classes.unsafe_text

import com.squins.kwmdsl.a
import com.squins.kwmdsl.attrClass
import com.squins.kwmdsl.attrHref
import com.squins.kwmdsl.code
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import com.squins.kwmdsl.component.linkPath
import com.squins.kwmdsl.div
import com.squins.kwmdsl.em
import com.squins.kwmdsl.h1
import com.squins.kwmdsl.h2
import com.squins.kwmdsl.markup
import com.squins.kwmdsl.p
import com.squins.kwmdsl.strong
import kwmdsl.examples.ExamplesConvenienceBasePage
import kwmdsl.examples.ExamplesListPage
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
    div(attrClass("content")) {
        h1 { text("Unsafe text") }

        //language=html
        unsafeText("""
        <h2>Unsafe Text</h2>
        <p>
            If you have large pieces of rich text content (for example the <a href="https://nightlies.apache.org/wicket/guide/9.x/single.html">Wicket Reference Guide</a>), the DSL is not convenient: the code becomes verbose, and hard to read and modify. Compare how <strong>this section</strong> is written, with the <em>other section</em>.
        </p>
        
        <p>
            For writing rich text content you can use the function <code>unsafeText(String)</code>. In IntelliJ IDEA you can use a <a href="https://www.jetbrains.com/help/idea/using-language-injections.html">language injection</a> comment to get HTML editing support: <code>//language=html</code>.
        </p>
        
        <p>
            If you are careful, and you do need some Wicket components within text content, you can mix unsafe text and regular markup. Here is a link to the 
        """)
        wicketLink { a(attrHref(ExamplesListPage::class.linkPath())) { text("examples list page") } }
        //language=html
        unsafeText(""", for example. But it is probably wiser to write an HTML element fully in either markup or unsafe text.
        </p>
        
        <p>
            <strong class="has-text-warning">WARNING</strong>: as always with unsafe constructs, you are responsible for ensuring the final markup is valid and does not contain vulnerabilities. 
        </p>
        """)

        h2{ text("DSL") }
        p {
            text("If you have large pieces of rich text content (for example the "); a(attrHref("https://nightlies.apache.org/wicket/guide/9.x/single.html")) { text("Wicket Reference Guide") }; text("), the DSL is not convenient: the code becomes verbose, and hard to read and modify. Compare how "); strong { text("this section") }; text(" is written, with the "); em { text("other section") }; text(".")
        }

        p {
            text("For writing rich text content you can use the function "); code { text("unsafeText(String)") }; text(". In IntelliJ IDEA you can use a "); a(attrHref("https://www.jetbrains.com/help/idea/using-language-injections.html")) { text("language injection") }; text(" comment to get HTML editing support: "); code { text("//language=html") }; text(".")
        }

        p {
            text("If you are careful, and you do need some Wicket components within text content, you can mix unsafe text and regular markup. Here is a link to the "); wicketLink { a(attrHref(ExamplesListPage::class.linkPath())) { text("examples list page") } }; text(", for example. But it is probably wiser to write an HTML element fully in either markup or unsafe text.")
        }

        p {
            strong(attrClass("has-text-warning")) { text("WARNING") }; text(": as always with unsafe constructs, you are responsible for ensuring the final markup is valid and does not contain vulnerabilities.")
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
