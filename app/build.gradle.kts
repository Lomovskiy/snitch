plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = com.lomovskiy.snitch.Config.Versions.compileSdk
    buildToolsVersion = com.lomovskiy.snitch.Config.Versions.buildTools

    defaultConfig {
        applicationId = com.lomovskiy.snitch.Config.Versions.applicationId
        minSdk = com.lomovskiy.snitch.Config.Versions.minSdk
        targetSdk = com.lomovskiy.snitch.Config.Versions.targetSdk
        versionCode = com.lomovskiy.snitch.Config.Versions.code
        versionName = com.lomovskiy.snitch.Config.Versions.name
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = com.lomovskiy.snitch.Config.Versions.compose
    }
}

dependencies {

    val lifecycle_version = "2.3.1"

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation(com.lomovskiy.snitch.Config.Deps.kotlinStd)
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha01")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.compose.ui:ui:${com.lomovskiy.snitch.Config.Versions.compose}")
    implementation("androidx.compose.material:material:${com.lomovskiy.snitch.Config.Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${com.lomovskiy.snitch.Config.Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-alpha08")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")

}