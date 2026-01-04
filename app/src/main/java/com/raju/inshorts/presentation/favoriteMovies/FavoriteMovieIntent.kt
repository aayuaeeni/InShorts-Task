package com.raju.inshorts.presentation.favoriteMovies

sealed class FavoriteMovieIntent {
    object GetFavoriteMovies : FavoriteMovieIntent()
}