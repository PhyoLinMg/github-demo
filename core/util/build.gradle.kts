plugins {
    alias(libs.plugins.github.android.library)
}
android {
    namespace="dev.linmaung.core.util"
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
    implementation(libs.bundles.coroutines)

}
