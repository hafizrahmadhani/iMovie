package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class TvDetailResponses(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("genres")
    val genres: List<GenreResponse>? = null,

    @SerializedName("first_air_date")
    val first_air_date: String? = null
)
