
package com.hafizrahmadhani.movie.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hafizrahmadhani.movie.fragment.MovieFragment
import com.hafizrahmadhani.movie.fragment.TvShowFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }
}