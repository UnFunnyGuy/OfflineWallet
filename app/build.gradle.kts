
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

ksp {
    arg("compose-destinations.generateNavGraphs", "false")
}

android {
    namespace = "com.sarathexp.offlinewallet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sarathexp.offlinewallet"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.compose.material3)
    implementation(libs.compose.material)
    implementation(libs.google.material)
    implementation(libs.compose.material.icons.full)
    implementation(libs.compose.foundation)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.testManifest)

    implementation(libs.compose.destination)
    ksp(libs.compose.destination.ksp)

    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.accompanist.animations)
    implementation(libs.compose.animation)
    implementation(libs.material.motion)

    implementation(libs.dokar.bottomsheet)
    implementation(libs.maxkeppeler.core)
    implementation(libs.maxkeppeler.calendar)
    implementation(libs.maxkeppeler.clock)
    implementation(libs.maxkeppeler.option)

    implementation(libs.dataStore.preferences)

    implementation(libs.timber)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.sql.cipher)
    implementation(libs.room.sql.ktx)
    ksp(libs.room.compiler)

    implementation(libs.google.gson)

    implementation(libs.kotlinx.collections.immutable)

}