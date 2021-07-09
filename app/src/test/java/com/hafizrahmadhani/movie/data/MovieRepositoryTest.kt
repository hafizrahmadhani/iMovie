@file:Suppress("UNCHECKED_CAST")

package com.hafizrahmadhani.movie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hafizrahmadhani.movie.AppExecutors
import com.hafizrahmadhani.movie.data.database.LocalDataSource
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.utils.LiveDataTestUtil
import com.hafizrahmadhani.movie.utils.PagedListUtil
import com.hafizrahmadhani.movie.utils.TestCoroutine
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutine = TestCoroutine()

    private var list = BankData.dataMovieList()[0]

    private val appExecutors = mock(AppExecutors::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val movieRepository = MovieRepository(remoteDataSource, localDataSource, appExecutors)


    private val movie = BankData.callMovieListResponse()
    private val detailMovie = BankData.callMovieDetailResponse()
    private val tvShow = BankData.callTvShowListResponse()
    private val detailTvShow = BankData.callTvShowDetailResponse()


    @Test
    fun callMovieList(){
        testCoroutine.runBlockingTest {
            `when`(remoteDataSource.callMovieList()).thenReturn(movie)
            val listMovie = movieRepository.callMovieList()
            verify(remoteDataSource).callMovieList()
            assertNotNull(listMovie)
            assertEquals(movie.movie.size, listMovie.size)
        }
    }

    @Test
    fun callTvShowList(){
        testCoroutine.runBlockingTest{
            `when`(remoteDataSource.callTvList()).thenReturn(tvShow)
            val listTvShow = movieRepository.callTvShowList()
            verify(remoteDataSource).callTvList()
            assertNotNull(listTvShow)
            assertEquals(tvShow.tv_show.size, listTvShow.size)
        }

    }

    @Test
    fun callDetailMovie(){
        testCoroutine.runBlockingTest {
            val idMovie = 1
            `when`(remoteDataSource.callMovieDetail(eq(idMovie))).thenReturn(detailMovie)
            val movieDetail = movieRepository.callDetailMovie(idMovie)
            verify(remoteDataSource).callMovieDetail(eq(idMovie))
            assertNotNull(movieDetail)
            assertEquals(detailMovie.title, movieDetail.title)
        }

    }

    @Test
    fun callDetailTvShow(){
        testCoroutine.runBlockingTest {
            val idTvShow = 1
            `when`(remoteDataSource.callTvShowDetail(eq(idTvShow))).thenReturn(detailTvShow)
            val tvShowDetail = movieRepository.callDetailTv(idTvShow)
            verify(remoteDataSource).callTvShowDetail(eq(idTvShow))
            assertNotNull(tvShowDetail)
            assertEquals(detailTvShow.name, tvShowDetail.title)
        }
    }

    @Test
    fun checkIfFavorite(){
        val idMovie = 1
        val statusFavorite = MutableLiveData<Boolean>()
        statusFavorite.value = false
        `when`(localDataSource.ifFavoriteMovie(idMovie)).thenReturn(statusFavorite)
        val value = LiveDataTestUtil.getValue(movieRepository.ifFavoriteMovie(idMovie))
        verify(localDataSource).ifFavoriteMovie(idMovie)
        assertEquals(false, value)
    }

    @Test
    fun callFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.callFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.callMovieFavorite()

        val favoriteEntities = PagedListUtil.mockPagedList(BankData.dataMovieList())
        verify(localDataSource).callFavoriteMovie()
        assertNotNull(favoriteEntities)
    }

    @Test
    fun callFavoriteTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.callFavoriteTvShow()).thenReturn(dataSourceFactory)
        movieRepository.callTvShowFavorite()

        val favoriteEntities = PagedListUtil.mockPagedList(BankData.dataMovieList())
        verify(localDataSource).callFavoriteTvShow()
        assertNotNull(favoriteEntities)
    }

    @Test
    fun insertFavoriteMovie(){
        testCoroutine.runBlockingTest {
            val movie = list
            `when`(localDataSource.insertMovieFavorite(movie)).thenReturn(Unit)
            movieRepository.insertMovieFavorite(movie)
            verify(localDataSource).insertMovieFavorite(movie)
        }
    }

    @Test
    fun deleteFavoriteMovie(){
       testCoroutine.runBlockingTest {
           val favorite = list
           `when`(localDataSource.deleteFavoriteMovie(favorite)).thenReturn(Unit)
           movieRepository.deleteMovieFavorite(favorite)
           verify(localDataSource).deleteFavoriteMovie(favorite)
       }
    }

}