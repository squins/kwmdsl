import org.jetbrains.dokka.gradle.DokkaTask
import java.io.PrintWriter
import java.util.Locale

plugins {
    kotlin("jvm") version "2.0.0"
    id("org.jetbrains.dokka")
}

group = "com.squins"
version = "1-SNAPSHOT"

repositories {
    mavenCentral()
}

val examples by sourceSets.registering {
    resources {
        srcDir("src/examples/kotlin")
        exclude("**/*.kt")
    }
}

val convenienceFunctionsSourceDirectory =
    File(project.layout.buildDirectory.get().getAsFile(), "generated/main/convenience")

val generateConvenienceFunctions by tasks.registering {
    inputs.property("voidElements", voidHtmlElements)
    inputs.property("nonVoidElements", nonVoidHtmlElements)
    val outputDirectory = convenienceFunctionsSourceDirectory
    outputs.dir(outputDirectory)

    doLast {
        val packageDirectory = File(outputDirectory, "com/squins/kwmdsl")
        packageDirectory.mkdirs()

        fun PrintWriter.supplierFacadeDoc() {
            println(" * @param TSupplierFacade the markup container type having the properties and functions to get the Wicket components.")
        }

        fun PrintWriter.blockDoc() {
            println(" * @param block the (optional) code for building the children of the element.")
        }

        fun PrintWriter.blockDeclaration() {
            println("    block: (MarkupBuilder<TSupplierFacade>.() -> Unit)? = null")
        }

        fun PrintWriter.docEnd() {
            println(" */")
        }

        val htmlElementsFile = File(packageDirectory, "HtmlElements.kt")
        htmlElementsFile.writer().use { fileWriter ->
            PrintWriter(fileWriter).use { printWriter ->
                with(printWriter) {
                    println("package com.squins.kwmdsl")
                    println()
                    println("import org.apache.wicket.Component")
                    println("import org.apache.wicket.MarkupContainer")
                    println("import kotlin.reflect.KFunction1")
                    println("import kotlin.reflect.KProperty1")
                    println()

                    fun wicketIdDoc() {
                        println(" * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.")
                    }

                    fun wicketIdDeclaration() {
                        println("    wicketId: String,")
                    }

                    fun functionSupplierDoc() {
                        println(" * @param supplier the function that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.")
                    }

                    fun functionSupplierDeclaration() {
                        println("    supplier: KFunction1<TSupplierFacade, Component>,")
                    }

                    fun propertySupplierDoc() {
                        println(" * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.")
                    }

                    fun propertySupplierDeclaration() {
                        println("    supplier: KProperty1<TSupplierFacade, Component>,")
                    }

                    fun attributesDoc() {
                        println(" * @param attributes the (optional) attributes to add: pairs of attribute name and attribute value. **Warning**: there is no validation and no escaping, so make sure the names and values are valid and safe.")
                    }

                    fun attributesDeclaration() {
                        println("    vararg attributes: Pair<String, AttributeValue>,")
                    }

                    fun freeElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element without a Wicket component.")
                        println(" *")
                    }

                    fun wicketIdElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with the given Wicket ID. It is the responsibility of the markup container to add a Wicket component with the same ID manually.")
                        println(" *")
                        println(" * This should only be used if it is not possible to use a supplier function or property. For example, when overriding the markup of a base class.")
                        println(" *")
                    }

                    fun supplierFunctionElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with the Wicket ID equal to the name of the supplier function. The Wicket component will be retrieved, by invoking the supplier function, and added to the markup container when the root markup is added to the markup container.")
                        println(" *")
                    }

                    fun supplierPropertyElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with the Wicket ID equal to the name of the supplier property. The Wicket component will be retrieved, by getting the value of the supplier property, and added to the markup container when the root markup is added to the markup container.")
                        println(" *")
                    }

                    voidHtmlElements.forEach { elementName ->
                        freeElementDocStart(elementName)
                        supplierFacadeDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$elementName(")
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement("$elementName", attributes)""")
                        println()
                        wicketIdElementDocStart(elementName)
                        supplierFacadeDoc()
                        wicketIdDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$elementName(")
                        wicketIdDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement("$elementName", arrayOf(attr("wicket:id", wicketId), *attributes))""")
                        println()
                        supplierFunctionElementDocStart(elementName)
                        supplierFacadeDoc()
                        functionSupplierDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$elementName(")
                        functionSupplierDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement(supplier, "$elementName", attributes)""")
                        println()
                        supplierPropertyElementDocStart(elementName)
                        supplierFacadeDoc()
                        propertySupplierDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$elementName(")
                        propertySupplierDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement(supplier, "$elementName", attributes)""")
                        println()
                        println()
                        println()
                    }

                    nonVoidHtmlElements.forEach { elementName ->
                        val functionName = if (elementName in elementNamesToQuote) "`$elementName`" else elementName
                        freeElementDocStart(elementName)
                        supplierFacadeDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$functionName(")
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", attributes, block)""")
                        println()
                        wicketIdElementDocStart(elementName)
                        supplierFacadeDoc()
                        wicketIdDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$functionName(")
                        wicketIdDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", arrayOf(attr("wicket:id", wicketId), *attributes), block)""")
                        println()
                        supplierFunctionElementDocStart(elementName)
                        supplierFacadeDoc()
                        propertySupplierDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$functionName(")
                        functionSupplierDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element(supplier, "$elementName", attributes, block)""")
                        println()
                        supplierPropertyElementDocStart(elementName)
                        supplierFacadeDoc()
                        propertySupplierDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.$functionName(")
                        propertySupplierDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element(supplier, "$elementName", attributes, block)""")
                        println()
                        println()
                        println()
                    }
                }
            }
        }

        val classHtmlElementsFile = File(packageDirectory, "ClassHtmlElements.kt")
        classHtmlElementsFile.writer().use { fileWriter ->
            PrintWriter(fileWriter).use { printWriter ->
                with(printWriter) {
                    println("package com.squins.kwmdsl")
                    println()
                    println("import org.apache.wicket.MarkupContainer")
                    println()

                    classElements.forEach { elementName ->
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with a `class` attribute having the value of `cssClasses`.")
                        println(" *")
                        supplierFacadeDoc()
                        println(" * @param cssClasses the value for attribute `class`. **Warning**: there is no validation and no escaping, so make sure the value is valid and safe.")
                        blockDoc()
                        docEnd()
                        println("fun <TSupplierFacade : MarkupContainer> MarkupBuilder<TSupplierFacade>.class${elementName[0].uppercase()}${elementName.substring(1)}(")
                        println("    cssClasses: String,")
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", arrayOf(attr("class", cssClasses)), block)""")
                        println()
                        println()
                        println()
                    }
                }
            }
        }
    }
}

