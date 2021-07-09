package com.hafizrahmadhani.movie.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.data.entity.MovieEntity

interface SourceMovie {

    suspend fun callMovieList() : List<MovieEntity>

    suspend fun callDetailMovie(idMovie : Int) : DetailMovieEntity

    suspend fun callTvShowList() : List<MovieEntity>

    suspend fun callDetailTv(idTv : Int) : DetailMovieEntity

    fun callMovieFavorite() : LiveData<PagedList<MovieEntity>>

    fun callTvShowFavorite() : LiveData<PagedList<MovieEntity>>

    suspend fun insertMovieFavorite(movie : MovieEntity)

    suspend fun deleteMovieFavorite(movie : MovieEntity)

    fun ifFavoriteMovie(id : Int) : LiveData<Boolean>


}