package com.andikas.libr.ui.screen.detail

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
class DetailViewModel @Inject constructor(
    private val librRepository: LibrRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<BookEntity>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<BookEntity>> get() = _uiState

    fun getBookDetail(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            librRepository.getBookDetail(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun updateBook(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            librRepository.updateBook(id, isFavorite)
        }
    }

}