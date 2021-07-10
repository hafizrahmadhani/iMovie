package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("genres")
    val genres: List<GenreResponse>? = listOf(),

    @SerializedName("release_date")
    val release_date: String? = null
)
