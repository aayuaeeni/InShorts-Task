package com.raju.inshorts.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raju.inshorts.R
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.presentation.bottomNavigation.BottomBar
import com.raju.inshorts.presentation.bottomNavigation.BottomNavItem
import com.raju.inshorts.presentation.common.HideNavigationBarOnly
import com.raju.inshorts.presentation.details.MovieDetailsScreen
import com.raju.inshorts.presentation.details.MovieDetailsViewModel
import com.raju.inshorts.presentation.favoriteMovies.FavoriteMovieViewModel
import com.raju.inshorts.presentation.favoriteMovies.FavoriteMoviesScreen
import com.raju.inshorts.presentation.home.HomeScreen
import com.raju.inshorts.presentation.home.HomeViewModel
import com.raju.inshorts.presentation.search.SearchMoviesViewModel
import com.raju.inshorts.presentation.search.SearchScreen
import com.raju.inshorts.utils.Constant.FAVOURTIE
import com.raju.inshorts.utils.Constant.HOME
import com.raju.inshorts.utils.Constant.SEARCH

@Composable
fun ContentNavGraph() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavItem(Icons.Default.Home, HOME),
            BottomNavItem(Icons.Default.Search, SEARCH),
            BottomNavItem(Icons.Default.Favorite, FAVOURTIE)
        )
    }


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backStackState?.destination?.route) {
        Screen.HomeScreen.route -> 0
        Screen.SearchScreen.route -> 1
        Screen.FavoriteMoviesScreen.route -> 2
        else -> 0
    }


    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.HomeScreen.route ||
                backStackState?.destination?.route == Screen.SearchScreen.route ||
                backStackState?.destination?.route == Screen.FavoriteMoviesScreen.route
    }


    // Hide navigation bar
    HideNavigationBarOnly()


    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomBar(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screen.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screen.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Screen.FavoriteMoviesScreen.route
                            )
                        }
                    }
                )

            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.5f)
            )

            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route,
            ) {
                composable(route = Screen.HomeScreen.route) { backStackEntry ->

                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.HomeScreen.route)
                    }

                    val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)

                    HomeScreen(
                        viewModel = homeViewModel,
                        onClickSearchBar = {
                            navController.navigate(Screen.SearchScreen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }  // 1 ==> translate to search screen
                        },
                        navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        })
                }

                composable(route = Screen.SearchScreen.route) {
                    val viewModel: SearchMoviesViewModel = hiltViewModel()
                    SearchScreen(
                        viewModel = viewModel,
                        navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        }
                    )
                }
                composable(route = Screen.FavoriteMoviesScreen.route) {
                    val viewModel: FavoriteMovieViewModel = hiltViewModel()
                    FavoriteMoviesScreen(
                        viewModel, navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        }
                    )
                }

                composable(route = Screen.DetailsScreen.route) { _ ->
                    val viewModel: MovieDetailsViewModel = hiltViewModel()

                    navController.previousBackStackEntry?.savedStateHandle?.get<Movie?>("movie")
                        ?.let { movie ->
                            MovieDetailsScreen(movie = movie, viewModel = viewModel, onBackClick = {
                                navController.popBackStack()
                            })
                        }
                }
            }

        }


    }
}


fun navigateToDetails(navController: NavHostController, movie: Movie) {
    // this will save the article in the savedStateHandle
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(
        route = Screen.DetailsScreen.route
    )
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
