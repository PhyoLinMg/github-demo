package com.google.samples.apps.nowinandroid


import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.util.Properties

internal fun Project.configureSharedBuildConfig(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
){
    val properties = project.rootProject.file("local.properties")
        .inputStream()
        .use { Properties().apply { load(it) } }
    commonExtension.apply {
        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            getByName("debug") {
                buildConfigField("String", "GITHUB_TOKEN", "\"${properties.getProperty("GITHUB_TOKEN")}\"")
                buildConfigField("String", "API_URL","\"https://api.github.com/\"")

            }
            getByName("release") {
                buildConfigField("String", "GITHUB_TOKEN", "\"${properties.getProperty("GITHUB_TOKEN")}\"")
                buildConfigField("String", "API_URL","\"https://api.github.com/\"")
            }
        }
    }
}