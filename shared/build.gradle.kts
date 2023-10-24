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
        val ktorVersion = "2.0.0-beta-1"
        val coroutinesVersion = "1.6.0"

        val commonMain by getting {
            dependencies {
                //Serializaction modulo compartido
                implementation("io.ktor:ktor-client-content-negotiation:$serializacionVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$serializacionVersion")

                //ktor modulo compartido
                implementation("io.ktor:ktor-client-core:$ktorVersion")

                //ktor pluggin
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                //Libreria de  logging
                implementation("io.github.aakira:napier:2.6.1")

                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting{
            dependencies{

                //ktor modulo android
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }

        val iosMain by getting{
            dependencies{

                //ktor modulo ios
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
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