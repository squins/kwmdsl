package kwmdsl.examples

import org.apache.wicket.protocol.http.WebApplication

class KwmDslExamplesApplication : WebApplication() {
    override fun getHomePage() = THE_HOME_PAGE.java
}

val THE_HOME_PAGE = ExamplesListPage::class
