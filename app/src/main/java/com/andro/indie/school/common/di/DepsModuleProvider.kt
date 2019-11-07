package com.andro.indie.school.common.di

import android.content.ContentResolver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by herisulistiyanto on 07/11/19.
 * KjokenKoddinger
 */

object DepsModuleProvider {

    private val appModule = module {
        single<ContentResolver> { androidContext().contentResolver }
    }

    val modules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                add(appModule)
                addAll(NetworkModules.modules)
                addAll(DataSourceModules.modules)
            }
        }

}