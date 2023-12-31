// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version ("11.0.0")
    id("io.gitlab.arturbosch.detekt").version("1.23.3")
    id("com.google.dagger.hilt.android") version "2.46" apply false
}
