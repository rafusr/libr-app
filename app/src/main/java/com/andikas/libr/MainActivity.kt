package com.andikas.libr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.andikas.libr.ui.LibrApp
import com.andikas.libr.ui.theme.LibrTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibrTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color(0xFFF7F7F7),
                        darkIcons = true
                    )
                    systemUiController.setSystemBarsColor(
                        color = Color(0xFFF7F7F7),
                        darkIcons = true
                    )
                    systemUiController.setNavigationBarColor(
                        color = Color(0xFFF7F7F7),
                        darkIcons = true
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LibrApp()
                }
            }
        }
    }
}