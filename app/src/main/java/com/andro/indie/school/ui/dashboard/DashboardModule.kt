package com.andro.indie.school.ui.dashboard

import com.andro.indie.school.common.di.BaseViewModelModuleProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by herisulistiyanto on 07/11/19.
 * KjokenKoddinger
 */

object DashboardModule : BaseViewModelModuleProvider {

    override fun loadModules() = lazyLoadModule

    private val lazyLoadModule by lazy { loadKoinModules(viewModelModule) }

    private val viewModelModule = module {
        viewModel { DashboardViewModel(get()) }
    }
}