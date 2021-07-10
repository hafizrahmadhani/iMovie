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
class ViewModelTvShowTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieResponse>>

    @Mock
    private lateinit var responseHttpError: HttpException

    private lateinit var viewModel : ViewModelTvShow

    @Before
    fun init(){
        viewModel = ViewModelTvShow(movieRepository)
        val response = Response.error<Error>(500, ResponseBody.create(null, "".toByteArray()))
        responseHttpError = HttpException(response)
    }

    @Test
    fun callTvShowList() {
        val dummyTvShow = BankData.dataTvShow()
        val detailTvShow = arrayListOf<MovieResponse>()
        dummyTvShow.forEach {
            val tv = MovieResponse(
                    it.title,
                    it.description,
                    it.poster,
                    it.image,
                    it.date,
                    it.voteAverage!!.toDouble(),
            )
            detailTvShow.add(tv)
        }
        val value = MutableLiveData<List<MovieResponse>>()
        value.value = detailTvShow


        val pageTv = 2
        Mockito.`when`(movieRepository.callTvApi(pageTv)).thenReturn(value)
        viewModel.callData(2)
        val result = viewModel.takeData()
        assertNotNull(result)
        verify(movieRepository).callTvApi(pageTv)
        viewModel.takeData().observeForever(observer)
        observer.onChanged(detailTvShow)
    }
}