plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    config = files("${rootProject.projectDir}/config/detekt/detekt.yml")

    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = true
    }
}
