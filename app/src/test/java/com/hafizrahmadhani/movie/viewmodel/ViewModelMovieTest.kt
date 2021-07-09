package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.utils.TestCoroutine
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ViewModelMovieTest {

    private lateinit var viewModel: ViewModelMovie

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutine = TestCoroutine()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var list: Observer<List<MovieEntity>>


    @Before
    fun init() {
        viewModel = ViewModelMovie(movieRepository)
    }

    @Test
    fun callMovieList() {
        testCoroutine.runBlockingTest {
            val movieList = BankData.dataMovieList()
            `when`(movieRepository.callMovieList()).thenReturn(movieList)
            val value = movieRepository.callMovieList()
            assertNotNull(value)
            verify(movieRepository).callMovieList()
            viewModel.callData().observeForever(list)
            verify(list).onChanged(movieList)
        }

    }

}

