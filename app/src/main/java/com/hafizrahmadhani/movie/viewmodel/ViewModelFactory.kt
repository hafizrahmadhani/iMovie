package com.hafizrahmadhani.movie.viewmodel

import android.content.Context
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
        fun getPosition(context: Context): ViewModelFactory =
            position ?: synchronized(this) {
                position ?: ViewModelFactory(Injection.appRepository(context))
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
            modelClass.isAssignableFrom(ViewModelFavoriteMovie::class.java) -> {
                ViewModelFavoriteMovie(movieRepository) as T
            }
            modelClass.isAssignableFrom(ViewModelFavoriteTvShow::class.java) -> {
                ViewModelFavoriteTvShow(movieRepository) as T
            }
            else -> ViewModelMovie(movieRepository) as T
        }
    }
}