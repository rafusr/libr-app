package com.andikas.libr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andikas.libr.R
import com.andikas.libr.ui.theme.LibrTheme
import com.andikas.libr.utils.Extension.librFonts

@Composable
fun BookItem(
    id: Long,
    image: Int,
    title: String,
    summary: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: (id: Long) -> Unit,
    onFavoriteClick: (id: Long, isFavorite: Boolean) -> Unit,
) {
    var setFavorite by remember { mutableStateOf(isFavorite) }
    println("INI IS FAVORITE == $isFavorite")
    println("INI SET FAVORITE == $setFavorite")

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onItemClick(id) },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier
                    .size(96.dp)
                    .shadow(6.dp, RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = librFonts
                    ),
                )
                Text(
                    text = summary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = {
                    setFavorite = !setFavorite
                    onFavoriteClick(id, setFavorite)
                },
            ) {
                Icon(
                    imageVector = if (setFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    tint = Color(0xFFFF4D4D),
                    contentDescription = "Add to favorite"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    LibrTheme {
        BookItem(
            1,
            R.drawable.no_longer_human,
            "No Longer Human",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            false,
            onFavoriteClick = { _, _ -> },
            onItemClick = { }
        )
    }
}