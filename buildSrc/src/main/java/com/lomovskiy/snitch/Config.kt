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

        const val kotlin = "1.5.0"

    }

    object Deps {

        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    }

}
