package com.hafizrahmadhani.movie.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hafizrahmadhani.movie.data.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile
        private var position : MovieDatabase? = null

        fun getPosition(context: Context) : MovieDatabase{
            if(position == null){
                synchronized(MovieDatabase::class.java){
                    if(position == null){
                        position = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, DB_NAME).build()
                    }
                }
            }
            return position as MovieDatabase
        }

        private const val DB_NAME = "MOVIE_DB.db"
    }
}