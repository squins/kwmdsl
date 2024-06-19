package com.squins.kwmdsl.component

import com.squins.kwmdsl.IRootMarkup

interface IKotlinWicketMarkupProvider {
    // TODO("Document why it is not called `markup`")
    val dslMarkup: IRootMarkup
}
