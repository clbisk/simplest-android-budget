plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
}

android {
	namespace = "clbisk.simplestbudget"
	compileSdk = 35

	defaultConfig {
		applicationId = "clbisk.simplestbudget"
		minSdk = 34
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables.useSupportLibrary = true
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
		viewBinding = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
	}
}

dependencies {
	// Compose
	implementation(libs.material3)
	implementation(libs.ui)
	implementation(libs.ui.tooling)
	implementation(libs.ui.tooling.preview)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.navigation.compose)

	// Room
	implementation(libs.androidx.room.runtime)
	implementation(libs.androidx.runtime.livedata)
	implementation(libs.androidx.glance.preview)
	implementation(libs.androidx.material3.adaptive.navigation.suite.android)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)

	// Glance
	implementation(libs.glance.appwidget)
	implementation(libs.glance.material)
	implementation(libs.hilt.android)
	implementation(libs.hilt.navigation.compose)
	ksp(libs.hilt.compiler)
	// Glance test
	androidTestImplementation(libs.hilt.android.testing)
	androidTestAnnotationProcessor(libs.hilt.compiler)
	testImplementation(libs.hilt.android.testing)
	testAnnotationProcessor(libs.hilt.compiler)

	// nav serialization
	implementation(libs.serialization)

	// included with new empty activity
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