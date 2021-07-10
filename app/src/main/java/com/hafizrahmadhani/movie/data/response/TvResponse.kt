package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class TvResponse (
    @SerializedName("name")
    val name : String? = null,

    @SerializedName("overview")
    val overview : String? = null,

    @SerializedName("poster_path")
    val poster_path : String? = null,

    @SerializedName("backdrop_path")
    val backdrop_path : String? = null,

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("genres")
    val genres : List<GenreResponse>? = null,

    @SerializedName("id")
    val id : Int? = null
)