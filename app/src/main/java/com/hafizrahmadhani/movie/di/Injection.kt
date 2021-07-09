package com.hafizrahmadhani.movie.di

import android.content.Context
import com.hafizrahmadhani.movie.AppExecutors
import com.hafizrahmadhani.movie.data.ApiDataSource
import com.hafizrahmadhani.movie.data.ApiService
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.RemoteDataSource
import com.hafizrahmadhani.movie.data.database.LocalDataSource
import com.hafizrahmadhani.movie.data.database.MovieDatabase
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {
    @InternalCoroutinesApi
    fun appRepository(context: Context) : MovieRepository {
        val apiDataSource = ApiService.getPosition().create(ApiDataSource::class.java)
        val movieDatabase = MovieDatabase.getPosition(context)
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(apiDataSource)
        val localDataSource = LocalDataSource.getInstance(movieDatabase.movieDao())
        return MovieRepository.getPosition(remoteDataSource, localDataSource, appExecutors)
    }
}