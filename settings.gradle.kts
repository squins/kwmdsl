pluginManagement {
    plugins {
        // https://kotlinlang.org/docs/home.html
        kotlin("jvm") version "2.0.0"

        // https://plugins.gradle.org/plugin/org.gradle.toolchains.foojay-resolver-convention
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"

        // https://plugins.gradle.org/plugin/org.jetbrains.dokka
        id("org.jetbrains.dokka") version "1.9.20"
    }

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention")
}

// https://wicket.apache.org/start/download.html
val wicketVersion = "10.0.0"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("wicketCore", "org.apache.wicket:wicket-core:$wicketVersion")
        }
    }
}

rootProject.name = "kotlin-wicket-markup-dsl"
