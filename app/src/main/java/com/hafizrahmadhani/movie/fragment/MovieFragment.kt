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
import com.hafizrahmadhani.movie.activity.TypeMovie
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.databinding.FragmentMovieBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelMovie
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieFragment : Fragment() {

    private lateinit var viewModelMovie: ViewModelMovie
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieFactory = ViewModelFactory.getPosition(requireActivity())
        viewModelMovie = ViewModelProvider(this, movieFactory)[ViewModelMovie::class.java]

        val adapter = MovieAdapter {
            val detailintent = Intent(context, MovieDetailActivity::class.java)
            detailintent.putExtra(MovieDetailActivity.extraMovie, it.id)
            detailintent.putExtra(MovieDetailActivity.extraType, TypeMovie.typeMovie)
            startActivity(detailintent)
        }

        viewModelMovie.callData().observe(viewLifecycleOwner, { list ->
            list?.let { data ->
                binding.movProgressbar.visibility = View.GONE
                adapter.setMovie(data)
                adapter.notifyDataSetChanged()
            }
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModelMovie.callLoading().observe(viewLifecycleOwner, {
            binding.movProgressbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModelMovie.callNotifMessage().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.notifMessageMov.apply {
                    visibility = View.VISIBLE
                    text = it
                }
            } else {
                binding.notifMessageMov.visibility = View.GONE
            }
        })
    }
}
