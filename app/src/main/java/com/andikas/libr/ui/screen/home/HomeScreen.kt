package com.andikas.libr.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andikas.libr.R
import com.andikas.libr.model.BookEntity
import com.andikas.libr.ui.common.UiState
import com.andikas.libr.ui.components.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAbout: () -> Unit,
    navigateToFavorite: () -> Unit,
    navigateToDetail: (id: Long) -> Unit,
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                LoadingSection()
                viewModel.getBooks()
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::searchBooks,
                    books = uiState.data,
                    modifier = modifier,
                    navigateToFavorite = navigateToFavorite,
                    navigateToDetail = navigateToDetail,
                    navigateToAbout = navigateToAbout,
                    onFavoriteClick = viewModel::updateBook
                )
            }
            is UiState.Error -> {
                ErrorSection(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    books: List<BookEntity>,
    modifier: Modifier = Modifier,
    navigateToAbout: () -> Unit,
    navigateToFavorite: () -> Unit,
    navigateToDetail: (id: Long) -> Unit,
    onFavoriteClick: (id: Long, isFavorite: Boolean) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 24.dp,
                end = 16.dp,
                bottom = 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TitleSection(
                        title = stringResource(R.string.app_name),
                        isHome = true,
                        navigateToAbout = navigateToAbout
                    )
                    SearchSection(
                        query = query,
                        onQueryChange = onQueryChange,
                        isHome = true,
                        navigateToFavorite = navigateToFavorite
                    )
                }
            }
            if (books.isNotEmpty()) {
                items(books, key = { it.id }) { book ->
                    BookItem(
                        id = book.id,
                        image = book.image,
                        title = book.title,
                        summary = book.summary,
                        isFavorite = book.isFavorite,
                        onFavoriteClick = onFavoriteClick,
                        onItemClick = navigateToDetail
                    )
                }
            } else {
                item {
                    EmptySection(
                        modifier = Modifier
                            .padding(64.dp)
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 32.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                }
            )
        }
    }
}