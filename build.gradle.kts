plugins {
    id("com.android.application") version "8.4.0-rc02" apply false
    id("com.android.library") version "8.4.0-rc02" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0-RC1" apply false
    id("com.vanniktech.maven.publish") version "0.22.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
