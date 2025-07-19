plugins {
    alias(libs.plugins.github.android.library)
    alias(libs.plugins.github.android.library.compose)
}
android {
    namespace = "dev.linmaung.github.core.presentation"
}

dependencies {
    api(project(":core:data"))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.browser)
}

