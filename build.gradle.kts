buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.lomovskiy.snitch.Config.Versions.kotlin}")
    }
}
