plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    //  aaaaaaaaaaaaaaaa plugin ksp
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.example.gerenciadorimoveis"
    // 34 para 35
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gerenciadorimoveis"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {

            // nativa e estável 'isMinifyEnabled = false' para não dar erro de compilação.
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // ponte entre app e o servior da interne, no caso para consulta de cep
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    //navegação jatckpack compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // room, banco local
    implementation("androidx.room:room-runtime:2.8.4")
    implementation("androidx.room:room-ktx:2.8.4")
    // 4. MUDANÇA CRÍTICA: Substituído 'annotationProcessor' por 'ksp'.
    // É isso aqui que vai resolver o erro fatal e gerar o ImovelDatabase_Impl!
    ksp("androidx.room:room-compiler:2.8.4")

    //ciclo view model p compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // extensão para facilitar design (ArrowBack)
    implementation("androidx.compose.material:material-icons-extended")
}