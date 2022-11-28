package com.andikas.libr.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.andikas.libr.ui.components.LoadingSection
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    LoadingSection(
        modifier = modifier,
    )
    LaunchedEffect(true) {
        delay(2000)
        viewModel.insertBooks().run {
            navigateToHome()
        }
    }
}