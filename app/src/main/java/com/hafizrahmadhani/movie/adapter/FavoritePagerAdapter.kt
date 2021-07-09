package com.hafizrahmadhani.movie.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hafizrahmadhani.movie.fragment.FavoriteMovieFragment
import com.hafizrahmadhani.movie.fragment.FavoriteTvShowFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }
    }

}