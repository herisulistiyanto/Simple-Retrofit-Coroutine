package com.andro.indie.school.common.base

/**
 * Created by herisulistiyanto on 01/11/19.
 * KjokenKoddinger
 */

sealed class ResponseResult<out T> {

    data class Success<T>(val result: T): ResponseResult<T>()
    data class Error<T>(val msg: T): ResponseResult<T>()
    object Loading : ResponseResult<Nothing>()

}

data class ResponseWrapper<out T>(val data: T?, val errorMsg: String?)