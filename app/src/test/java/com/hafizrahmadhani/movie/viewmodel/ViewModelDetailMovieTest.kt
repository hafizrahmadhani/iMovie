package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.MovieType
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.TvShowType
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.response.GenreResponse
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.nhaarman.mockitokotlin2.verify
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Error

@RunWith(MockitoJUnitRunner::class)
class ViewModelDetailMovieTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var viewModelDetailMovie: ViewModelDetailMovie

    @Mock
    private lateinit var observer: Observer<MovieDetailResponse>

    private lateinit var responseHttpError : HttpException

    @Before
    fun init(){
        viewModelDetailMovie = ViewModelDetailMovie(movieRepository)
        val response = Response.error<Error>(500, ResponseBody.create(null, "".toByteArray()))
        responseHttpError = HttpException(response)
    }

    @Test
    fun callMovieDetail(){
        val dummyDetail = BankData.dataMovie()[1]
        val dummyGenre = dummyDetail.genre?.map { genre -> GenreResponse(genre.name) }
        val detailMovie = MovieDetailResponse(
                dummyDetail.id,
                dummyDetail.title,
                dummyDetail.date,
                dummyDetail.poster,
                dummyDetail.image,
                dummyGenre,
                dummyDetail.description
        )
        val movie = MutableLiveData<MovieDetailResponse>()
        movie.value = detailMovie

        val movieId = 1
        Mockito.`when`(movieRepository.callDetailMovie(movieId)).thenReturn(movie)
        val result = viewModelDetailMovie.callMovie(movieId)
        assertNotNull(result)
        verify(movieRepository).callDetailMovie(movieId)
        viewModelDetailMovie.getDataApi(MovieType, movieId).observeForever(observer)
        observer.onChanged(detailMovie)
    }

    @Test
    fun callTvShowDetail(){
        val dummyTv = BankData.dataTvShow()[1]
        val genre = dummyTv.genre?.map { genre -> GenreResponse(genre.name) }
        val detailTvShow = MovieDetailResponse(
                dummyTv.id,
                dummyTv.description,
                dummyTv.image,
                dummyTv.poster,
                dummyTv.date,
                genre,
                dummyTv.title,
        )
        val tvShow = MutableLiveData<MovieDetailResponse>()
        tvShow.value = detailTvShow

        val tvShowId = 1
        Mockito.`when`(movieRepository.callDetailTv(tvShowId)).thenReturn(tvShow)
        val result = viewModelDetailMovie.callTvShow(tvShowId)
        assertNotNull(result)
        verify(movieRepository).callDetailTv(tvShowId)
        viewModelDetailMovie.getDataApi(TvShowType, tvShowId).observeForever(observer)
        observer.onChanged(detailTvShow)
    }
}