package com.hafizrahmadhani.movie.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ViewModelTvShowTest{
    private lateinit var viewModel : ViewModelTvShow

    @Before
    fun setUp(){
        viewModel = ViewModelTvShow()
    }

    @Test
    fun getDetail(){
        val tvShowDetail = viewModel.takeData()
        assertNotNull(tvShowDetail)
    }
}