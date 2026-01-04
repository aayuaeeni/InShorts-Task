package com.raju.inshorts.domain.repository

import androidx.paging.PagingData
import com.raju.inshorts.data.source.local.FavoriteMovieEntity
import com.raju.inshorts.data.source.local.MovieCastEntity
import com.raju.inshorts.data.source.local.MovieEntity
import com.raju.inshorts.data.source.remote.dto.movie.MovieDto
import com.raju.inshorts.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getTrendingMovies(): Flow<List<MovieEntity>>

    fun getMoviesListByCategory(
        category: String,
    ): Flow<PagingData<MovieEntity>>


    fun getMovieCast(movieId: Int): Flow<List<MovieCastEntity>>

    fun searchMovies(query: String): Flow<PagingData<MovieDto>>


    suspend fun addFavoriteMovie(movie: Movie)

    suspend fun getFavoriteMovieById(movieId: Int): Movie?

    suspend fun deleteFavoriteMovie(movieId: Int)

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

}