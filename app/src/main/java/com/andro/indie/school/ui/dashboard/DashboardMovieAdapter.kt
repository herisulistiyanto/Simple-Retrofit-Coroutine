package com.andro.indie.school.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andro.indie.school.R
import com.andro.indie.school.common.base.DiffCallback
import com.andro.indie.school.common.extension.convertDate
import com.andro.indie.school.common.extension.loadImageWithUrl
import com.andro.indie.school.data.model.vo.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by herisulistiyanto on 2019-11-05.
 * KjokenKoddinger
 */

class DashboardMovieAdapter(
        private val diffCallback: DiffCallback = DiffCallback(),
        private val listener: (Pair<Int, String?>) -> Unit
) : RecyclerView.Adapter<DashboardMovieAdapter.MovieItemViewHolder>() {

    private val movieList = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieItemViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.onBind(movieList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setListMovieData(data: List<MovieItem>?) {
        calculateDiff(data.orEmpty())
    }

    private fun calculateDiff(newData: List<MovieItem>) {
        diffCallback.setList(movieList, newData)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(movieList) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    inner class MovieItemViewHolder(private val curItemView: View) : RecyclerView.ViewHolder(curItemView) {

        fun onBind(data: MovieItem) {
            with(curItemView) {
                tvMovieTitle.text = data.title
                tvMovieVoteAverage.text = curItemView.context.getString(R.string.text_score, (data.voteAverage
                        ?: 0).toString())
                tvMovieReleaseDate.text = curItemView.context.getString(R.string.text_release, data.releaseDate.convertDate())
                ivMovieImage.loadImageWithUrl(data.backdropPath)
            }

            curItemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    data.id?.let {
                        listener.invoke(it to data.title)
                    }
                }
            }
        }

    }
}