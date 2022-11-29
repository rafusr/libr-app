package com.andikas.libr.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.andikas.libr.R

object Extension {

    val librFonts = FontFamily(
        Font(R.font.roboto_regular),
        Font(R.font.roboto_bold, weight = FontWeight.Bold),
    )

}