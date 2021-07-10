package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.di.Injection
import kotlinx.coroutines.InternalCoroutinesApi

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {
    @InternalCoroutinesApi
    companion object {
        @Volatile
        private var position: ViewModelFactory? = null
        fun getPosition(): ViewModelFactory =
            position ?: synchronized(this) {
                position ?: ViewModelFactory(Injection.appRepository())
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ViewModelMovie::class.java) -> {
                ViewModelMovie(movieRepository) as T
            }
            modelClass.isAssignableFrom(ViewModelTvShow::class.java) -> {
                ViewModelTvShow(movieRepository) as T
            }
            modelClass.isAssignableFrom(ViewModelDetailMovie::class.java) -> {
                ViewModelDetailMovie(movieRepository) as T
            }
            else -> ViewModelMovie(movieRepository) as T
        }
    }
}