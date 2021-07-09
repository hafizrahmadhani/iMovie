package com.hafizrahmadhani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hafizrahmadhani.movie.data.MovieRepository
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelFavoriteTvShowTest{
    private lateinit var viewModelFavoriteTvShow: ViewModelFavoriteTvShow

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
        viewModelFavoriteTvShow = ViewModelFavoriteTvShow(movieRepository)
    }

    @Test
    fun callFavoriteTvShowList(){
        val favorite = pagedList
        `when`(favorite.size).thenReturn(4)
        val status = MutableLiveData<PagedList<MovieEntity>>()
        status.value = favorite
        `when`(movieRepository.callTvShowFavorite()).thenReturn(status)
        val favoriteTvShow = viewModelFavoriteTvShow.callFavoriteTvShow().value
        verify(movieRepository).callTvShowFavorite()
        TestCase.assertNotNull(favoriteTvShow)
        TestCase.assertEquals(favorite.size, favoriteTvShow?.size)
        viewModelFavoriteTvShow.callFavoriteTvShow().observeForever(list)
        viewModelFavoriteTvShow.callNotifMessage().observeForever(message)
        verify(list).onChanged(favorite)
    }
}