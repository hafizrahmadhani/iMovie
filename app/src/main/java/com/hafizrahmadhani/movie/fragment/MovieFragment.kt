package com.hafizrahmadhani.movie.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hafizrahmadhani.movie.activity.MovieDetailActivity
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraType
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeMovie
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.databinding.FragmentMovieBinding
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail
import com.hafizrahmadhani.movie.viewmodel.ViewModelMovie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var viewModelMovie : ViewModelMovie
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelMovie = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelMovie::class.java)
        viewModelMovie.callData()
        viewModelMovie.takeData().observe(viewLifecycleOwner,{
            binding.movProgressbar.visibility = View.GONE
            callRecycler(it)
        })
    }

    private fun callRecycler(movie: ArrayList<DataModelMovieDetail>){
        rv_movie.apply {
            val movieAdapter = MovieAdapter(movie)
            adapter = movieAdapter

            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataModelMovieDetail) {
                    val detailIntent = Intent(context, MovieDetailActivity::class.java)
                    detailIntent.putExtra(extraMovie, data.title)
                    detailIntent.putExtra(extraType, typeMovie)
                    startActivity(detailIntent)
                }
            })
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
    }
}