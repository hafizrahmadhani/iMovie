package com.hafizrahmadhani.movie.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelMovieDetail(
    val title : String,
    val description : String,
    val poster : String,
    val image : String,
    val genre : String,
    val date : String,
): Parcelable
