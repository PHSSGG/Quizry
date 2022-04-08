buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://kotlin.bintray.com/kotlinx")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.android.tools.build:gradle:7.0.4")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://kotlin.bintray.com/kotlinx")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}