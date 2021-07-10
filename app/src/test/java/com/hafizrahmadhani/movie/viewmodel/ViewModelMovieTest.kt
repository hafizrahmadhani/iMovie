package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.response.MovieResponse
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
class ViewModelMovieTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieResponse>>

    @Mock
    private lateinit var responseHttpError: HttpException

    private lateinit var viewModel :ViewModelMovie

    @Before
    fun init(){
        viewModel = ViewModelMovie(movieRepository)
        val response = Response.error<Error>(500, ResponseBody.create(null, "".toByteArray()))
        responseHttpError = HttpException(response)
    }

    @Test
    fun callMovieList(){
        val dummyMovie = BankData.dataMovie()
        val detailMovie = arrayListOf<MovieResponse>()
        dummyMovie.forEach { val movie = MovieResponse(
                it.title,
                it.description,
                it.poster,
                it.image,
                it.date,
                it.voteAverage!!.toDouble(),
        )
            detailMovie.add(movie)
        }
        val value = MutableLiveData<List<MovieResponse>>()
        value.value = detailMovie


        val pageMovie = 2
        Mockito.`when`(movieRepository.callMovieApi(pageMovie)).thenReturn(value)
        viewModel.callData(2)
        val result = viewModel.takeData()
        assertNotNull(result)
        verify(movieRepository).callMovieApi(pageMovie)
        viewModel.takeData().observeForever(observer)
        observer.onChanged(detailMovie)
    }
}