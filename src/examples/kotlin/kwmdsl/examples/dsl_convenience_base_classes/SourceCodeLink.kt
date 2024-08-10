package kwmdsl.examples.dsl_convenience_base_classes

import com.squins.kwmdsl.*
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
