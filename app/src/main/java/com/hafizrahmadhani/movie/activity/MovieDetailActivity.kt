package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.hafizrahmadhani.movie.databinding.ActivityMovieDetailBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelDetailMovie
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.coroutines.InternalCoroutinesApi

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MovieType = "movie"
        const val TvShowType = "tvshow"
        const val extraType ="extra_type"
        const val extraMovie = "extra_movie"
        const val posterSize = "https://image.tmdb.org/t/p/w300/"
        const val imageSize = "https://image.tmdb.org/t/p/w500/"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModelDetailMovie : ViewModelDetailMovie

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getPosition()
        viewModelDetailMovie = ViewModelProvider(this, factory).get(ViewModelDetailMovie::class.java)

        val call = intent.extras
        if(call != null){
            val movieId = call.getInt(extraMovie)
            val movieType = call.getString(extraType)
            viewModelDetailMovie.getDataApi(movieType, movieId).observe(this, {
                callDetailMovie(it)
                binding.progressbarDetail.visibility = View.GONE
            })
        }

        viewModelDetailMovie.getLoading().observe(this, {
            binding.progressbarDetail.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun callDetailMovie(movie: MovieDetailResponse){
        Glide.with(this@MovieDetailActivity).load(posterSize + movie.poster_path).apply(RequestOptions().override(150, 200)).into(mov_poster2)
        Glide.with(this@MovieDetailActivity).load(imageSize + movie.backdrop_path).apply(RequestOptions().override(0, 400)).into(binding.movPoster)
        binding.movTitle.text = movie.title
        binding.movDescription.text = movie.overview
        binding.movDate.text = movie.release_date
        binding.movGenre.text = movie.genres?.map { genre -> genre.name }?.joinToString()
    }
}