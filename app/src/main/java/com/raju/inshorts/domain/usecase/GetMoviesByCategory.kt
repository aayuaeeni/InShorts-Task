package com.raju.inshorts.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.raju.inshorts.data.mapper.toMovie
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetMoviesByCategory(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(category: String): Flow<PagingData<Movie>> {
        return moviesRepository.getMoviesListByCategory(category).map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovie(category)  // convert MovieEntity to Movie
            }
        }
    }
}