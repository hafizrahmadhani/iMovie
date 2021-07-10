package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hafizrahmadhani.movie.adapter.PagerAdapter
import com.hafizrahmadhani.movie.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            val pagerAdapter = PagerAdapter(this@MainActivity)
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tab, viewPager){
                tab, position -> tab.text = TAB_TITLES[position]
            }.attach()
        }
    }

}