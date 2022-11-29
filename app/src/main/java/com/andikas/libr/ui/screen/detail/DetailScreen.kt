package com.andikas.libr.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andikas.libr.R
import com.andikas.libr.ui.common.UiState
import com.andikas.libr.ui.components.ErrorSection
import com.andikas.libr.ui.components.LoadingSection
import com.andikas.libr.ui.components.TitleSection

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    detailId: Long,
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                LoadingSection()
                viewModel.getBookDetail(detailId)
            }
            is UiState.Success -> {
                DetailContent(
                    modifier = modifier,
                    id = detailId,
                    image = uiState.data.image,
                    title = uiState.data.title,
                    summary = uiState.data.summary,
                    author = uiState.data.author ?: "",
                    isFavorite = uiState.data.isFavorite,
                    navigateBack = navigateBack,
                    onFavoriteClick = viewModel::updateBook,
                )
            }
            is UiState.Error -> {
                ErrorSection(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    id: Long,
    image: Int,
    title: String,
    summary: String,
    author: String,
    isFavorite: Boolean,
    navigateBack: () -> Unit,
    onFavoriteClick: (id: Long, isFavorite: Boolean) -> Unit,
) {
    var setFavorite by remember { mutableStateOf(isFavorite) }

    Box(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 16.dp,
                    top = 24.dp,
                    end = 16.dp,
                    bottom = 80.dp
                ),
        ) {
            TitleSection(
                title = title,
                shouldBack = true,
                navigateBack = navigateBack
            )
            Image(
                painter = painterResource(id = image),
                modifier = Modifier
                    .height(350.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = title
            )
            Text(
                text = summary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(top = 16.dp),
            )
            Text(
                text = stringResource(id = R.string.author, author),
                style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )
        }
        Button(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .padding(bottom = 32.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
                .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                .clip(shape = RoundedCornerShape(16.dp))
                .size(56.dp),
            onClick = {
                setFavorite = !setFavorite
                onFavoriteClick(id, setFavorite)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color(0xFFFF4D4D)
            )
        ) {
            Icon(
                imageVector = if (setFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = Color(0xFFFF4D4D),
                contentDescription = stringResource(id = R.string.add_to_favorite),
            )
        }
    }
}