package com.andikas.libr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    var id: Long,
    var image: Int,
    var title: String,
    var summary: String,
    var author: String? = null,
    var isFavorite: Boolean = false
)