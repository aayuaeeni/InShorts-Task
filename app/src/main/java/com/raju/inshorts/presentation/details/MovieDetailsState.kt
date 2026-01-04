package com.raju.inshorts.presentation.details

import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.domain.model.MovieCast
import com.raju.inshorts.utils.UiState

data class MovieDetailsState(
    val movieState: UiState<Movie> = UiState.Loading,
    val favoriteState: Boolean = false,
    val movieCastState: UiState<List<MovieCast>> = UiState.Loading,
)
