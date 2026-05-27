import java.io.PrintWriter
import java.util.Locale

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.google.cloud.artifactregistry.gradle-plugin")
    id("org.jetbrains.dokka")
    id("org.jetbrains.dokka-javadoc")
}

group = "com.squins"
version = "4-SNAPSHOT"

tasks.wrapper {
    // https://gradle.org/releases/
    gradleVersion = "8.14.4"
    distributionType = Wrapper.DistributionType.ALL
}

publishing {
    repositories {
        maven {
            setUrl("artifactregistry://europe-west4-maven.pkg.dev/squins-ci/squins-components-dwm8a3suqkvq")
        }
    }
}

repositories {
    mavenCentral()
    maven {
        setUrl("artifactregistry://europe-west4-maven.pkg.dev/squins-ci/squins-components-dwm8a3suqkvq")
    }
}

sourceSets.register("examples") {
    resources {
        srcDir("src/examples/kotlin")
    }
}

val convenienceFunctionsSourceDirectory =
    File(project.layout.buildDirectory.get().asFile, "generated/main/convenience")

val generateConvenienceFunctions by tasks.registering {
    inputs.property("voidElements", voidHtmlElements)
    inputs.property("nonVoidElements", nonVoidHtmlElements)
    val outputDirectory = convenienceFunctionsSourceDirectory
    outputs.dir(outputDirectory)

    doLast {
        val packageDirectory = File(outputDirectory, "com/squins/kwmdsl")
        packageDirectory.mkdirs()

        fun PrintWriter.receiverDoc() {
            println(" * @receiver a markup builder.")
        }

        fun PrintWriter.supplierDoc() {
            println(" * @param TSupplier the markup container type having the properties and functions to get the Wicket components.")
        }

        fun PrintWriter.blockDoc() {
            println(" * @param block the (optional) code for building the children of the element.")
        }

        fun PrintWriter.blockDeclaration() {
            println("    block: (MarkupBuilder<TSupplier>.() -> Unit)? = null")
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
                    println("import kotlin.reflect.KProperty1")
                    println()

                    fun wicketIdDoc() {
                        println(" * @param wicketId the Wicket ID to assign to the element. **Warning**: there is no validation and no escaping, so make sure the ID is valid and safe.")
                    }

                    fun wicketIdDeclaration() {
                        println("    wicketId: String,")
                    }

                    fun repeatedDoc() {
                        println(" * @param repeated indicates that the component of this element will be added in a repeater. The Wicket ID of `repeated` is assigned to the element.")
                    }

                    fun repeatedDeclaration() {
                        println("    repeated: Repeated,")
                    }

                    fun propertySupplierDoc() {
                        println(" * @param supplier the property that will be used to determine the Wicket ID to assign to the element, and to retrieve the Wicket component when the root markup is added to the markup container.")
                    }

                    fun propertySupplierDeclaration() {
                        println("    supplier: KProperty1<TSupplier, Component>,")
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

                    fun repeatedElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with the Wicket ID of the given supplier. The component of the element will be added during the population of the items of a repeater. It is the responsibility of the markup container to add a Wicket component with the same ID manually.")
                        println(" *")
                    }

                    fun supplierPropertyElementDocStart(elementName: String) {
                        println("/**")
                        println(" * Add ${aOrAn(elementName)} `$elementName` element with the Wicket ID equal to the name of the supplier property. The Wicket component will be retrieved, by getting the value of the supplier property, and added to the markup container when the root markup is added to the markup container.")
                        println(" *")
                    }

                    voidHtmlElements.forEach { elementName ->
                        freeElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$elementName(")
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement("$elementName", *attributes)""")
                        println()
                        wicketIdElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        wicketIdDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$elementName(")
                        wicketIdDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement("$elementName", attr("wicket:id", wicketId), *attributes)""")
                        println()
                        repeatedElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        repeatedDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$elementName(")
                        repeatedDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement("$elementName", attr("wicket:id", repeated.wicketId), *attributes)""")
                        println()
                        supplierPropertyElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        propertySupplierDoc()
                        attributesDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$elementName(")
                        propertySupplierDeclaration()
                        attributesDeclaration()
                        println(") =")
                        println("""    voidElement(supplier, "$elementName", *attributes)""")
                        println()
                        println()
                        println()
                    }

                    nonVoidHtmlElements.forEach { elementName ->
                        val functionName = if (elementName in elementNamesToQuote) "`$elementName`" else elementName
                        freeElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$functionName(")
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", *attributes, block = block)""")
                        println()
                        wicketIdElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        wicketIdDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$functionName(")
                        wicketIdDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", attr("wicket:id", wicketId), *attributes, block = block)""")
                        println()
                        repeatedElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        repeatedDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$functionName(")
                        repeatedDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element("$elementName", attr("wicket:id", repeated.wicketId), *attributes, block = block)""")
                        println()
                        supplierPropertyElementDocStart(elementName)
                        receiverDoc()
                        supplierDoc()
                        propertySupplierDoc()
                        attributesDoc()
                        blockDoc()
                        docEnd()
                        println("fun <TSupplier : MarkupContainer> MarkupBuilder<TSupplier>.$functionName(")
                        propertySupplierDeclaration()
                        attributesDeclaration()
                        blockDeclaration()
                        println(") =")
                        println("""    element(supplier, "$elementName", *attributes, block = block)""")
                        println()
                        println()
                        println()
                    }
                }
            }
        }
    }
}

kotlin {
    sourceSets {
        named("main") {
            kotlin.srcDir(generateConvenienceFunctions)
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
    "examplesImplementation"(libs.serialysis)

    testImplementation(kotlin("test"))
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

java {
    withSourcesJar()
}

dokka {
    dokkaSourceSets.main {
        suppressGeneratedFiles.set(false)
    }
}

val dokkaHtmlJar by tasks.registering(Jar::class) {
    group = "documentation"
    dependsOn(tasks.dokkaGeneratePublicationHtml)
    from(tasks.dokkaGeneratePublicationHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

val dokkaJavadocJar by tasks.registering(Jar::class) {
    group = "documentation"
    dependsOn(tasks.dokkaGeneratePublicationJavadoc)
    from(tasks.dokkaGeneratePublicationJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("main") {
            pom {
                name.set("kwmdsl")
                description.set("Kotlin DSL for writing Wicket markup.")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://mit-license.org/")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/squins/kwmdsl.git")
                    developerConnection.set("scm:git:https://github.com/squins/kwmdsl.git")
                    url.set("https://github.com/squins/kwmdsl/")
                }
                developers {
                    developer {
                        id.set("jstuyts")
                        name.set("Johan Stuyts")
                        email.set("j.stuyts@javathinker.com")
                    }
                    developer {
                        id.set("keesvandieren")
                        name.set("Kees van Dieren")
                        email.set("keesvandieren@squins.com")
                    }
                }
            }

            artifactId = base.archivesName.get()
            from(components["java"])

            artifact(dokkaHtmlJar)
            artifact(dokkaJavadocJar)
        }
    }
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

private val LETTERS_REQUIRING_AN = setOf(
    'a',
    'e',
    'i',
    'o',
)

private fun aOrAn(word: String) =
    if (word.lowercase(Locale.ENGLISH)[0] in LETTERS_REQUIRING_AN) "an" else "a"
