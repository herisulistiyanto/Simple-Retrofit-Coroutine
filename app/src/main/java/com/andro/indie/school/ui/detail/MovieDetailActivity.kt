package com.andro.indie.school.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.andro.indie.school.R
import com.andro.indie.school.common.base.BaseActivity
import com.andro.indie.school.common.base.ResponseResult
import com.andro.indie.school.common.extension.loadImageWithUrl
import com.andro.indie.school.data.model.response.MovieDetailResponse
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail_content.*
import kotlinx.android.synthetic.main.layout_common_error.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by herisulistiyanto on 06/11/19.
 * KjokenKoddinger
 */

class MovieDetailActivity : BaseActivity() {

    private val movieDetailViewModel by viewModel<MovieDetailViewModel>()

    private val movieId by lazy(LazyThreadSafetyMode.NONE) {
        intent?.getIntExtra(ExtraKey.EXTRA_MOVIE_ID, 0) ?: 0
    }

    private val movieTitle by lazy(LazyThreadSafetyMode.NONE) {
        intent?.getStringExtra(ExtraKey.EXTRA_MOVIE_TITLE)
    }

    object ExtraKey {
        const val EXTRA_MOVIE_ID = "MovieDetailActivity.EXTRA_MOVIE_ID"
        const val EXTRA_MOVIE_TITLE = "MovieDetailActivity.EXTRA_MOVIE_TITLE"
    }

    object MovieDetailStateView {
        const val STATE_LOADING = 0
        const val STATE_CONTENT = STATE_LOADING + 1
        const val STATE_ERROR = STATE_CONTENT + 1
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        setupToolbar(movieTitle)

        movieDetailViewModel.fetchDetailMovie(movieId).onResult {
            vfMovieDetail.displayedChild = when (it) {
                is ResponseResult.Success -> {
                    it.result.data?.let { response ->
                        populateDetailDisplay(response)
                    }
                    MovieDetailStateView.STATE_CONTENT
                }
                is ResponseResult.Error -> {
                    tvCommonError.text = it.msg.errorMsg.orEmpty()
                    MovieDetailStateView.STATE_ERROR
                }
                is ResponseResult.Loading -> MovieDetailStateView.STATE_LOADING
            }
        }
    }

    override fun loadModules() {
        MovieDetailModule.loadModules()
    }

    private fun setupToolbar(movieTitle: String?) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = movieTitle ?: getString(R.string.text_toolbar_title_movie_detail)
        }
    }

    private fun populateDetailDisplay(response: MovieDetailResponse) {
        ivMovieDetail.loadImageWithUrl(response.backdropPath)
        tvDetailOverview.text = response.overview.orEmpty()

        val genres = response.genres.orEmpty()
        lifecycleScope.launch {
            populateGenre(genres)
        }
    }

    private fun populateGenre(listGenre: List<MovieDetailResponse.Genre>) {
        listGenre.forEach {
            val chip = Chip(this@MovieDetailActivity).apply {
                text = it.name.orEmpty()
                isClickable = false
            }
            chipGroupGenre.addView(chip)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}