package com.andro.indie.school.api

import com.andro.indie.school.data.model.response.MovieDetailResponse
import com.andro.indie.school.data.model.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by herisulistiyanto on 2019-10-31.
 * KjokenKoddinger
 */

interface MovieApiService {

    @GET("/3/discover/movie")
    suspend fun discoverAllMovies(): Response<MovieResponse>

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): Response<MovieDetailResponse>
}