package com.andikas.libr.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andikas.libr.R
import com.andikas.libr.ui.components.TitleSection
import com.andikas.libr.utils.Extension

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    AboutContent(
        modifier = modifier,
        onBackPressed = onBackPressed
    )
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                top = 24.dp,
                end = 16.dp,
                bottom = 80.dp
            ),
    ) {
        TitleSection(
            title = "About",
            shouldBack = true,
            onBackPressed = { onBackPressed() }
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.andikas),
                modifier = Modifier
                    .height(128.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "Andika Sultanrafli"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Andika Sultanrafli",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = Extension.librFonts,
                    ),
                )
                Text(
                    text = "andpuji27@gmail.com",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = Extension.librFonts,
                    ),
                )
            }
        }
    }
}