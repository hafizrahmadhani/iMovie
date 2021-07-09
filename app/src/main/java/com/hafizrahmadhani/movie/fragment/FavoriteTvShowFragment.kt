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
import com.hafizrahmadhani.movie.databinding.FragmentFavoriteTvShowBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelFavoriteTvShow
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteTvShowFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var viewModelFavoriteTvShow: ViewModelFavoriteTvShow
    private lateinit var adapter: PagedMovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(inflater)
        return binding.root

    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favoriteFactory = ViewModelFactory.getPosition(requireActivity())
            viewModelFavoriteTvShow = ViewModelProvider(this, favoriteFactory)[ViewModelFavoriteTvShow::class.java]

            adapter = PagedMovieListAdapter {
                val detailIntent = Intent(context, MovieDetailActivity::class.java)
                detailIntent.putExtra(MovieDetailActivity.extraMovie, it.id)
                detailIntent.putExtra(MovieDetailActivity.extraType, TypeMovie.typeTvShow)
                startActivity(detailIntent)
            }


            viewModelFavoriteTvShow.callFavoriteTvShow().observe(viewLifecycleOwner, { favorite ->
                favorite?.let { detail ->
                    binding.tvProgressbarFavorite.visibility = View.GONE
                    adapter.submitList(detail)
                    adapter.notifyDataSetChanged()
                }
            })

            viewModelFavoriteTvShow.callNotifMessage().observe(viewLifecycleOwner, {
                if(it.isNotEmpty()){
                    binding.notifMessageTvFavorite.apply{
                        visibility = View.VISIBLE
                        text = it
                    }
                }else{
                    binding.notifMessageTvFavorite.visibility = View.GONE
                }
            })

            binding.rvTvshowFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvTvshowFavorite.setHasFixedSize(true)
            binding.rvTvshowFavorite.adapter = adapter
        }
    }
}