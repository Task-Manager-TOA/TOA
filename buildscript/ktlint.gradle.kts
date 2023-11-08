import org.jlleitschuh.gradle.ktlint.KtlintPlugin

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
}

configure<KtlintPlugin> {
    version.set("1.0.1")

    reporters {
        reporter("plain")
        reporter("checkstyle")
        reporter("html")
    }

    outputColorName.set("RED")
}
