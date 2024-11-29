plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.amonteiro.a2024_11_sopra"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.amonteiro.a2024_11_sopra"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    //requete
    implementation("com.squareup.okhttp3:okhttp:+")
    //Parsing JSON
    implementation("com.google.code.gson:gson:+")
    //collectAsState de MutableStateFlow
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    //Image url
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    //Cycle de vie du ViewModel =viewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    //Navigation compose
    implementation("androidx.navigation:navigation-compose:2.7.0")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}