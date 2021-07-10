package com.hafizrahmadhani.movie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hafizrahmadhani.movie.data.response.GenreResponse
import com.hafizrahmadhani.movie.data.response.MovieDetailResponse
import com.hafizrahmadhani.movie.data.response.MovieResponse
import com.hafizrahmadhani.movie.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieResponse = BankData.dataMovie()
    private val dataMovieDetail = MovieDetailResponse(1,
            "A Star is Born (2018)",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            genres = listOf(GenreResponse("Drama, Romance, Music")),
            "10/05/2018 (US)")

    private val tvShowResponse = BankData.dataTvShow()
    private val dataTvShowDetail = MovieDetailResponse(1,
            "Dragon Ball Z (1989)",
            "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
            "https://www.themoviedb.org/t/p/w1280/f2zhRLqwRLrKhEMeIM7Z5buJFo3.jpg",
            "https://www.themoviedb.org/t/p/w1280/f2zhRLqwRLrKhEMeIM7Z5buJFo3.jpg",
            genres = listOf(GenreResponse("Animation, Sci-Fi & Fantasy, Action & Adventure")),
            "April 26, 1989")


    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val fakeMovieRepository = FakeMovieRepository(remoteDataSource)


    @Test
    fun callMovieList(){
        val dataMovie = BankData.dataMovie()
        val movieList = arrayListOf<MovieResponse>()
        dataMovie.forEach { list -> val value =
        MovieResponse(
                list.title,
                list.description,
                list.poster,
                list.image,
                list.date,
                list.voteAverage!!.toDouble(),
                list.genre?.map { genre -> GenreResponse(genre.name) },
                list.id
        )
        movieList.add(value)}

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.CallListCallback)
                    .callListBack(movieList.toList())
            null
        }.`when`(remoteDataSource).callMovieList(eq(1), any())

        val list = LiveDataTestUtil.getValue(fakeMovieRepository.callMovieApi(1))
        verify(remoteDataSource).callMovieList(eq(1), any())
        assertNotNull(movieList)
        assertEquals(movieResponse.size.toLong(), list.size.toLong())
    }

    @Test
    fun callDetailMovie(){
        val detailMovie = BankData.dataMovie()[0]

        val genre = detailMovie.genre?.map { genre ->
            GenreResponse(genre.name)
        }
        val detail = MovieDetailResponse(
                detailMovie.id,
                detailMovie.title,
                detailMovie.description,
                detailMovie.poster,
                detailMovie.image,
                genre,
                detailMovie.date
        )
        val value = MutableLiveData<MovieDetailResponse>()
        value.value = detail

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.CallDetailCallback)
                    .callDetailBack(dataMovieDetail)
            null
        }.`when`(remoteDataSource).callMovieDetail(eq(1), any())

        val mDetail = LiveDataTestUtil.getValue(fakeMovieRepository.callDetailMovie(1))
        verify(remoteDataSource).callMovieDetail(eq(1), any())
        assertNotNull(mDetail)
        assertEquals(detail, mDetail)
    }

    @Test
    fun callTvShowList() {
        val dataTvShow = BankData.dataTvShow()
        val tvShowList = arrayListOf<MovieResponse>()
        dataTvShow.forEach {
            list -> val value = MovieResponse(
                list.title,
                list.description,
                list.poster,
                list.image,
                list.date,
                list.voteAverage!!.toDouble(),
                list.genre?.map { genre -> GenreResponse(genre.name) },
                list.id
            )
            tvShowList.add(value)
        }

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.CallListCallback)
                    .callListBack(tvShowList.toList())
            null
        }.`when`(remoteDataSource).callTvList(eq(1), any())

        val list = LiveDataTestUtil.getValue(fakeMovieRepository.callTvApi(1))
        verify(remoteDataSource).callTvList(eq(1), any())
        assertNotNull(list)
        assertEquals(tvShowResponse.size.toLong(), list.size.toLong())
    }

    @Test
    fun getTVDetail(){
        val detailTvShow = BankData.dataTvShow()[0]

        val genre = detailTvShow.genre?.map { genre ->
            GenreResponse(genre.name)
        }
        val detail = MovieDetailResponse(
                detailTvShow.id,
                detailTvShow.title,
                detailTvShow.description,
                detailTvShow.poster,
                detailTvShow.image,
                genre,
                detailTvShow.date)
        val value = MutableLiveData<MovieDetailResponse>()
        value.value = detail

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.CallDetailCallback)
                    .callDetailBack(dataTvShowDetail)
            null
        }.`when`(remoteDataSource).callTvShowDetail(eq(1), any())

        val tDetail = LiveDataTestUtil.getValue(fakeMovieRepository.callDetailTv(1))
        verify(remoteDataSource).callTvShowDetail(eq(1), any())
        assertNotNull(tDetail)
        assertEquals(detail, tDetail)
    }
}