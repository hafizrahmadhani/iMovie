package com.hafizrahmadhani.movie.data.entity

data class DetailMovieEntity(
    val id : Int? = null,
    val title : String? = "",
    val description : String? = "",
    val poster : String? = "",
    val image : String? = "",
    val genre : List<String?> = listOf(),
    val date : String? = "",
    val voteAverage : Double? = null,
)
