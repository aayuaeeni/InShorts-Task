package com.raju.inshorts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raju.inshorts.presentation.common.HideNavigationBarOnly
import com.raju.inshorts.presentation.intro.IntroScreen
import com.raju.inshorts.presentation.intro.SplashScreen


@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    // Hide navigation bar
    HideNavigationBarOnly()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(route = Screen.IntroScreen.route) { IntroScreen(navController) }
        composable(route = Screen.ContentNavGraph.route) { ContentNavGraph() }
    }
}
