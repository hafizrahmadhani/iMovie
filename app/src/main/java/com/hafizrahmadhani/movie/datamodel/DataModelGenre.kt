package com.hafizrahmadhani.movie.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelGenre(
    val name : String? = ""
):Parcelable
