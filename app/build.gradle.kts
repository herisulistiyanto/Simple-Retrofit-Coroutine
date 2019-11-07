import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}

fun loadProperties(fileName: String, act: (String, String) -> Unit) {
    val propFile = File(fileName)
    if (propFile.canRead()) {
        val data = readProperties(propFile)
        data.forEach { key, value ->
            act.invoke(key.toString().toUpperCase(), value.toString())
        }
    }

}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")
    defaultConfig {
        applicationId = "com.andro.indie.school"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        loadProperties("credential.properties") { key, value ->
            buildConfigField("String", key, "\"$value\"")
        }

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

}

fun DependencyHandlerScope.execDeps(vararg deps: List<Pair<String, LibType>>) {
    deps.forEach { collection ->
        collection.forEach {
            when (it.second) {
                is LibType.Library -> implementation(it.first)
                is LibType.Compiler -> kapt(it.first)
                is LibType.TestLib -> testImplementation(it.first)
                is LibType.AndroidTestLib -> androidTestImplementation(it.first)
            }
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50")

    execDeps(
            Dependencies.androidLibs,
            Dependencies.lifecycleLibs,
            Dependencies.concurrentLibs,
            Dependencies.injectionLibs,
            Dependencies.networkingLib,
            Dependencies.persistenceLibs,
            Dependencies.imageLoaderLibs,
            Dependencies.workerLibs,
            Dependencies.debuggingLibs,
            Dependencies.commonThirdPartyLibs
    )

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
