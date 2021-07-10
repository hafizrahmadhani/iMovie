package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.response.MovieResponse

class ViewModelTvShow(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var listTvShow : LiveData<List<MovieResponse>>
    private val loading = MutableLiveData<Boolean>()

    fun getLoading(): LiveData<Boolean> = loading

    fun callData(page : Int){
        listTvShow = movieRepository.callTvApi(page)
    }

    fun takeData() : LiveData<List<MovieResponse>> {
        return listTvShow
    }
}