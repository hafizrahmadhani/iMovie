package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity

class ViewModelFavoriteTvShow(private val movieRepository: MovieRepository) : ViewModel() {

    private val notifMessage = MutableLiveData<String>()

    fun callNotifMessage() :LiveData<String> = notifMessage

    fun callFavoriteTvShow(): LiveData<PagedList<MovieEntity>> {
        val favorite = movieRepository.callTvShowFavorite()
        favorite.observeForever{
            val messageData = if(it.isEmpty()) "Tidak Ada Data" else ""
            notifMessage.postValue(messageData)
        }
        return favorite
    }
}