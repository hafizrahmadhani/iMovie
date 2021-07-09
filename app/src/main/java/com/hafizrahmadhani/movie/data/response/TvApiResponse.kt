package com.hafizrahmadhani.movie.data.response


import com.google.gson.annotations.SerializedName

data class TvApiResponse(
    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("poster_path")
    val poster_path : String,

    @SerializedName("vote_average")
    val vote_average : Double
)
