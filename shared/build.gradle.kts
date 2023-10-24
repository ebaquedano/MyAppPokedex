plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.6.10"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val serializacionVersion = "2.0.0-beta-1"

        val commonMain by getting {
            dependencies {
                //Serializaction
                implementation("io.ktor:ktor-client-content-negotiation:$serializacionVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$serializacionVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.myapppokedex"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
}