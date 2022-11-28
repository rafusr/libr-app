package com.andikas.libr.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andikas.libr.data.local.dao.BookDao
import com.andikas.libr.model.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LibrDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var database: LibrDatabase? = null

        fun instance(context: Context): LibrDatabase {
            if (database == null) {
                synchronized(LibrDatabase::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        LibrDatabase::class.java,
                        "libr_db.db"
                    ).build()
                }
            }
            return database!!
        }
    }

}