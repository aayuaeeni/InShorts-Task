package com.raju.inshorts.domain.usecase

import com.raju.inshorts.data.mapper.toMovie
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<List<Movie>> =
        moviesRepository.getAllFavoriteMovies().map { listFavoriteMovieEntity ->
            listFavoriteMovieEntity.map { favoriteMovieEntity ->
                favoriteMovieEntity.toMovie()
            }
        }
}