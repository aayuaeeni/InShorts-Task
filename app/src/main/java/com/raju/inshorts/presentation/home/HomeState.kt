package com.raju.inshorts.presentation.home

import androidx.paging.PagingData
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.utils.UiState
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val trendingMovies: UiState<List<Movie>> = UiState.Loading,
    val popularMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val topRatedMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val upcomingMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val discoverMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val nowPlayingMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,

    )