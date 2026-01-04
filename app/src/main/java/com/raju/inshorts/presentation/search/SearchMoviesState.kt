package com.raju.inshorts.presentation.search

import androidx.paging.PagingData
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.utils.UiState
import kotlinx.coroutines.flow.Flow

data class SearchMoviesState(
    val idledState: Boolean = true,
    val searchQuery: String = "",
    val moviesState: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
)
