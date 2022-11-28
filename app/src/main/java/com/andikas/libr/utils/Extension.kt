package com.andikas.libr.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.andikas.libr.R

object Extension {

    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.longToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    val librFonts = FontFamily(
        Font(R.font.roboto_regular),
        Font(R.font.roboto_bold, weight = FontWeight.Bold),
    )

}