package com.andikas.libr.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andikas.libr.R

@Composable
fun SearchSection(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isHome: Boolean,
    navigateToFavorite: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
        )
        if (isHome) Button(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(start = 8.dp)
                .height(52.dp)
                .width(52.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Color(0xFFFF4D4D)
            ),
            elevation = null,
            onClick = { navigateToFavorite?.invoke() }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(id = R.string.favorite_books),
            )
        }
    }
}