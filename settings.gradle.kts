rootProject.name = "kspigot-gradle-template"

pluginManagement {
    val kotlin_version: String by settings
    val shadowjar_version: String by settings

    plugins {
        kotlin("jvm") version kotlin_version
        id("com.github.johnrengelman.shadow") version shadowjar_version
    }
}