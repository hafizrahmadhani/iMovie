package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.response.MovieResponse

class ViewModelMovie (private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var listMovie : LiveData<List<MovieResponse>>
    private val loading = MutableLiveData<Boolean>()

    fun getLoading(): LiveData<Boolean> = loading

    fun callData(page : Int){
        listMovie = movieRepository.callMovieApi(page)
    }

    fun takeData() : LiveData<List<MovieResponse>>{
        return listMovie
    }
}


