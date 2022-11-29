package com.andikas.libr.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andikas.libr.R
import com.andikas.libr.ui.components.TitleSection
import com.andikas.libr.utils.Extension

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    AboutContent(
        modifier = modifier,
        navigateBack = navigateBack
    )
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
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
            title = stringResource(id = R.string.about),
            shouldBack = true,
            navigateBack = navigateBack
        )
        Image(
            painter = painterResource(id = R.drawable.andikas),
            modifier = Modifier
                .height(128.dp)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = stringResource(id = R.string.dev_name)
        )
        Text(
            text = stringResource(id = R.string.dev_name),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = Extension.librFonts,
            ),
        )
        Text(
            text = stringResource(id = R.string.dev_email),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = Extension.librFonts,
            ),
        )
    }
}