package com.andro.indie.school.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andro.indie.school.data.source.remote.MovieRemoteDataSource

/**
 * Created by herisulistiyanto on 01/11/19.
 * KjokenKoddinger
 */

class DashboardViewModel(private val movieRemoteDataSource: MovieRemoteDataSource) : ViewModel() {

    fun discoverAllMovies() = movieRemoteDataSource.discoverAllMovies(viewModelScope)
}