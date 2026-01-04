package com.raju.inshorts.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.presentation.common.SearchBar
import com.raju.inshorts.presentation.home.component.MovieSectionContent
import com.raju.inshorts.presentation.home.component.Section
import com.raju.inshorts.presentation.home.component.TrendingMovieSectionContent
import com.raju.inshorts.utils.Constant

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClickSearchBar: () -> Unit,
    navigateToDetails: (Movie) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    // Trigger data loading
    LaunchedEffect(Unit) {
        viewModel.handleIntent(HomeIntent.LoadTrendingMovies)
        viewModel.handleIntent(HomeIntent.LoadPopularMovies)
        viewModel.handleIntent(HomeIntent.LoadTopRatedMovies)
        viewModel.handleIntent(HomeIntent.LoadUpcomingMovies)
        viewModel.handleIntent(HomeIntent.LoadDiscoverMovies)
        viewModel.handleIntent(HomeIntent.LoadNowPlayingMovies)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 46.dp, bottom = 60.dp)
    ) {
        SearchBar(isClickable = true,
            onClick = {onClickSearchBar()}
        )

        Spacer(modifier = Modifier.height(20.dp))

        TrendingMovieSectionContent(uiState.trendingMovies)

        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Now Playing Movies") {
            MovieSectionContent(moviesCategory = Constant.NOW_PLAYING, uiState.nowPlayingMovies) { movie ->
                navigateToDetails(movie)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Popular Movies") {
            MovieSectionContent(moviesCategory = Constant.POPULAR, uiState.popularMovies) { movie ->
                navigateToDetails(movie)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Upcoming Movies") {
            MovieSectionContent(moviesCategory = Constant.UPCOMING, uiState.upcomingMovies) { movie ->
                navigateToDetails(movie)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Discover Movies") {
            MovieSectionContent(moviesCategory = Constant.DISCOVER, uiState.discoverMovies) { movie ->
                navigateToDetails(movie)
            }
        }

    }
}