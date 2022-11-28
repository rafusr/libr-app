package com.andikas.libr.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andikas.libr.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookDetail(id: Long): Flow<BookEntity>

    @Query("SELECT * FROM books WHERE title = :query")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isFavorite = 1 AND title = :query")
    fun searchFavoriteBooks(query: String): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBooks(books: List<BookEntity>)

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE id = :id")
    fun updateBook(id: Long, isFavorite: Boolean)

}