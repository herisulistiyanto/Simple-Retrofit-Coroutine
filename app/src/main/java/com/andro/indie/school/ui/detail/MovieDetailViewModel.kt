package com.andro.indie.school.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andro.indie.school.data.source.remote.MovieRemoteDataSource

/**
 * Created by herisulistiyanto on 06/11/19.
 * KjokenKoddinger
 */

class MovieDetailViewModel(private val movieRemoteDataSource: MovieRemoteDataSource): ViewModel() {

    fun fetchDetailMovie(movieId: Int) = movieRemoteDataSource.getMovieDetails(movieId, viewModelScope)

}