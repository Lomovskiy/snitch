buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${com.lomovskiy.snitch.Config.Versions.hiltCompiler}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.lomovskiy.snitch.Config.Versions.kotlin}")
    }
}
