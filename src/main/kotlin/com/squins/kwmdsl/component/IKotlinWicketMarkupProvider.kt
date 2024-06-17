package com.squins.kwmdsl.component

import com.squins.kwmdsl.RootMarkup

interface IKotlinWicketMarkupProvider {
    // TODO("Document why it is not called `markup`")
    val dslMarkup: RootMarkup<*>
}
