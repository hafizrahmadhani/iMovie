package com.hafizrahmadhani.movie.data

import com.hafizrahmadhani.movie.BuildConfig
import com.hafizrahmadhani.movie.data.response.MovieApiResponse
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.hafizrahmadhani.movie.data.response.TvApiResponse
import com.hafizrahmadhani.movie.data.response.TvDetailResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val movieApi : String = BuildConfig.MOVIE_API

interface ApiDataSource {

    @GET("movie/now_playing?api_key=$movieApi")
    fun getMovie(@Query("api_key") movieApi: String, @Query("page") page: Int): Call<MovieApiResponse>

    @GET("movie/{movie_id}?api_key=$movieApi")
    fun getMovieDetail( @Path("movie_id") movieId: Int, @Query("api_key") api_key: String) : Call<MovieDetailResponse>

    @GET("tv/popular?api_key=$movieApi")
    fun getTvShow(@Query("api_key") movieApi: String, @Query("page") page: Int) : Call<TvApiResponse>

    @GET("tv/{tv_id}?api_key=$movieApi")
    fun getTvShowDetail(@Path("tv_id") tvId: Int, @Query("api_key") api_key: String) : Call<TvDetailResponses>

}