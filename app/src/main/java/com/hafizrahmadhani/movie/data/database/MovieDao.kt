package com.hafizrahmadhani.movie.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.hafizrahmadhani.movie.activity.TypeMovie
import com.hafizrahmadhani.movie.data.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie : MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

    @Query("select * from movie where type = ${TypeMovie.typeMovie}")
    fun favoriteMovieList() : DataSource.Factory<Int, MovieEntity>

    @Query("select * from movie where type = ${TypeMovie.typeTvShow}")
    fun favoriteTvShowList() : DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun ifMovieFavorite(query: SupportSQLiteQuery) : LiveData<Boolean>
}