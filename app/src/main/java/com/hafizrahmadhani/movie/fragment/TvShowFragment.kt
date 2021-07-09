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
import com.hafizrahmadhani.movie.databinding.FragmentTvShowBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelFactory
import com.hafizrahmadhani.movie.viewmodel.ViewModelTvShow
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TvShowFragment : Fragment() {

    private lateinit var viewModelTvShow: ViewModelTvShow
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFactory = ViewModelFactory.getPosition(requireActivity())
        viewModelTvShow = ViewModelProvider(this, tvFactory)[ViewModelTvShow::class.java]

        val adapter = MovieAdapter {
            val detailintent = Intent(context, MovieDetailActivity::class.java)
            detailintent.putExtra(MovieDetailActivity.extraMovie, it.id)
            detailintent.putExtra(MovieDetailActivity.extraType, TypeMovie.typeTvShow)
            startActivity(detailintent)
        }

        viewModelTvShow.callData().observe(viewLifecycleOwner, { list ->
            list?.let { data ->
                binding.tvshowProgressbar.visibility = View.GONE
                adapter.setMovie(data)
                adapter.notifyDataSetChanged()
            }
        })

        with(binding.rvTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        viewModelTvShow.callLoading().observe(viewLifecycleOwner, {
            binding.tvshowProgressbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModelTvShow.callNotifMessage().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.notifMessageTv.apply {
                    visibility = View.VISIBLE
                    text = it
                }
            } else {
                binding.notifMessageTv.visibility = View.GONE
            }
        })
    }
}

