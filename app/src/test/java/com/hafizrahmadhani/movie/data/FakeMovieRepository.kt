package com.hafizrahmadhani.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.hafizrahmadhani.movie.data.response.MovieResponse

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource): SourceMovie {

    override fun callMovieApi(movie: Int): LiveData<List<MovieResponse>> {
        val movieApi = MutableLiveData<List<MovieResponse>>()
        remoteDataSource.callMovieList(movie, object : RemoteDataSource.CallListCallback {
            override fun callListBack(listItem: List<MovieResponse>) {
                movieApi.postValue(listItem)
            }

        })
        return movieApi
    }

    override fun callDetailMovie(idMovie: Int): LiveData<MovieDetailResponse> {
        val detailMovie = MutableLiveData<MovieDetailResponse>()
        remoteDataSource.callMovieDetail(idMovie, object : RemoteDataSource.CallDetailCallback{
            override fun callDetailBack(detailItem: MovieDetailResponse) {
                detailMovie.postValue(detailItem)
            }
        })
        return detailMovie
    }

    override fun callTvApi(tv: Int): LiveData<List<MovieResponse>> {
        val tvApi = MutableLiveData<List<MovieResponse>>()
        remoteDataSource.callTvList(tv, object : RemoteDataSource.CallListCallback{
            override fun callListBack(listItem: List<MovieResponse>) {
                tvApi.postValue(listItem)
            }
        })
        return tvApi
    }

    override fun callDetailTv(idTv: Int): LiveData<MovieDetailResponse> {
        val detailTvShow = MutableLiveData<MovieDetailResponse>()
        remoteDataSource.callTvShowDetail(idTv, object : RemoteDataSource.CallDetailCallback{
            override fun callDetailBack(detailItem: MovieDetailResponse) {
                detailTvShow.postValue(detailItem)
            }
        })
        return detailTvShow
    }

}