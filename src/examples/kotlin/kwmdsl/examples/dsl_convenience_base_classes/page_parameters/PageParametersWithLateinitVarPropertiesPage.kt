package kwmdsl.examples.dsl_convenience_base_classes.page_parameters

import com.squins.kwmdsl.*
import com.squins.kwmdsl.component.IKotlinWicketMarkupProvider
import kwmdsl.examples.ExamplesConvenienceBasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.model.LoadableDetachableModel

class PageParametersWithLateinitVarPropertiesPage : ExamplesConvenienceBasePage() {
    private lateinit var text: Label

    override fun onInitialize() {
        super.onInitialize()

        val modelUsingAPageParameter = object : LoadableDetachableModel<String>() {
            override fun load() = pageParameters.get("text").toString("Hello, world!").reversed()
        }
        text = Label(::text.name, modelUsingAPageParameter)

        noVariantMarkup.addTo(this)
    }

    companion object : IKotlinWicketMarkupProvider {
        override val noVariantMarkup = markup {
            wicketExtend {
                classH1("title") { text("Components Defined by Lateinit Var Properties Accessing Page Parameters") }

                classDiv("content") {
                    p {
                        text("This page does not have a constructor accepting page parameters, so it must create the components after Wicket has set the page parameters: in ")
                        code { text("onInitialize()") }
                        text(". ")
                    }

                    p {
                        text("This is the reverse of the text you sent me: ")
                        span(PageParametersWithLateinitVarPropertiesPage::text)
                    }

                    hr()

                    p {
                        text("This is also the pattern to use when you have to decide if the page can be shown before expensive initialization takes place:")
                    }

                    pre {
                        text("""fun onInitialize() {
    super.onInitialize()
    
    // Check if page can be shown, throw a sub class of `ReplaceHandlerException` if not
    
    // Do the (possibly expensive) initialization
}""")
                    }
                }
            }
        }
    }
}
