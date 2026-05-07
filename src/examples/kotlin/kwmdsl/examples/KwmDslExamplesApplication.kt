package kwmdsl.examples

import org.apache.wicket.markup.head.CssHeaderItem
import org.apache.wicket.markup.head.MetaDataHeaderItem
import org.apache.wicket.markup.head.MetaDataHeaderItem.META_TAG
import org.apache.wicket.markup.html.SecurePackageResourceGuard
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.request.resource.PackageResourceReference

class KwmDslExamplesApplication : WebApplication() {
    override fun getHomePage() = THE_HOME_PAGE.java

    override fun init() {
        super.init()

        headerContributorListeners.add { response ->
            response.render(META_DEVICE_WIDTH_INITIAL_SCALE_1)
            response.render(BULMA_ITEM)
            response.render(EXAMPLES_ITEM)
        }
        resourceSettings.packageResourceGuard = object : SecurePackageResourceGuard() {
            // Always allow HTML so the source markup can be shown.
            override fun accept(path: String) =
                path.endsWith(".html") || super.accept(path)
        }
            .apply {
                addPattern("+*.kt")
            }
    }
}

val THE_HOME_PAGE = ExamplesListPage::class

private val BULMA_REFERENCE = PackageResourceReference(KwmDslExamplesApplication::class.java, "bulma.min.css")
private val BULMA_ITEM = CssHeaderItem.forReference(BULMA_REFERENCE)

private val EXAMPLES_REFERENCE = PackageResourceReference(KwmDslExamplesApplication::class.java, "examples.css")
private val EXAMPLES_ITEM = CssHeaderItem.forReference(EXAMPLES_REFERENCE)

private val META_DEVICE_WIDTH_INITIAL_SCALE_1 = MetaDataHeaderItem(META_TAG).addTagAttribute("name", "viewport")
    .addTagAttribute("content", "width=device-width, initial-scale=1")
