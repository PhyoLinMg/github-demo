plugins {
    alias(libs.plugins.github.android.feature)
    alias(libs.plugins.github.android.library.compose)
}

android {
    namespace = "dev.linmaung.github.user"
}
dependencies{

    implementation(project(":network"))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)

    implementation(libs.androidx.paging.compose)
    implementation(project(":core:util"))
    implementation(project(":core:presentation"))
    implementation(project(":core:domain"))

}