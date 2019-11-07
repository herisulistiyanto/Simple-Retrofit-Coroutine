package com.andro.indie.school

import android.app.Application
import com.andro.indie.school.common.di.DepsModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by herisulistiyanto on 01/11/19.
 * KjokenKoddinger
 */

class RetrofitCoroutineApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RetrofitCoroutineApp)
            modules(DepsModuleProvider.modules)
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}