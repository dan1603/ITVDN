package com.kalashnyk.denys.kotlinsample.di.module

import com.kalashnyk.denys.kotlinsample.repository.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }
}