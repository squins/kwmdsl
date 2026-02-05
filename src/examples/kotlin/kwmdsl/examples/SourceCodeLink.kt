package kwmdsl.examples

import com.squins.kwmdsl.MarkupBuilder
import com.squins.kwmdsl.a
import com.squins.kwmdsl.attr
import com.squins.kwmdsl.div
import com.squins.kwmdsl.hr
import kotlin.reflect.KClass

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

private fun MarkupBuilder<*>.sourceCodeLink(sourceFilename: String) {
    div {
        wicketLink {
            a(attr("href", sourceFilename)) {
                text("Source code of ")
                text(sourceFilename)
            }
        }
    }
}
