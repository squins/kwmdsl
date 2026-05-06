package kwmdsl.examples

import org.apache.wicket.markup.html.link.ResourceLink
import org.apache.wicket.request.resource.PackageResourceReference
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.reflect.KClass

fun htmlResourceLink(id: String, componentClass: KClass<*>): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}
