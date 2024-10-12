plugins {
    alias (libs.plugins.android.jetbrains.ktx)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.ksp.plugin)
}

android {
    namespace = "com.msqr.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.koin.core.ktx)
    implementation(libs.koin.android.ktx)
    implementation(libs.kotlin.corutines)
    implementation(libs.retrofit.ktx)
    implementation(libs.converter.gson.ktx)
    implementation(project(":domain"))
    implementation("dev.krud:shapeshift:0.8.0")

    runtimeOnly(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}