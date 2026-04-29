package kwmdsl.examples

import com.squins.kwmdsl.MarkupBuilder
import com.squins.kwmdsl.a
import com.squins.kwmdsl.attrHref
import com.squins.kwmdsl.div
import com.squins.kwmdsl.hr
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.html.link.ResourceLink
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

fun MarkupBuilder<*>.firstSourceCodeLink(companion: Any) {
    hr()
    sourceCodeLink(classSourceFilename(companion))
}

fun MarkupBuilder<*>.sourceCodeLink(companion: Any) {
    sourceCodeLink(classSourceFilename(companion))
}

fun MarkupBuilder<*>.firstSourceCodeLink(kClass: KClass<*>) {
    hr()
    sourceCodeLink("${kClass.simpleName}.kt")
}

fun MarkupBuilder<*>.sourceCodeLink(kClass: KClass<*>) {
    sourceCodeLink("${kClass.simpleName}.kt")
}

fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.sourceMarkupLink(kClass: KClass<*>, supplier: KProperty1<TSupplier, ResourceLink<*>>) {
    resourceLink("${kClass.simpleName}.html", supplier)
}

private fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.resourceLink(sourceFilename: String, supplier: KProperty1<TSupplier, ResourceLink<*>>) {
    div {
        a(supplier) {
            text("Source code of ")
            text(sourceFilename)
        }
    }
}

private fun MarkupBuilder<*>.sourceCodeLink(sourceFilename: String) {
    div {
        wicketLink {
            a(attrHref(sourceFilename)) {
                text("Source code of ")
                text(sourceFilename)
            }
        }
    }
}
