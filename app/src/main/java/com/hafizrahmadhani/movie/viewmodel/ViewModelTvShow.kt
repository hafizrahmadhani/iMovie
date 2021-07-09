package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import kotlinx.coroutines.launch

class ViewModelTvShow(private val movieRepository: MovieRepository) : ViewModel() {

    private var listTvShow = MutableLiveData<List<MovieEntity>>()
    private val loading = MutableLiveData<Boolean>()
    private val notifMessage = MutableLiveData<String>()

    fun callLoading(): LiveData<Boolean> = loading
    fun callNotifMessage() : LiveData<String> = notifMessage

    fun callData(): LiveData<List<MovieEntity>> {
        viewModelScope.launch {
            val data = movieRepository.callTvShowList()
            listTvShow.postValue(data)
        }
        return listTvShow
    }
}