plugins {
    alias(libs.plugins.github.android.library)
    alias(libs.plugins.github.hilt)
    alias(libs.plugins.github.buildconfig)
    id("kotlinx-serialization")

}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies{
    api(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
    api(libs.bundles.coroutines)
}
android{
    namespace="dev.linmaung.network"
}