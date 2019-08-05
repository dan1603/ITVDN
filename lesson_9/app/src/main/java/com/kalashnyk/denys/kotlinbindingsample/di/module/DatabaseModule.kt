package com.kalashnyk.denys.kotlinbindingsample.di.module

import com.kalashnyk.denys.kotlinbindingsample.repository.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }
}