package com.hafizrahmadhani.movie.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelMovieDetail(
    val id : Int? = null,
    val title : String? = "",
    val description : String? = "",
    val poster : String? = "",
    val image : String? = "",
    val genre : List<DataModelGenre>? = listOf(),
    val date : String? = "",
    val voteAverage : Double? = null,
): Parcelable
