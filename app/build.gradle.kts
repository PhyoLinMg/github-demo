import com.google.samples.apps.nowinandroid.GithubBuildType
import java.util.Properties

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


plugins {
    alias(libs.plugins.github.android.application)
    alias(libs.plugins.github.android.application.compose)
    alias(libs.plugins.github.android.application.flavors)
    alias(libs.plugins.github.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig {
        applicationId = "dev.linmaung.github"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    }
    buildFeatures{
        buildConfig= true
    }
    buildTypes {
        debug {
            applicationIdSuffix = GithubBuildType.DEBUG.applicationIdSuffix

        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = GithubBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.

            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.

        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "dev.linmaung.github"
}

dependencies {


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.material.iconsExtended)

    implementation(libs.androidx.compose.foundation)

    implementation(libs.coil.kt.compose)
    implementation(project(":features:user"))
    implementation(project(":features:repo"))
    implementation(project(":core:util"))

    ksp(libs.hilt.compiler)
//
//    debugImplementation(libs.androidx.compose.ui.testManifest)
//    debugImplementation(projects.uiTestHiltManifest)
//
//    kspTest(libs.hilt.compiler)
//
//    testImplementation(projects.core.dataTest)
//    testImplementation(projects.core.datastoreTest)
//    testImplementation(libs.hilt.android.testing)
//    testImplementation(projects.sync.syncTest)
//    testImplementation(libs.kotlin.test)
//
//    testDemoImplementation(libs.androidx.navigation.testing)
//    testDemoImplementation(libs.robolectric)
//    testDemoImplementation(libs.roborazzi)
//    testDemoImplementation(projects.core.screenshotTesting)
//    testDemoImplementation(projects.core.testing)
//
//    androidTestImplementation(projects.core.testing)
//    androidTestImplementation(projects.core.dataTest)
//    androidTestImplementation(projects.core.datastoreTest)
//    androidTestImplementation(libs.androidx.test.espresso.core)
//    androidTestImplementation(libs.androidx.compose.ui.test)
//    androidTestImplementation(libs.hilt.android.testing)
//    androidTestImplementation(libs.kotlin.test)


}


