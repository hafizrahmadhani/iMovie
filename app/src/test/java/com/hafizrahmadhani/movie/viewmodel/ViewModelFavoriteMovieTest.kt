package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelFavoriteMovieTest{

    private lateinit var viewModelFavoriteMovie: ViewModelFavoriteMovie

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var message : Observer<String>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var list : Observer<List<MovieEntity>>

    @Before
    fun init(){
        viewModelFavoriteMovie = ViewModelFavoriteMovie(movieRepository)
    }

    @Test
    fun callFavoriteMovieList(){
        val favorite = pagedList
        `when`(favorite.size).thenReturn(4)
        val status = MutableLiveData<PagedList<MovieEntity>>()
        status.value = favorite
        `when`(movieRepository.callMovieFavorite()).thenReturn(status)
        val favoriteMovie = viewModelFavoriteMovie.callFavoriteMovie().value
        verify(movieRepository).callMovieFavorite()
        assertNotNull(favoriteMovie)
        assertEquals(favorite.size, favoriteMovie?.size)
        viewModelFavoriteMovie.callFavoriteMovie().observeForever(list)
        viewModelFavoriteMovie.callNotifMessage().observeForever(message)
        verify(list).onChanged(favorite)
    }
}