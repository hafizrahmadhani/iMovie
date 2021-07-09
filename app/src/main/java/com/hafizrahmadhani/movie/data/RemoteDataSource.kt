package com.hafizrahmadhani.movie.data

import com.hafizrahmadhani.movie.IdlingResource
import com.hafizrahmadhani.movie.data.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSource private constructor(private val apiDataSource: ApiDataSource){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(apiDataSource: ApiDataSource): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiDataSource)
            }
        }
    }

    suspend fun callMovieList() : MovieResponse {
        IdlingResource.idlingIncrement()
        val movieList = withContext(Dispatchers.IO)
        {apiDataSource.callMovieList()}
        IdlingResource.idlingDecrement()
        return movieList
    }

    suspend fun callMovieDetail(id : Int) : MovieDetailResponse{
        IdlingResource.idlingIncrement()
        val movieDetail = withContext(Dispatchers.IO)
        {apiDataSource.callMovieDetail(id)}
        IdlingResource.idlingDecrement()
        return movieDetail
    }

    suspend fun callTvList() : TvResponse {
        IdlingResource.idlingIncrement()
        val tvList = withContext(Dispatchers.IO)
        {apiDataSource.callTvShowList()}
        IdlingResource.idlingDecrement()
        return tvList
    }

    suspend fun callTvShowDetail(id : Int) : TvDetailResponse{
        IdlingResource.idlingIncrement()
        val tvDetail = withContext(Dispatchers.IO)
        {apiDataSource.callTvShowDetail(id)}
        IdlingResource.idlingDecrement()
        return tvDetail
    }

}