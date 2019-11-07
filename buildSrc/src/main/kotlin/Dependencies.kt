/**
 * Created by herisulistiyanto on 2019-10-31.
 * KjokenKoddinger
 */

typealias DepsList = List<Pair<String, LibType>>

object Dependencies {

    object AndroidLib {
        const val ktx = "androidx.core:core-ktx:${DepsConstant.LibVersion.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${DepsConstant.LibVersion.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${DepsConstant.LibVersion.constraintLayout}"
        const val cardView = "androidx.cardview:cardview:${DepsConstant.LibVersion.cardView}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${DepsConstant.LibVersion.recyclerView}"
        const val material = "com.google.android.material:material:${DepsConstant.LibVersion.material}"
        const val paging = "androidx.paging:paging-runtime:${DepsConstant.LibVersion.pagingVersion}"
    }

    object LifecycleLib {
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${DepsConstant.LibVersion.lifecycleVersion}"
        const val extension = "androidx.lifecycle:lifecycle-extensions:${DepsConstant.LibVersion.lifecycleVersion}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${DepsConstant.LibVersion.lifecycleVersion}"
        const val livedataCore = "androidx.lifecycle:lifecycle-livedata-core:${DepsConstant.LibVersion.lifecycleVersion}"
        const val reactive = "androidx.lifecycle:lifecycle-reactivestreams:${DepsConstant.LibVersion.lifecycleVersion}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${DepsConstant.LibVersion.lifecycleVersion}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DepsConstant.LibVersion.lifecycleVersion}"
    }

    object PersistenceLib {
        const val roomKtx = "androidx.room:room-ktx:${DepsConstant.LibVersion.roomVersion}"
        const val roomRuntime = "androidx.room:room-runtime:${DepsConstant.LibVersion.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${DepsConstant.LibVersion.roomVersion}"
    }

    object ConcurrentLib {
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DepsConstant.LibVersion.coroutineVersion}"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DepsConstant.LibVersion.coroutineVersion}"
    }

    object DebuggingLib {
        const val timber = "com.jakewharton.timber:timber:${DepsConstant.DebuggingVersion.timberVersion}"
        const val stetho = "com.facebook.stetho:stetho:${DepsConstant.DebuggingVersion.stethoVersion}"
        const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${DepsConstant.DebuggingVersion.stethoVersion}"
    }

    object NetworkingLib {
        const val gson = "com.google.code.gson:gson:${DepsConstant.LibVersion.gsonVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${DepsConstant.LibVersion.retrofitVersion}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${DepsConstant.LibVersion.retrofitVersion}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${DepsConstant.LibVersion.okhttpVersion}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${DepsConstant.LibVersion.okhttpVersion}"
    }

    object WorkerLib {
        const val work = "androidx.work:work-runtime-ktx:${DepsConstant.LibVersion.workVersion}"
    }

    object InjectionLib {
        const val koin = "org.koin:koin-android:${DepsConstant.LibVersion.koinVersion}"
        const val koinScope = "org.koin:koin-androidx-scope:${DepsConstant.LibVersion.koinVersion}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${DepsConstant.LibVersion.koinVersion}"
    }

    object ImageLoaderLib {
        const val coil = "io.coil-kt:coil:${DepsConstant.LibVersion.coilVersion}"
    }

    object CommonThirdPartyLib {
        const val shimmer = "com.facebook.shimmer:shimmer:${DepsConstant.LibVersion.shimmerVersion}"
    }


    //===================== getter function =========================
    val androidLibs: DepsList = listOf(
            AndroidLib.ktx to LibType.Library,
            AndroidLib.appCompat to LibType.Library,
            AndroidLib.constraintLayout to LibType.Library,
            AndroidLib.cardView to LibType.Library,
            AndroidLib.recyclerView to LibType.Library,
//                AndroidLib.paging to LibType.Library,
            AndroidLib.material to LibType.Library
    )

    val lifecycleLibs: DepsList = listOf(
            LifecycleLib.compiler to LibType.Compiler,
            LifecycleLib.extension to LibType.Library,
            LifecycleLib.livedata to LibType.Library,
            LifecycleLib.livedataCore to LibType.Library,
            LifecycleLib.reactive to LibType.Library,
            LifecycleLib.runtime to LibType.Library,
            LifecycleLib.viewmodel to LibType.Library
    )

    val persistenceLibs: DepsList = listOf(
            PersistenceLib.roomCompiler to LibType.Compiler,
            PersistenceLib.roomKtx to LibType.Library,
            PersistenceLib.roomRuntime to LibType.Library
    )

    val concurrentLibs: DepsList = listOf(
            ConcurrentLib.coroutineCore to LibType.Library,
            ConcurrentLib.coroutineAndroid to LibType.Library
    )

    val debuggingLibs: DepsList = listOf(
            DebuggingLib.timber to LibType.Library,
            DebuggingLib.stetho to LibType.Library,
            DebuggingLib.stethoOkHttp to LibType.Library
    )

    val networkingLib: DepsList = listOf(
            NetworkingLib.gson to LibType.Library,
            NetworkingLib.retrofit to LibType.Library,
            NetworkingLib.retrofitGson to LibType.Library,
            NetworkingLib.okhttp to LibType.Library,
            NetworkingLib.okhttpLogging to LibType.Library
    )

    val workerLibs: DepsList = listOf(
            WorkerLib.work to LibType.Library
    )

    val injectionLibs: DepsList = listOf(
            InjectionLib.koin to LibType.Library,
            InjectionLib.koinScope to LibType.Library,
            InjectionLib.koinViewModel to LibType.Library
    )

    val imageLoaderLibs: DepsList = listOf(
            ImageLoaderLib.coil to LibType.Library
    )

    val commonThirdPartyLibs: DepsList = listOf(
            CommonThirdPartyLib.shimmer to LibType.Library
    )

}

sealed class LibType {
    object Library: LibType()
    object Compiler: LibType()
    object TestLib: LibType()
    object AndroidTestLib: LibType()
}
