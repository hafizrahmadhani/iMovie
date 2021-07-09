package com.hafizrahmadhani.movie.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hafizrahmadhani.movie.AppExecutors
import com.hafizrahmadhani.movie.data.database.LocalDataSource
import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.data.entity.MovieEntity


class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : SourceMovie {

    companion object {
        @Volatile
        private var position: MovieRepository? = null
        fun getPosition(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            position ?: synchronized(this) {
                position ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override suspend fun callMovieList(): List<MovieEntity> {
        val movielist = remoteDataSource.callMovieList()
        val value = ArrayList<MovieEntity>()
        movielist.movie.forEach {
            value.add(
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    description = it.overview,
                    poster = it.poster_path,
                    vote = it.vote_average
                )
            )
        }
        return value
    }

    override suspend fun callDetailMovie(idMovie: Int): DetailMovieEntity {
        val movieDetail = remoteDataSource.callMovieDetail(idMovie)
        val genre = movieDetail.genres.map { it.name }
        return DetailMovieEntity(
            movieDetail.id,
            movieDetail.title,
            movieDetail.overview,
            movieDetail.poster_path,
            movieDetail.backdrop_path,
            genre = genre,
            movieDetail.release_date,
            movieDetail.vote_average
        )
    }

    override suspend fun callTvShowList(): List<MovieEntity> {
        val tvList = remoteDataSource.callTvList()
        val value = ArrayList<MovieEntity>()
        tvList.tv_show.forEach {
            value.add(
                MovieEntity(
                    id = it.id,
                    title = it.name,
                    description = it.overview,
                    poster = it.poster_path,
                    vote = it.vote_average
                )
            )
        }
        return value.toList()
    }

    override suspend fun callDetailTv(idTv: Int): DetailMovieEntity {
        val tvDetail = remoteDataSource.callTvShowDetail(idTv)
        val genre = tvDetail.genres.map { it.name }
        return DetailMovieEntity(
            tvDetail.id,
            tvDetail.name,
            tvDetail.overview,
            tvDetail.poster_path,
            tvDetail.backdrop_path,
            genre = genre,
            tvDetail.first_air_date,
            tvDetail.vote_average
        )
    }

    override fun callMovieFavorite(): LiveData<PagedList<MovieEntity>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.callFavoriteMovie(), favorite).build()
    }

    override suspend fun insertMovieFavorite(movie: MovieEntity) {
        localDataSource.insertMovieFavorite(movie)
    }

    override suspend fun deleteMovieFavorite(movie: MovieEntity) {
        localDataSource.deleteFavoriteMovie(movie)
    }

    override fun callTvShowFavorite(): LiveData<PagedList<MovieEntity>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.callFavoriteTvShow(), favorite).build()
    }

    override fun ifFavoriteMovie(id: Int): LiveData<Boolean> {
        return localDataSource.ifFavoriteMovie(id)
    }

}