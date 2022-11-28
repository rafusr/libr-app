package com.andikas.libr.di

import android.app.Application
import com.andikas.libr.data.local.dao.BookDao
import com.andikas.libr.data.local.room.LibrDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): LibrDatabase {
        return LibrDatabase.instance(application)
    }

    @Provides
    @Singleton
    fun provideBookDao(librDatabase: LibrDatabase): BookDao {
        return librDatabase.bookDao()
    }

}