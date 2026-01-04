package com.raju.inshorts.domain.usecase

import com.raju.inshorts.data.mapper.toMovieCast
import com.raju.inshorts.domain.model.MovieCast
import com.raju.inshorts.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieCast(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(movieId: Int): Flow<List<MovieCast>> {
        val listMovieCast = moviesRepository.getMovieCast(movieId).map { listMovieCastEntity ->
            listMovieCastEntity.map {
                it.toMovieCast()
            }
        }
        return listMovieCast
    }
}