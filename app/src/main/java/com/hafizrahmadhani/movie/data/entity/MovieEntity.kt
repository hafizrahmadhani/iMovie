package com.hafizrahmadhani.movie.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hafizrahmadhani.movie.activity.TypeMovie


@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id : Int?,
    val title : String?,
    val description : String?,
    val poster : String?,
    val vote : Double?,
    val type : Int? = TypeMovie.typeMovie
)
