package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.MovieType
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.TvShowType

import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse

class ViewModelDetailMovie (private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var callData : LiveData<MovieDetailResponse>
    private val loading = MutableLiveData<Boolean>()

    fun getLoading(): LiveData<Boolean> = loading

    fun callMovie(idMovie : Int){
        callData = movieRepository.callDetailMovie(idMovie)
    }

    fun callTvShow(idTvShow : Int){
        callData = movieRepository.callDetailTv(idTvShow)
    }

    fun getDataApi(typeMovie : String?, idMovie: Int): LiveData<MovieDetailResponse>{
        when(typeMovie){
            MovieType -> callMovie(idMovie)
            TvShowType -> callTvShow(idMovie)
        }
        return callData
    }
}