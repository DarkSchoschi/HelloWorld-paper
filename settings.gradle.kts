rootProject.name = "join-quit-message"

pluginManagement {
    val kotlin_version: String by settings
    val shadowjar_version: String by settings

    plugins {
        kotlin("jvm") version kotlin_version
        id("com.github.johnrengelman.shadow") version shadowjar_version
    }
}