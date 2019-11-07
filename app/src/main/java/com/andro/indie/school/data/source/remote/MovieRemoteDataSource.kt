package com.andro.indie.school.data.source.remote

import androidx.lifecycle.LiveData
import com.andro.indie.school.api.MovieApiService
import com.andro.indie.school.common.base.BaseDataSource
import com.andro.indie.school.common.base.ResponseResult
import com.andro.indie.school.common.base.ResponseWrapper
import com.andro.indie.school.common.base.resultLiveData
import com.andro.indie.school.data.model.response.MovieDetailResponse
import com.andro.indie.school.data.model.response.MovieResponse
import kotlinx.coroutines.CoroutineScope

/**
 * Created by herisulistiyanto on 01/11/19.
 * KjokenKoddinger
 */

class MovieRemoteDataSource(private val movieApiService: MovieApiService) : BaseDataSource() {

    fun discoverAllMovies(scope: CoroutineScope): LiveData<ResponseResult<ResponseWrapper<MovieResponse>>> = resultLiveData(scope) {
        getResult {
            movieApiService.discoverAllMovies()
        }
    }

    fun getMovieDetails(movieId: Int, scope: CoroutineScope): LiveData<ResponseResult<ResponseWrapper<MovieDetailResponse>>> = resultLiveData(scope) {
        getResult {
            movieApiService.getMovieDetail(movieId)
        }
    }

}