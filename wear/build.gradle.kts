plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "me.chenhe.wearmsger.demo"
        minSdk = 22
        targetSdk = 34
        versionCode = 2
        versionName = versionCode.toString()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
    namespace = "me.chenhe.lib.wearmsger.demo"
}

dependencies {
    implementation(fileTree("libs") { include("*.jar") })
    implementation(project(path = ":wearmsgerlib"))

    implementation("com.google.android.support:wearable:2.9.0")
    implementation("com.google.android.gms:play-services-wearable:18.1.0")
    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    compileOnly("com.google.android.wearable:wearable:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
}
