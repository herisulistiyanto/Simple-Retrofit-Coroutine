package com.andro.indie.school.data.model.vo

import com.google.gson.annotations.SerializedName


/**
 * Created by herisulistiyanto on 2019-11-05.
 * KjokenKoddinger
 */

data class MovieItem(
        @SerializedName("backdrop_path")
        val backdropPath: String? = "",
        @SerializedName("genre_ids")
        val genreIds: List<Int?>? = listOf(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("overview")
        val overview: String? = "",
        @SerializedName("popularity")
        val popularity: Double? = 0.0,
        @SerializedName("poster_path")
        val posterPath: String? = "",
        @SerializedName("release_date")
        val releaseDate: String? = "",
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("vote_average")
        val voteAverage: Double? = 0.0
)