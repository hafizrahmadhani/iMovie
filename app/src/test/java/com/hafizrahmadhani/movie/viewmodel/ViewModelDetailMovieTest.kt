package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.utils.TestCoroutine
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ViewModelDetailMovieTest {

    companion object {
        const val movieType = 1
        const val tvType = 2
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutine = TestCoroutine()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieEntity>

    @Mock
    private lateinit var ifFavoriteObserver: Observer<Boolean>

    @Mock
    private lateinit var viewModelDetailMovie: ViewModelDetailMovie
    private lateinit var responseHttpError: HttpException

    private var list = BankData.dataMovieList()[0]
    private var detail = BankData.dataMovieDetail()


    @Before
    fun init() {
        viewModelDetailMovie = ViewModelDetailMovie(movieRepository)
        val response = Response.error<Error>(500, ResponseBody.create(null, "".toByteArray()))
        responseHttpError = HttpException(response)
    }

    @Test
    fun callMovieDetail() {
        testCoroutine.runBlockingTest {
            val idMovie = 1
            val detailMovie = detail
            `when`(movieRepository.callDetailMovie(idMovie)).thenReturn(detailMovie)
            val value = viewModelDetailMovie.callDetailMovie(movieType, idMovie)
            verify(movieRepository).callDetailMovie(idMovie)
            TestCase.assertNotNull(value)
            viewModelDetailMovie.callDetailMovie(movieType, idMovie).observeForever(observer)
            verify(observer).onChanged(detail)
        }

    }

    @Test
    fun callTvShowDetail() {
        testCoroutine.runBlockingTest {
            val idTvShow = 1
            val detailTvShow = detail
            `when`(movieRepository.callDetailTv(idTvShow)).thenReturn(detailTvShow)
            val value = viewModelDetailMovie.callDetailMovie(tvType, idTvShow)
            verify(movieRepository).callDetailTv(idTvShow)
            assertNotNull(value)

            viewModelDetailMovie.callDetailMovie(tvType, idTvShow).observeForever(observer)
            verify(observer).onChanged(detail)
        }
    }

    @Test
    fun addFavoriteMovie() {
        testCoroutine.runBlockingTest {
            val favorite = list
            `when`(movieRepository.insertMovieFavorite(favorite)).thenReturn(Unit)
            viewModelDetailMovie.insertMovieFavorite(favorite)
            verify(movieRepository).insertMovieFavorite(favorite)
        }
    }

    @Test
    fun deleteFavoriteMovie() {
        testCoroutine.runBlockingTest {
            val favorite = list
            `when`(movieRepository.deleteMovieFavorite(favorite)).thenReturn(Unit)
            viewModelDetailMovie.deleteMovieFavorite(favorite)
            verify(movieRepository).deleteMovieFavorite(favorite)
        }
    }

    @Test
    fun checkIfMovieFavorite() {
        val idMovie = 1
        val status = MutableLiveData<Boolean>()
        status.value = false
        `when`(movieRepository.ifFavoriteMovie(idMovie)).thenReturn(status)
        val value = viewModelDetailMovie.ifFavoriteMovie(idMovie).value
        verify(movieRepository).ifFavoriteMovie(idMovie)
        assertNotNull(value)
        assertEquals(status.value, value)
        viewModelDetailMovie.ifFavoriteMovie(idMovie).observeForever(ifFavoriteObserver)
        verify(ifFavoriteObserver).onChanged(status.value)
    }
}