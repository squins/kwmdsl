pluginManagement {
    plugins {
        // Three years back: https://kotlinlang.org/docs/releases.html#kotlin-release-compatibility
        kotlin("jvm") version "1.5.0"

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

// https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-server
val jettyVersion = "12.0.12"
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
val logbackVersion = "1.5.7"
// https://mvnrepository.com/artifact/io.github.eamonnmcmanus/serialysis
val serialysisVersion = "0.9"
// https://wicket.apache.org/start/download.html
val wicketVersion = "8.0.0"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("jettyEe10Servlet", "org.eclipse.jetty.ee10:jetty-ee10-servlet:$jettyVersion")
            library("jettyEe8Servlet", "org.eclipse.jetty.ee8:jetty-ee8-servlet:$jettyVersion")
            library("jettyServer", "org.eclipse.jetty:jetty-server:$jettyVersion")
            library("logbackClassic", "ch.qos.logback:logback-classic:$logbackVersion")
            library("serialysis", "io.github.eamonnmcmanus:serialysis:$serialysisVersion")
            library("wicketCore", "org.apache.wicket:wicket-core:$wicketVersion")
        }
    }
}

rootProject.name = "kotlin-wicket-markup-dsl"
