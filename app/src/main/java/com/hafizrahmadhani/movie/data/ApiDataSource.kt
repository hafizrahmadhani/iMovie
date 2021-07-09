package com.hafizrahmadhani.movie.data

import com.hafizrahmadhani.movie.BuildConfig
import com.hafizrahmadhani.movie.data.response.*
import retrofit2.http.GET
import retrofit2.http.Path

const val movieApi : String = BuildConfig.MOVIE_API

interface ApiDataSource {

    @GET("movie/now_playing?api_key=$movieApi")
    suspend fun callMovieList(): MovieResponse

    @GET("movie/{movie_id}?api_key=$movieApi")
    suspend fun callMovieDetail(@Path("movie_id") movieId: Int) : MovieDetailResponse

    @GET("tv/popular?api_key=$movieApi")
    suspend fun callTvShowList() : TvResponse

    @GET("tv/{tv_id}?api_key=$movieApi")
    suspend fun callTvShowDetail(@Path("tv_id") tvId: Int) : TvDetailResponse

}