package com.lomovskiy.snitch

object Config {

    object GradlePlugins {

        const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    }

    object Versions {

        const val minSdk = 21
        const val compileSdk = 30
        const val targetSdk = 30
        const val buildTools = "30.0.3"
        const val applicationId = "com.lomovskiy.snitch"
        const val code = 1
        const val name = "1.0.0"
        const val compose = "1.0.0-beta07"

        const val androidGradlePlugin = "4.1.2"

        const val kotlin = "1.4.32"
        const val lifecycle = "2.3.1"
        const val lifecycleViewModelCompose = "1.0.0-alpha05"
        const val hilt = "1.0.0"
        const val hiltCompiler = "2.35"

    }

    object Deps {

        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        val lifecycle = arrayOf(
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}",
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}",
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}",
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}",
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        )
        val compose = arrayOf(
            "androidx.compose.ui:ui:${Versions.compose}",
            "androidx.compose.ui:ui-tooling:${Versions.compose}",
            "androidx.compose.material:material:${Versions.compose}"
        )

        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltCompiler}"

    }

}
