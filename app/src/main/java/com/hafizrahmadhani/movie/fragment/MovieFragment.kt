package com.hafizrahmadhani.movie.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizrahmadhani.movie.activity.MovieDetailActivity
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.MovieType
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraType
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.data.response.MovieResponse
import com.hafizrahmadhani.movie.databinding.FragmentMovieBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelMovie
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieFragment : Fragment() {

    private lateinit var viewModelMovie : ViewModelMovie
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val factory = ViewModelFactory.getPosition()
        viewModelMovie = ViewModelProvider(this, factory).get(ViewModelMovie::class.java)
        viewModelMovie.callData(2)
        viewModelMovie.takeData().observe(viewLifecycleOwner,{
            binding.movProgressbar.visibility = View.GONE
            callRecycler(it)
        })

                viewModelMovie.getLoading().observe(this, {
            binding.movProgressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun callRecycler(movie: List<MovieResponse>){
        binding.rvMovie.apply {
            val movieAdapter = MovieAdapter(movie)
            adapter = movieAdapter

            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MovieResponse) {
                    val detailIntent = Intent(context, MovieDetailActivity::class.java)
                    detailIntent.putExtra(extraMovie, data.id)
                    detailIntent.putExtra(extraType, MovieType)
                    startActivity(detailIntent)
                }
            })
            layoutManager = LinearLayoutManager(context)
        }
    }
}