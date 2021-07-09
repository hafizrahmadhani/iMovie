package com.hafizrahmadhani.movie.data.response

import com.google.gson.annotations.SerializedName

data class TvResponse (
    @SerializedName("results")
    val tv_show : List<TvApiResponse>

)