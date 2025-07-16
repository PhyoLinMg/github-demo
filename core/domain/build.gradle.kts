plugins {
    alias(libs.plugins.github.android.library)
    id("kotlinx-serialization")
}

android {
    namespace = "dev.linmaung.github.core.domain"
}

dependencies {
    api(project(":core:util"))
}
