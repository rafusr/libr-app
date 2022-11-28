package com.andikas.libr.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("detail/{detailId}") {
        fun createRoute(detailId: Long) = "detail/$detailId"
    }
}