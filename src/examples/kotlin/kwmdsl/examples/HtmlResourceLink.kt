package kwmdsl.examples

import org.apache.wicket.markup.html.link.ResourceLink
import org.apache.wicket.request.resource.PackageResourceReference
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Locale
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

fun htmlResourceLink(id: String, componentClass: KClass<*>, locale: Locale): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}_$locale.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}

fun htmlResourceLinkStyle(id: String, componentClass: KClass<*>, style: String): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}_$style.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}

fun htmlResourceLinkVariation(id: String, componentClass: KClass<*>, variation: String): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}_$variation.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}

fun htmlResourceLinkStyleAndVariation(id: String, componentClass: KClass<*>, style: String, variation: String): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}_${variation}_$style.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}

fun htmlResourceLinkStyleAndVariation(id: String, componentClass: KClass<*>, style: String, variation: String, locale: Locale): ResourceLink<Unit> {
    return ResourceLink<Unit>(
        id,
        object : PackageResourceReference(componentClass.java, "${componentClass.simpleName}_${variation}_${style}_$locale.html") {
            override fun getResource() =
                super.getResource()
                    .apply {
                        textEncoding = UTF_8.name()
                    }
        }
    )
}
