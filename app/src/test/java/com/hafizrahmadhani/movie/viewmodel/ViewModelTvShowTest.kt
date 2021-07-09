package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.utils.TestCoroutine
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Error

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ViewModelTvShowTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutine = TestCoroutine()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var list: Observer<List<MovieEntity>>

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
        testCoroutine.runBlockingTest {
            val tvShowList = BankData.dataMovieList()
            `when`(movieRepository.callTvShowList()).thenReturn(tvShowList)
            val value = movieRepository.callTvShowList()
            assertNotNull(value)
            verify(movieRepository).callTvShowList()
            viewModel.callData().observeForever(list)
            verify(list).onChanged(tvShowList)
        }
    }
}