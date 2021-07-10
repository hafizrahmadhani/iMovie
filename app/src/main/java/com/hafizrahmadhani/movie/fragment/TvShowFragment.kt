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
import com.hafizrahmadhani.movie.activity.MovieDetailActivity.Companion.typeTvShow
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.databinding.FragmentTvShowBinding
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail
import com.hafizrahmadhani.movie.viewmodel.ViewModelTvShow
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private lateinit var viewModelTvShow: ViewModelTvShow
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelTvShow = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelTvShow::class.java)
        viewModelTvShow.callData()
        viewModelTvShow.takeData().observe(viewLifecycleOwner, {
            binding.tvshowProgressbar.visibility = View.GONE
            callRecycler(it)
        })
    }

    private fun callRecycler(tvShow: ArrayList<DataModelMovieDetail>){
        rv_tvshow.apply {
            val tvShowAdapter = MovieAdapter(tvShow)
            adapter = tvShowAdapter

            tvShowAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataModelMovieDetail) {
                    val detailIntent = Intent(context, MovieDetailActivity::class.java)
                    detailIntent.putExtra(extraMovie, data.title)
                    detailIntent.putExtra(extraType, typeTvShow)
                    startActivity(detailIntent)
                }
            })
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
    }
}