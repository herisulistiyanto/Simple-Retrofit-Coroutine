package com.andro.indie.school.ui.dashboard

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andro.indie.school.R
import com.andro.indie.school.common.base.BaseActivity
import com.andro.indie.school.common.base.ResponseResult
import com.andro.indie.school.common.extension.launchActivity
import com.andro.indie.school.ui.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_common_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity() {

    private val dashboardViewModel by viewModel<DashboardViewModel>()
    private val movieAdapter by lazy {
        DashboardMovieAdapter {
            launchActivity<MovieDetailActivity>(
                    MovieDetailActivity.ExtraKey.EXTRA_MOVIE_ID to it.first,
                    MovieDetailActivity.ExtraKey.EXTRA_MOVIE_TITLE to it.second
            )
        }
    }

    object DashboardStateView {
        const val STATE_LOADING = 0
        const val STATE_CONTENT = STATE_LOADING + 1
        const val STATE_ERROR = STATE_CONTENT + 1
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dashboard)
        initDisplay()

        dashboardViewModel.discoverAllMovies().onResult {
            vfDashboardContent.displayedChild = when(it) {
                is ResponseResult.Success -> {
                    val data = it.result.data?.mapToMovieItemList()
                    movieAdapter.setListMovieData(data)
                    DashboardStateView.STATE_CONTENT
                }
                is ResponseResult.Loading -> {
                    DashboardStateView.STATE_LOADING
                }
                is ResponseResult.Error -> {
                    tvCommonError.text = it.msg.errorMsg.orEmpty()
                    DashboardStateView.STATE_ERROR
                }
            }
        }
    }

    override fun loadModules() {
        DashboardModule.loadModules()
    }

    private fun initDisplay() {
        with(rvDashboardMovie) {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = movieAdapter
        }
    }
}
