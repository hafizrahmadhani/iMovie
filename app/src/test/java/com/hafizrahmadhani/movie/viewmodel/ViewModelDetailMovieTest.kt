package com.hafizrahmadhani.movie.viewmodel

import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeTvShow
import com.hafizrahmadhani.movie.data.BankData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ViewModelDetailMovieTest{
    private lateinit var viewModel : ViewModelDetailMovie

    private val dummyMovDetail = BankData.dataMovie()[0]
    private val dummyTvDetail = BankData.dataTvShow()[0]
    private val detailMovTitle = dummyMovDetail.title
    private val detailTvTitle = dummyTvDetail.title

    @Before
    fun setUp(){
        viewModel = ViewModelDetailMovie()
        viewModel.selectedMovie(detailMovTitle)
        viewModel.selectedMovie(detailTvTitle)
    }

    @Test
    fun getMovDetail(){
        viewModel.selectedMovie(dummyMovDetail.title)
        val detailMovEntity = viewModel.callMovie(type = typeMovie)
        assertNotNull(detailMovEntity)
        assertEquals(dummyMovDetail.title, dummyMovDetail.title)
        assertEquals(dummyMovDetail.poster, dummyMovDetail.poster)
        assertEquals(dummyMovDetail.description, dummyMovDetail.description)
        assertEquals(dummyMovDetail.image, dummyMovDetail.image)
        assertEquals(dummyMovDetail.genre, dummyMovDetail.genre)
        assertEquals(dummyMovDetail.date, dummyMovDetail.date)
    }

    @Test
    fun getTvDetail(){
        viewModel.selectedMovie(dummyTvDetail.title)
        val detailTvEntity = viewModel.callMovie(type = typeTvShow)
        assertNotNull(detailTvEntity)
        assertEquals(dummyTvDetail.title, dummyTvDetail.title)
        assertEquals(dummyTvDetail.poster, dummyTvDetail.poster)
        assertEquals(dummyTvDetail.description, dummyTvDetail.description)
        assertEquals(dummyTvDetail.image, dummyTvDetail.image)
        assertEquals(dummyTvDetail.genre, dummyTvDetail.genre)
        assertEquals(dummyTvDetail.date, dummyTvDetail.date)
    }
}