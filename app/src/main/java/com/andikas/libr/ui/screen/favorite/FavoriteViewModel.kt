package com.andikas.libr.ui.screen.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andikas.libr.data.LibrRepository
import com.andikas.libr.model.BookEntity
import com.andikas.libr.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val librRepository: LibrRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _uiState: MutableStateFlow<UiState<List<BookEntity>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<BookEntity>>> get() = _uiState

    fun getFavoriteBooks() {
        viewModelScope.launch(ioDispatcher) {
            librRepository.getFavoriteBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }

    fun updateBook(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            librRepository.updateBook(id, isFavorite)
        }
    }

    fun searchFavoriteBooks(newQuery: String) {
        viewModelScope.launch(ioDispatcher) {
            _query.value = newQuery
            librRepository.searchFavoriteBooks(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }

}