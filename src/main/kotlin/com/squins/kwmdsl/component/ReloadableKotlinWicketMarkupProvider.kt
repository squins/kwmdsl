package com.squins.kwmdsl.component

import com.squins.kwmdsl.IRootMarkup
import org.apache.wicket.Application

// TODO("Decide if it is worthwhile to keep this")
abstract class ReloadableKotlinWicketMarkupProvider<TRootMarkup : IRootMarkup> : IKotlinWicketMarkupProvider {
    private val productionMarkup by lazy {
        createDslMarkup()
    }

    override val dslMarkup: TRootMarkup
        get() = if (Application.exists() && Application.get().usesDevelopmentConfig()) {
            createDslMarkup()
        } else {
            productionMarkup
        }

    abstract fun createDslMarkup(): TRootMarkup
}
