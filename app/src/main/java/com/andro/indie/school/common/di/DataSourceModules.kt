package com.andro.indie.school.common.di

import com.andro.indie.school.data.source.remote.MovieRemoteDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by herisulistiyanto on 07/11/19.
 * KjokenKoddinger
 */

object DataSourceModules: BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(
                remoteDataSourceModule
        )

    private val remoteDataSourceModule = module {
        single { MovieRemoteDataSource(get()) }
    }
}