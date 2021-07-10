package com.hafizrahmadhani.movie.data

import android.util.Log
import com.hafizrahmadhani.movie.BuildConfig
import com.hafizrahmadhani.movie.IdlingResource
import com.hafizrahmadhani.movie.data.response.*
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiDataSource: ApiDataSource){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        @InternalCoroutinesApi
        fun getInstance(data: ApiDataSource): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(data)
            }
    }

    fun callMovieList(movie : Int, callback: CallListCallback){
        IdlingResource.increment()
        apiDataSource.getMovie(BuildConfig.MOVIE_API, movie).enqueue(object : Callback<MovieApiResponse>{
            override fun onResponse(call: Call<MovieApiResponse>, response: Response<MovieApiResponse>
            ) {
                if (response.isSuccessful){
                    val movieList = response.body()?.results
                    if (movieList != null) {
                        callback.callListBack(movieList)
                    }
                    IdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                val mMovie = BankData.dataMovie()
                val result = arrayListOf<MovieResponse>()
                val findGenre = mMovie.find { it.id == movie }
                val genre = findGenre?.genre?.map { it -> GenreResponse(it.name) }
                mMovie.forEach { movie ->
                    val value = MovieResponse(
                            movie.title,
                            movie.description,
                            movie.image,
                            movie.poster,
                            movie.date,
                            movie.voteAverage!!.toDouble(),
                            genre,
                            movie.id)
                    result.add(value)
                }
                callback.callListBack(result.toList())
                IdlingResource.decrement()
            }
        })

    }

    fun callMovieDetail(idMovie : Int, callback: CallDetailCallback){
        IdlingResource.increment()
        apiDataSource.getMovieDetail(idMovie, BuildConfig.MOVIE_API).enqueue(object : Callback<MovieDetailResponse>{
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if(response.isSuccessful){
                    val movie = response.body()
                    Log.d("dataApi", movie.toString())
                    callback.callDetailBack(movie!!)
                    IdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                val mMovie = BankData.dataMovie()
                val result = mMovie.find { it.id == idMovie }
                val genre = result?.genre?.map { it ->
                    GenreResponse(it.name)
                }
                val movie = MovieDetailResponse(
                        result?.id,
                        result?.title,
                        result?.description,
                        result?.poster,
                        result?.image,
                        genre,
                        result?.date)
                callback.callDetailBack(movie)
                IdlingResource.decrement()
            }
        })
    }

    fun callTvList(tv : Int, callback: CallListCallback){
        IdlingResource.increment()
        apiDataSource.getTvShow(BuildConfig.MOVIE_API, tv).enqueue(object : Callback<TvApiResponse>{
            override fun onResponse(call: Call<TvApiResponse>, response: Response<TvApiResponse>) {
                val tvList = arrayListOf<MovieResponse>()
                if(response.isSuccessful){
                    val result = response.body()?.results
                    result?.forEach { tv ->
                        val value = MovieResponse(
                                tv.name,
                                tv.overview,
                                tv.poster_path,
                                tv.backdrop_path,
                                tv.firstAirDate,
                                tv.voteAverage,
                                tv.genres,
                                tv.id)
                        tvList.add(value)
                    }
                }
                callback.callListBack(tvList.toList())
                IdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvApiResponse>, t: Throwable) {
                val mTv = BankData.dataTvShow()
                val result = arrayListOf<MovieResponse>()
                val findGenre = mTv.find { it.id == tv }
                val genre = findGenre?.genre?.map { it -> GenreResponse(it.name) }
                mTv.forEach { tv ->
                    val value = MovieResponse(
                            tv.title,
                            tv.description,
                            tv.poster,
                            tv.date,
                            tv.image,
                            tv.voteAverage!!.toDouble(),
                            genre,
                            tv.id)
                    result.add(value)
                }
                callback.callListBack(result.toList())
                IdlingResource.decrement()
            }

        })
    }

    fun callTvShowDetail(idTv : Int, callback: CallDetailCallback){
        IdlingResource.increment()
        apiDataSource.getTvShowDetail(idTv, BuildConfig.MOVIE_API).enqueue(object : Callback<TvDetailResponses>{
            override fun onResponse(call: Call<TvDetailResponses>, response: Response<TvDetailResponses>) {
                if(response.isSuccessful){
                    val tvShow = response.body()
                    Log.d("dataApi", tvShow.toString())
                    val genre = tvShow?.genres?.map { it -> GenreResponse(it.name) }
                    val tv = MovieDetailResponse(
                        tvShow?.id,
                        tvShow?.name,
                        tvShow?.overview,
                        tvShow?.poster_path,
                        tvShow?.backdrop_path,
                        genre,
                        tvShow?.first_air_date,
                    )
                    callback.callDetailBack(tv)
                    IdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvDetailResponses>, t: Throwable) {
                val mTV = BankData.dataTvShow()
                val result = mTV.find { it.id == idTv }

                val genre = result?.genre?.map { it ->
                    GenreResponse(it.name)
                }
                val tv = MovieDetailResponse(
                        result?.id,
                        result?.title,
                        result?.description,
                        result?.poster,
                        result?.image,
                        genre,
                        result?.date,
                )

                callback.callDetailBack(tv)
                IdlingResource.decrement()

            }

        })
    }

    interface CallListCallback {
        fun callListBack(listItem: List<MovieResponse>)
    }

    interface CallDetailCallback {
        fun callDetailBack(detailItem: MovieDetailResponse)
    }
}