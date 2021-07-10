package com.hafizrahmadhani.movie.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ViewModelMovieTest{
    private lateinit var viewModel :ViewModelMovie

    @Before
    fun setUp(){
        viewModel = ViewModelMovie()
    }

    @Test
    fun getDetail(){
        val movDetail = viewModel.takeData()
        assertNotNull(movDetail)
    }
}