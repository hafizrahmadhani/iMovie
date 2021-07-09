package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hafizrahmadhani.movie.adapter.FavoritePagerAdapter
import com.hafizrahmadhani.movie.databinding.ActivityFavoriteBinding
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteActivity : AppCompatActivity() {

    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }

    private lateinit var favoriteBinding : ActivityFavoriteBinding
    private lateinit var favoritePagerAdapter: FavoritePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        with(favoriteBinding){
            favoritePagerAdapter = FavoritePagerAdapter(this@FavoriteActivity)
            viewPagerFavorite.adapter = favoritePagerAdapter
            TabLayoutMediator(tabFavorite, viewPagerFavorite)
            {
                tabFavorite, position -> tabFavorite.text = TAB_TITLES[position]
            }.attach()
        }
    }
}