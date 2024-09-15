plugins {
    alias (libs.plugins.android.application)
    alias (libs.plugins.android.jetbrains.ktx)
}

android {
    namespace = "com.msqr.camapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.msqr.camapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.runtime.ktx)
    implementation(libs.activity.ktx)
    implementation(platform(libs.compose.ktx))
    implementation(libs.ui.ktx)
    implementation(libs.graphics.ktx)
    implementation(libs.tooling.preview.ktx)
    implementation(libs.material3.ktx)
    implementation(libs.junit.ktx)
    androidTestImplementation(libs.test.ext.ktx)
    androidTestImplementation(libs.espresso.ktx)
    androidTestImplementation(platform(libs.compose.ktx))
    androidTestImplementation(libs.ui.test.ktx)
    debugImplementation(libs.ui.tooling.ktx)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.navigator.ktx)

    implementation(libs.koin.core.ktx)
    implementation(project(":navigation"))
}