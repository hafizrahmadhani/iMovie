package com.hafizrahmadhani.movie.data.database

import androidx.lifecycle.LiveData
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mMovieDao).apply {
                INSTANCE = this
            }
    }

    fun callFavoriteMovie() : DataSource.Factory<Int, MovieEntity> = mMovieDao.favoriteMovieList()

    fun callFavoriteTvShow() : DataSource.Factory<Int, MovieEntity> = mMovieDao.favoriteTvShowList()

    suspend fun insertMovieFavorite(movie : MovieEntity){
        withContext(Dispatchers.IO){
            mMovieDao.insert(movie)
        }
    }

    suspend fun deleteFavoriteMovie(movie: MovieEntity){
        withContext(Dispatchers.IO) {
            mMovieDao.delete(movie)
        }
    }

    fun ifFavoriteMovie(id : Int) : LiveData<Boolean>{
        val favorite = SimpleSQLiteQuery("select count(*) > 0 from movie where id = $id")
        return mMovieDao.ifMovieFavorite(favorite)
    }

}