package com.andikas.libr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andikas.libr.utils.Extension

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String,
    shouldBack: Boolean = false,
    isHome: Boolean = false,
    onBackPressed: (() -> Unit?)? = null,
    onAboutPressed: (() -> Unit?)? = null,
) {
    Row(
        horizontalArrangement = if (shouldBack) Arrangement.Start else Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface, RoundedCornerShape(8.dp))
            .fillMaxWidth(),
    ) {
        if (shouldBack) Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(52.dp)
                .width(52.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Color.DarkGray
            ),
            elevation = null,
            onClick = { onBackPressed?.invoke() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "about_page",
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = Extension.librFonts,
                color = MaterialTheme.colors.primary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        )
        if (isHome) Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(52.dp)
                .width(52.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.surface
            ),
            elevation = null,
            onClick = { onAboutPressed?.invoke() }
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "about_page",
            )
        }
    }
}