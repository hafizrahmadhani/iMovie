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
import com.hafizrahmadhani.movie.adapter.PagedMovieListAdapter
import com.hafizrahmadhani.movie.databinding.FragmentFavoriteMovieBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelFavoriteMovie
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding
    private lateinit var viewModelFavoriteMovie: ViewModelFavoriteMovie
    private lateinit var adapter: PagedMovieListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favoriteFactory = ViewModelFactory.getPosition(requireActivity())
            viewModelFavoriteMovie = ViewModelProvider(this, favoriteFactory)[ViewModelFavoriteMovie::class.java]

            adapter = PagedMovieListAdapter {
                val detailIntent = Intent(context, MovieDetailActivity::class.java)
                detailIntent.putExtra(MovieDetailActivity.extraMovie, it.id)
                detailIntent.putExtra(MovieDetailActivity.extraType, TypeMovie.typeMovie)
                startActivity(detailIntent)
            }

            viewModelFavoriteMovie.callFavoriteMovie().observe(viewLifecycleOwner, { favorite ->
                favorite?.let { detail ->
                    binding.movProgressbarFavorite.visibility = View.GONE
                    adapter.submitList(detail)
                    adapter.notifyDataSetChanged()
                }
            })

            viewModelFavoriteMovie.callNotifMessage().observe(viewLifecycleOwner, {
                if(it.isNotEmpty()){
                    binding.notifMessageMovFavorite.apply {
                        visibility = View.VISIBLE
                        text = it
                    }
                }else{
                    binding.notifMessageMovFavorite.visibility = View.GONE
                }
            })

            binding.rvMovieFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvMovieFavorite.setHasFixedSize(true)
            binding.rvMovieFavorite.adapter = adapter
        }
    }
}