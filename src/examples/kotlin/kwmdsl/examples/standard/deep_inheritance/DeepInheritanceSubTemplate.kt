package kwmdsl.examples.standard.deep_inheritance

import kwmdsl.examples.htmlResourceLink
import org.apache.wicket.markup.html.basic.Label

open class DeepInheritanceSubTemplate : DeepInheritanceBaseTemplate() {
    override fun onInitialize() {
        super.onInitialize()

        add(Label("subTemplateLabel", "Deep inheritance, sub template component"))

        add(htmlResourceLink("deepInheritanceBasePageMarkupLink", DeepInheritanceBasePage::class))
        add(htmlResourceLink("deepInheritanceBaseTemplateMarkupLink", DeepInheritanceBaseTemplate::class))
        add(htmlResourceLink("deepInheritanceSubPageMarkupLink", DeepInheritanceSubPage::class))
        add(htmlResourceLink("deepInheritanceSubTemplateMarkupLink", DeepInheritanceSubTemplate::class))
    }
}
