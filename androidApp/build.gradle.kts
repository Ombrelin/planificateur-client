plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("com.arkivanov.decompose:decompose:2.1.4")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.1.4")
            }
        }
    }
    jvmToolchain(17)
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "fr.arsenelapostolet.planificateur.client"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "fr.arsenelapostolet.planificateur.client"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
    google()
}