plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 35
    defaultConfig {
        applicationId = "me.chenhe.wearmsger.demo"
        minSdk = 21
        targetSdk = 35
        versionCode = 2
        versionName = versionCode.toString()
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
    namespace = "me.chenhe.lib.wearmsger.demo"

    kotlin {
        jvmToolchain(21)
    }
}

dependencies {
    implementation(project(path = ":wearmsgerlib"))
    // wearApp(":wear")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    implementation("com.google.android.gms:play-services-wearable:18.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
