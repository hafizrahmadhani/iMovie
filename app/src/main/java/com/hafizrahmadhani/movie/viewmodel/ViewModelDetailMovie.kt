package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeTvShow
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail

class ViewModelDetailMovie : ViewModel() {

    private lateinit var movieTitle: String

    fun selectedMovie(movies: String) {
        this.movieTitle = movies
    }

    fun callMovie(type: String?): DataModelMovieDetail {
        lateinit var movie: DataModelMovieDetail
        when (type) {
            typeMovie -> {
                for (i in BankData.dataMovie()) {
                    if (i.title == movieTitle) {
                        movie = i
                    }
                }
            }
            typeTvShow -> {
                for(i in BankData.dataTvShow()){
                    if (i.title == movieTitle) {
                        movie = i
                    }
                }
            }
        }
        return movie
    }
}