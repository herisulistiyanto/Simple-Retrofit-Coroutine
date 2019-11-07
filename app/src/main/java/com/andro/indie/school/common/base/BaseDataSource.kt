package com.andro.indie.school.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

/**
 * Created by herisulistiyanto on 01/11/19.
 * KjokenKoddinger
 */

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResponseResult<ResponseWrapper<T>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (null != body) return ResponseResult.Success(ResponseWrapper(body, null))
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(msg: String): ResponseResult<ResponseWrapper<T>> {
        Timber.e(msg)
        return ResponseResult.Error(ResponseWrapper(null, msg))
    }

}

fun <T> resultLiveData(scope: CoroutineScope, call: suspend () -> ResponseResult<T>): LiveData<ResponseResult<T>> {
    return liveData(scope.coroutineContext) {
        emit(ResponseResult.Loading)

        withContext(Dispatchers.IO) {
            emit(call.invoke())
        }
    }
}