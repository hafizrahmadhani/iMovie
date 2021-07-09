package com.hafizrahmadhani.movie.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        @Volatile
        private var position: Retrofit? = null
        fun getPosition(): Retrofit {
            return position ?: synchronized(this) {
                position ?: Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }
}