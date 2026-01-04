package com.raju.inshorts.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.raju.inshorts.data.mapper.toMovie
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return moviesRepository.searchMovies(query).map { pagingData ->
            pagingData.map { movieDto ->
                movieDto.toMovie() // Assuming you have a mapper function
            }
        }
    }
}