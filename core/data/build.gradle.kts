
plugins {
    alias(libs.plugins.github.android.library)
    alias(libs.plugins.github.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "dev.linmaung.github.core.data"
}

dependencies {
    api(project(":core:domain"))
    implementation(libs.kotlinx.serialization.json)
}
