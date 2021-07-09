package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movie : List<MovieApiResponse>
)
