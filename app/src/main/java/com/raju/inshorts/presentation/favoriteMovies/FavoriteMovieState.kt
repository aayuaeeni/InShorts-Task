package com.raju.inshorts.presentation.favoriteMovies

import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.utils.UiState

data class FavoriteMovieState(
    val favoriteMovies: UiState<List<Movie>> = UiState.Loading,
    val favoriteMoviesFound: Boolean = false,
)