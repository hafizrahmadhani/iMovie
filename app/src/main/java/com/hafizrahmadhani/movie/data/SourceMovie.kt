package com.hafizrahmadhani.movie.data

import androidx.lifecycle.LiveData
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.hafizrahmadhani.movie.data.response.MovieResponse

interface SourceMovie {
    fun callMovieApi(movie : Int) : LiveData<List<MovieResponse>>

    fun callDetailMovie(idMovie : Int) : LiveData<MovieDetailResponse>

    fun callTvApi(tv : Int) : LiveData<List<MovieResponse>>

    fun callDetailTv(idTv : Int) : LiveData<MovieDetailResponse>

}