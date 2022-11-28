package com.andikas.libr.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andikas.libr.data.LibrRepository
import com.andikas.libr.model.BooksData.books
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val librRepository: LibrRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun insertBooks() {
        viewModelScope.launch(ioDispatcher) {
            val currentBooks = librRepository.getBooks().first()
            if (currentBooks.isEmpty()) {
                librRepository.addBooks(books)
            }
        }
    }

}