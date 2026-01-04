package com.raju.inshorts.presentation.details

import com.raju.inshorts.domain.model.Movie


sealed class MovieDetailsIntent {
    data class CheckFavoriteMovie(val movie: Movie) : MovieDetailsIntent()
    data class ToggleFavorite(val movie: Movie, val favorite: Boolean) : MovieDetailsIntent()
    data class GetMovieCast(val movieId: Int) : MovieDetailsIntent()
}