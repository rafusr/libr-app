package com.andikas.libr.data

import com.andikas.libr.data.local.dao.BookDao
import com.andikas.libr.model.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LibrRepository @Inject constructor(
    private val bookDao: BookDao
) {

    fun getBooks(): Flow<List<BookEntity>> {
        return bookDao.getBooks()
    }

    fun getFavoriteBooks(): Flow<List<BookEntity>> {
        return bookDao.getFavoriteBooks()
    }

    fun getBookDetail(id: Long): Flow<BookEntity> {
        return bookDao.getBookDetail(id)
    }

    fun searchBooks(query: String): Flow<List<BookEntity>> {
        return bookDao.searchBooks(query)
    }

    fun searchFavoriteBooks(query: String): Flow<List<BookEntity>> {
        return bookDao.searchFavoriteBooks(query)
    }

    fun addBooks(books: List<BookEntity>): Flow<String> {
        bookDao.addBooks(books)
            .run {
                return flowOf("")
            }
    }

    fun updateBook(id: Long, isFavorite: Boolean): Flow<String> {
        bookDao.updateBook(id, isFavorite)
            .run {
                return flowOf("")
            }
    }

}