plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("io.realm.kotlin")
}

android {
    namespace = "vn.dungnt.medpro"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.dungnt.medpro"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            isDefault = true
            buildConfigField("String", "DEFAULT_URL", "\"https://backend-nhom5.vercel.app/api/\"")
            buildConfigField("String", "PROVINCE_URL", "\"https://vapi.vnappmob.com/api/\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.guava)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Multidex
    implementation(libs.androidx.multidex)

    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // DI Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.gson)
    implementation(libs.rxjava3.retrofit.adapter)

    implementation(platform(libs.firebase.bom))

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    // Eventbus
    implementation(libs.eventbus)

    //Realm Database
    implementation(libs.library.base)

    // View Support
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    implementation(libs.circleimageview)
    implementation(libs.view)
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.android.mask.date.edittext)
}