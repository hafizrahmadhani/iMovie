package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.databinding.ActivityMovieDetailBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelDetailMovie
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.coroutines.InternalCoroutinesApi

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val extraType ="extra_type"
        const val extraMovie = "extra_movie"
        const val posterSize = "https://image.tmdb.org/t/p/w300/"
        const val imageSize = "https://image.tmdb.org/t/p/w500/"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModelDetailMovie : ViewModelDetailMovie
    private lateinit var detailMovieEntity: DetailMovieEntity

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val typeMovie = intent.getIntExtra(extraType, TypeMovie.typeMovie)
        val idMovie = intent.getIntExtra(extraMovie, 0)
        val detailFactory = ViewModelFactory.getPosition(this)
        viewModelDetailMovie = ViewModelProvider(this, detailFactory)[ViewModelDetailMovie::class.java]
        var statusFavoriteMovie = false

        binding.progressbarDetail.visibility = View.VISIBLE

        viewModelDetailMovie.callDetailMovie(typeMovie, idMovie).observe(this, {
                detailMovie -> detailMovieEntity = detailMovie

            binding.progressbarDetail.visibility = View.GONE
            Glide.with(this@MovieDetailActivity).load("$posterSize${detailMovie?.poster}").apply(RequestOptions().override(150, 200)).into(mov_poster2)
            Glide.with(this@MovieDetailActivity).load("$imageSize${detailMovie?.image}").apply(RequestOptions().override(0, 400)).into(binding.movPoster)
            binding.movTitle.text = detailMovie?.title
            binding.movDate.text = detailMovie?.date
            binding.movDescription.text = detailMovie?.description
            binding.movGenre.text = detailMovie?.genre?.toString()
        })

        viewModelDetailMovie.callLoading().observe(this, {
            binding.progressbarDetail.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModelDetailMovie.callNotifMessage().observe(this, {
            if(it.isNotEmpty()){
                notif_message.apply {
                    visibility = View.VISIBLE
                }
            }
            else {
                notif_message.visibility = View.GONE
            }
        })

        viewModelDetailMovie.ifFavoriteMovie(idMovie).observe(this, {
            statusFavoriteMovie = it
            val status = if(it) {
                R.drawable.ic_favorite_blue
            } else {
                R.drawable.ic_favorite_blank
            }
            fab_detail.setImageResource(status)
        })

        fab_detail.setOnClickListener{
            val favorite = callDetailMovie(detailMovieEntity, typeMovie)
            if(statusFavoriteMovie){
                viewModelDetailMovie.deleteMovieFavorite(favorite)
                Toast.makeText(this, "Dihapus dari Favorit", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModelDetailMovie.insertMovieFavorite(favorite)
                Toast.makeText(this, "Ditambahkan ke dalam Favorit", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun callDetailMovie(detail: DetailMovieEntity, type : Int) : MovieEntity {
        return MovieEntity(
            detail.id,
            detail.title,
            detail.description,
            detail.poster,
            detail.voteAverage,
            type
        )

    }
}