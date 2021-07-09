package com.hafizrahmadhani.movie.data

import com.hafizrahmadhani.movie.data.entity.DetailMovieEntity
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.data.response.*

object BankData {
        fun dataMovieList(): List<MovieEntity> {
                val data = ArrayList<MovieEntity>()
                data.add(
                        MovieEntity(
                                1,
                                "A Star is Born (2018)",
                                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                                "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                                7.5,
                        )
                )
                return data
        }

        fun dataMovieDetail(): DetailMovieEntity {
                return DetailMovieEntity(
                        1,
                        "A Star is Born (2018)",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        genre = listOf("Drama, Romance, Music"),
                        "10/05/2018 (US)",
                        7.5
                )
        }

        fun callMovieListResponse(): MovieResponse {
                return MovieResponse(
                        movie = mutableListOf(
                                MovieApiResponse(
                                        1,
                                        "A Star is Born (2018)",
                                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                                        "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                                        7.5
                                )
                        )
                )
        }

        fun callMovieDetailResponse(): MovieDetailResponse {
                return MovieDetailResponse(
                        1,
                        "A Star is Born (2018)",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "https://www.themoviedb.org/t/p/w1280/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        genres = listOf(GenreResponse("Drama, Romance, Music")),
                        "10/05/2018 (US)"
                )
        }

        fun callTvShowListResponse(): TvResponse {
                return TvResponse(
                        tv_show = mutableListOf(
                                TvApiResponse(
                                        1,
                                        "Dragon Ball Z (1989)",
                                        "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                                        "https://www.themoviedb.org/t/p/w1280/f2zhRLqwRLrKhEMeIM7Z5buJFo3.jpg",
                                        8.2
                                )
                        )
                )
        }

        fun callTvShowDetailResponse(): TvDetailResponse {
                return TvDetailResponse(
                        1,
                        "Dragon Ball Z (1989)",
                        "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                        "https://www.themoviedb.org/t/p/w1280/f2zhRLqwRLrKhEMeIM7Z5buJFo3.jpg",
                        "https://www.themoviedb.org/t/p/w1280/f2zhRLqwRLrKhEMeIM7Z5buJFo3.jpg",
                        genres = listOf(GenreResponse("Animation, Sci-Fi & Fantasy, Action & Adventure")),
                        "April 26, 1989"
                )
        }
}