sourceSets {
    main {
        kotlin {
            srcDir(generateConvenienceFunctions)
        }
    }
}

configurations.named("examplesImplementation") {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    api(libs.wicketCore)
    implementation(kotlin("reflect"))

    "examplesImplementation"(sourceSets.main.get().output)
    "examplesImplementation"(libs.jettyEe8Servlet)
    "examplesImplementation"(libs.jettyServer)
    "examplesImplementation"(libs.logbackClassic)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets.named("main") {
        suppressGeneratedFiles.set(false)
    }
}

tasks.register<Jar>("dokkaHtmlJar") {
    group = "documentation"
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

tasks.register<Jar>("dokkaJavadocJar") {
    group = "documentation"
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

private val voidHtmlElements = listOf(
    "area",
    "base",
    "br",
    "col",
    "embed",
    "hr",
    "img",
    "input",
    "link",
    "meta",
    "source",
    "track",
    "wbr",
)

private val nonVoidHtmlElements = listOf(
    "a",
    "abbr",
    "address",
    "article",
    "aside",
    "audio",
    "b",
    "bdi",
    "bdo",
    "blockquote",
    "body",
    "button",
    "canvas",
    "caption",
    "cite",
    "code",
    "colgroup",
    "data",
    "datalist",
    "dd",
    "del",
    "details",
    "dfn",
    "dialog",
    "div",
    "dl",
    "dt",
    "em",
    "fencedframe",
    "fieldset",
    "figcaption",
    "figure",
    "footer",
    "form",
    "h1",
    "h2",
    "h3",
    "h4",
    "h5",
    "h6",
    "head",
    "header",
    "hgroup",
    "html",
    "i",
    "iframe",
    "ins",
    "kbd",
    "label",
    "legend",
    "li",
    "main",
    "map",
    "mark",
    "menu",
    "meter",
    "nav",
    "noscript",
    "object",
    "ol",
    "optgroup",
    "option",
    "output",
    "p",
    "picture",
    "portal",
    "pre",
    "progress",
    "q",
    "rp",
    "rt",
    "ruby",
    "s",
    "samp",
    "script",
    "search",
    "section",
    "select",
    "slot",
    "small",
    "span",
    "strong",
    "style",
    "sub",
    "summary",
    "sup",
    "table",
    "tbody",
    "td",
    "template",
    "textarea",
    "tfoot",
    "th",
    "thead",
    "time",
    "title",
    "tr",
    "u",
    "ul",
    "var",
    "video",
)

private val elementNamesToQuote = setOf(
    "object",
    "var",
)

private val classElements = listOf(
    "div",
    "span",
)

private val LETTERS_REQUIRING_AN = setOf(
    'a',
    'e',
    'i',
    'o',
)

private fun aOrAn(word: String) =
    if (word.lowercase(Locale.ENGLISH)[0] in LETTERS_REQUIRING_AN) "an" else "a"
