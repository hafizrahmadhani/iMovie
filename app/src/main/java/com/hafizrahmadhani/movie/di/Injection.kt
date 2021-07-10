package com.hafizrahmadhani.movie.di

import com.hafizrahmadhani.movie.data.ApiDataSource
import com.hafizrahmadhani.movie.data.ApiService
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.RemoteDataSource
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {
    @InternalCoroutinesApi
    fun appRepository() : MovieRepository {
        val apiDataSource = ApiService.getPosition().create(ApiDataSource::class.java)
        val remoteDataSource = RemoteDataSource.getInstance(apiDataSource)
        return MovieRepository.getPosition(remoteDataSource)
    }
}