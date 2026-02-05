pluginManagement {
    plugins {
        // Three years back: https://kotlinlang.org/docs/releases.html#kotlin-release-compatibility
        kotlin("jvm") version "1.8.0"

        // https://plugins.gradle.org/plugin/com.google.cloud.artifactregistry.gradle-plugin
        // Do not upgrade unless https://github.com/GoogleCloudPlatform/artifact-registry-maven-tools/issues/106 has been resolved.
        id("com.google.cloud.artifactregistry.gradle-plugin") version "2.2.3"

        // https://plugins.gradle.org/plugin/org.gradle.toolchains.foojay-resolver-convention
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"

        // https://plugins.gradle.org/plugin/org.jetbrains.dokka
        id("org.jetbrains.dokka") version "2.1.0"

        // https://plugins.gradle.org/plugin/org.jetbrains.dokka
        id("org.jetbrains.dokka-javadoc") version "2.1.0"
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
val jettyVersion = "12.1.6"
// https://mvnrepository.com/artifact/org.junit.platform/junit-platform-launcher
val junitPlatformLauncherVersion = "1.14.2"
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
val junitVersion = "5.14.2"
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
val logbackVersion = "1.5.27"
// https://mvnrepository.com/artifact/io.github.eamonnmcmanus/serialysis
val serialysisVersion = "0.9"
// https://wicket.apache.org/start/download.html
val wicketVersion = "9.0.0"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("jettyEe10Servlet", "org.eclipse.jetty.ee10:jetty-ee10-servlet:$jettyVersion")
            library("jettyEe8Servlet", "org.eclipse.jetty.ee8:jetty-ee8-servlet:$jettyVersion")
            library("jettyServer", "org.eclipse.jetty:jetty-server:$jettyVersion")
            library("logbackClassic", "ch.qos.logback:logback-classic:$logbackVersion")
            library("serialysis", "io.github.eamonnmcmanus:serialysis:$serialysisVersion")
            library("wicketCore", "org.apache.wicket:wicket-core:$wicketVersion")

            library("junitJupiter", "org.junit.jupiter:junit-jupiter:$junitVersion")
            library("junitPlatformLauncher", "org.junit.platform:junit-platform-launcher:$junitPlatformLauncherVersion")
        }
    }
}

rootProject.name = "kotlin-wicket-markup-dsl"
