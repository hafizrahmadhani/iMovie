package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    @SerializedName("page")
    val page : Int? = null,

    @SerializedName("results")
    val results : List<MovieResponse>? = null,

    @SerializedName("total_results")
    val total_results: Int? = null,

    @SerializedName("total_pages")
    val total_pages: Int? = null
)
