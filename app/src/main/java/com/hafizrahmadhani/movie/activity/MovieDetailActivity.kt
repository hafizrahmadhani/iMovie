package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.databinding.ActivityMovieDetailBinding
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail
import com.hafizrahmadhani.movie.viewmodel.ViewModelDetailMovie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val typeMovie = "movie"
        const val typeTvShow = "tvshow"
        const val extraType ="extra_type"
        const val extraMovie = "extra_movie"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModelDetailMovie : ViewModelDetailMovie
    private lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        image = findViewById(R.id.mov_poster)
        binding.progressbarDetail.visibility = View.VISIBLE
        viewModelDetailMovie = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelDetailMovie::class.java)

        val call = intent.extras
        if(call != null){
            val movie = call.getString(extraMovie)
            val type = call.getString(extraType)
            if(movie != null){
                viewModelDetailMovie.selectedMovie(movie)
                callDetailMovie(viewModelDetailMovie.callMovie(type))
                binding.progressbarDetail.visibility = View.GONE
            }
        }
    }

    private fun callDetailMovie(movie: DataModelMovieDetail){
        Glide.with(this@MovieDetailActivity).load(movie.poster).apply(RequestOptions().override(150, 200)).into(mov_poster2)
        Glide.with(this@MovieDetailActivity).load(movie.image).apply(RequestOptions().override(0, 400)).into(image)
        binding.movTitle.text = movie.title
        binding.movDescription.text = movie.description
        binding.movDate.text = movie.date
        binding.movGenre.text = movie.genre

    }
}