package com.andro.indie.school.api

import com.andro.indie.school.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by herisulistiyanto on 2019-10-31.
 * KjokenKoddinger
 */

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val interceptUrl = original.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
        val newReq = original.newBuilder().url(interceptUrl).build()
        return chain.proceed(newReq)
    }
}