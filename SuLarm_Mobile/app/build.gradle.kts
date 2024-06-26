plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.sularm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sularm"
        minSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
//    implementation(files("libs\\retrofit-2.11.0.jar"))
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
//    implementation("com.mapbox.maps:android:11.4.1")
//    implementation("com.mapbox.search:autofill:2.2.0")
//    implementation("com.mapbox.search:discover:2.2.0")
//    implementation("com.mapbox.search:place-autocomplete:2.2.0")
//    implementation("com.mapbox.search:mapbox-search-android-ui:2.2.0")
    implementation("com.google.android.material:material:1.3.0")
//    implementation("com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.2")
//    implementation("com.mapbox.navigation:core:2.12.0")
//    implementation("com.mapbox.navigation:ui:2.12.0")
    implementation("com.mapbox.navigationcore:navigation:3.2.0-rc.1")
    implementation("com.mapbox.navigationcore:ui-maps:3.2.0-rc.1")
//    implementation 'com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.2'
//    implementation("androidx.fragment.app.DialogFragment")
//    implementation("com.mapbox.mapboxsdk:mapbox-android-geocoding:5.3.0")
//    implementation("com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.2")
//    implementation("com.mapbox.mapboxsdk:mapbox-sdk-services:5.6.1")
//    implementation("com.mapbox.mapboxsdk:mapbox-android-geocoding:5.3.0")

//    implementation(files("libs\\converter-gson-2.11.0.jar"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}