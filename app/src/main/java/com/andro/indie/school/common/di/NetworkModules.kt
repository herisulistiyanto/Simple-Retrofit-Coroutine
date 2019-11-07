package com.andro.indie.school.common.di

import com.andro.indie.school.BuildConfig
import com.andro.indie.school.api.AuthInterceptor
import com.andro.indie.school.api.MovieApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by herisulistiyanto on 07/11/19.
 * KjokenKoddinger
 */

object NetworkModules: BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(
                retrofitModule,
                webServiceModule
        )

    private val webServiceModule = module {
        single { get<Retrofit>().create(MovieApiService::class.java) }
    }

    private val retrofitModule = module {
        single { provideAuthInterceptor() }
        single { provideOkHttpClient(get()) }
        single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    }

    private fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(logging)
                .connectTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}