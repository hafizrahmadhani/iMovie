package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizrahmadhani.movie.activity.TypeMovie
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import kotlinx.coroutines.launch

class ViewModelDetailMovie (private val movieRepository: MovieRepository) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val notifMessage = MutableLiveData<String>()

    fun callLoading(): LiveData<Boolean> = loading
    fun callNotifMessage() : LiveData<String> = notifMessage

    fun callDetailMovie(typeMovie : Int, idMovie : Int) : LiveData<DetailMovieEntity>{
        val detail = MutableLiveData<DetailMovieEntity>()
        viewModelScope.launch {
            val type = when (typeMovie) {
                TypeMovie.typeMovie -> movieRepository.callDetailMovie(idMovie)
                else -> movieRepository.callDetailTv(idMovie)
            }
            detail.postValue(type)
        }
        return detail
    }

    fun insertMovieFavorite(favorite : MovieEntity){
        viewModelScope.launch {
            movieRepository.insertMovieFavorite(favorite)
        }
    }

    fun deleteMovieFavorite(favorite : MovieEntity){
        viewModelScope.launch {
            movieRepository.deleteMovieFavorite(favorite)
        }
    }

    fun ifFavoriteMovie(id : Int) : LiveData<Boolean>{
        return movieRepository.ifFavoriteMovie(id)
    }

}