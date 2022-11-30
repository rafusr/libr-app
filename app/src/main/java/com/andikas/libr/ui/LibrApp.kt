package com.andikas.libr.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andikas.libr.ui.navigation.Screen
import com.andikas.libr.ui.screen.about.AboutScreen
import com.andikas.libr.ui.screen.detail.DetailScreen
import com.andikas.libr.ui.screen.favorite.FavoriteScreen
import com.andikas.libr.ui.screen.home.HomeScreen
import com.andikas.libr.ui.screen.splash.SplashScreen
import com.andikas.libr.ui.theme.LibrTheme

@Composable
fun LibrApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToAbout = {
                    navController.navigate(Screen.About.route)
                },
                navigateToFavorite = {
                    navController.navigate(Screen.Favorite.route)
                },
                navigateToDetail = { detailId ->
                    navController.navigate(Screen.Detail.createRoute(detailId))
                }
            )
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToDetail = { detailId ->
                    navController.navigate(Screen.Detail.createRoute(detailId))
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("detailId") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("detailId") ?: -1L
            DetailScreen(
                detailId = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun LibrAppPreview() {
    LibrTheme {
        LibrApp()
    }
}