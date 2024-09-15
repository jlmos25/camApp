plugins {
    alias (libs.plugins.android.jetbrains.ktx)
    alias(libs.plugins.com.android.library)
}

android {
    namespace = "com.msqr.server_list"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
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
}