plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")

    id("com.vanniktech.maven.publish")
}

android {
    compileSdk = 35
    namespace = "me.chenhe.lib.wearmsger"

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }

    kotlin {
        jvmToolchain(21)
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    api("androidx.lifecycle:lifecycle-service:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("com.google.android.gms:play-services-wearable:18.2.0")
}

/*
mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01)
    signAllPublications()
}
**/