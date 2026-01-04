package com.raju.inshorts.domain.usecase

import android.util.Log
import com.raju.inshorts.data.mapper.toMovie
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.repository.MoviesRepository
import com.raju.inshorts.utils.Constant.TRENDING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTrendingMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.getTrendingMovies().map { listMovieEntity ->
            Log.i("TrendingMoviesUseCase", "getTrendingMovies: $listMovieEntity")

            listMovieEntity.map {movieEntity ->
                movieEntity.toMovie(TRENDING)  // convert MovieEntity to Movie
            }
        }
    }
}