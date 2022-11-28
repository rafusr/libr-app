package com.andikas.libr

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
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToAbout = {
                    navHostController.navigate(Screen.About.route)
                },
                navigateToFavorite = {
                    navHostController.navigate(Screen.Favorite.route)
                },
                navigateToDetail = { detailId ->
                    navHostController.navigate(Screen.Detail.createRoute(detailId))
                }
            )
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(
                onBackPressed = {
                    navHostController.navigateUp()
                },
                navigateToDetail = { detailId ->
                    navHostController.navigate(Screen.Detail.createRoute(detailId))
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                onBackPressed = {
                    navHostController.navigateUp()
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
                    navHostController.navigateUp()
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