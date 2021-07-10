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
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.TvShowType
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.extraType
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.data.response.MovieResponse
import com.hafizrahmadhani.movie.databinding.FragmentTvShowBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelTvShow
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TvShowFragment : Fragment() {

    private lateinit var viewModelTvShow: ViewModelTvShow
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getPosition()
        viewModelTvShow = ViewModelProvider(this, factory).get(ViewModelTvShow::class.java)
        viewModelTvShow.callData(2)
        viewModelTvShow.takeData().observe(viewLifecycleOwner, {
            binding.tvshowProgressbar.visibility = View.GONE
            callRecycler(it)
        })

        viewModelTvShow.getLoading().observe(this, {
            binding.tvshowProgressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun callRecycler(tvShow: List<MovieResponse>){
        binding.rvTvshow.apply {
            val tvShowAdapter = MovieAdapter(tvShow)
            adapter = tvShowAdapter

            tvShowAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MovieResponse) {
                    val detailIntent = Intent(context, MovieDetailActivity::class.java)
                    detailIntent.putExtra(extraMovie, data.id)
                    detailIntent.putExtra(extraType, TvShowType)
                    startActivity(detailIntent)
                }
            })
            layoutManager = LinearLayoutManager(context)
        }
    }
}